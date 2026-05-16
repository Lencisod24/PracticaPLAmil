package ast;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoFuncion extends Declaracion {

    private String tipoRetorno;
    private String identificador;
    private List<NodoParametro> parametros;
    private NodoBloque bloque;
    private int memMax;
    private int deltaRetorno;
    private NodoDecVariable varRetorno;

    public NodoFuncion(int fil, int col, String tipoRetorno, String identificador, List<NodoParametro> parametros,
            NodoBloque bloque) {
        super(fil, col);
        this.tipoRetorno = tipoRetorno;
        this.identificador = identificador;
        this.parametros = parametros;
        this.bloque = bloque;
        this.memMax = 0;
        this.deltaRetorno = 4; // justo después del DL por defecto
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getTipo() {
        return this.tipoRetorno;
    }

    public List<NodoParametro> getParametros() {
        return parametros;
    }

    public int getDeltaRetorno() {
        return deltaRetorno;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("FUNCION ").append(tipoRetorno).append(" ").append(identificador).append("() {\n");
        for (NodoParametro p : parametros)
            sb.append(p.toString(tab + "  "));
        sb.append(bloque.toString(tab + "  "));
        sb.append(tab).append("}\n");
        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (!ComprobadorTipos.esTipoValido(tipoRetorno, ts) && !tipoRetorno.equals(Tipos.VACIO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El tipo de retorno '" + tipoRetorno + "' de la función '" + identificador
                    + "' no está definido.");
        }

        if (!ts.insertaId(identificador, this)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: La función '" + identificador + "' ya ha sido declarada en este ámbito.");
        }

        ts.abreBloque();

        // Insertamos la variable de retorno implícita con el mismo nombre que la
        // función
        if (!tipoRetorno.equals(Tipos.VACIO)) {
            varRetorno = new NodoDecVariable(
                    getFila(), getColumna(), tipoRetorno, identificador, false, null, null);
            ts.insertaId(identificador, varRetorno);
        }

        if (parametros != null) {
            for (NodoParametro param : parametros)
                if (param != null)
                    param.chequea(ts);
        }

        if (bloque != null) {
            for (Instruccion inst : bloque.getInstrucciones())
                if (inst != null)
                    inst.chequea(ts);
        }

        ts.cierraBloque();
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        AtomicInteger c = new AtomicInteger(4); // 4 bytes para el DL
        AtomicInteger m = new AtomicInteger(4);

        // Variable de retorno implícita
        if (!tipoRetorno.equals(Tipos.VACIO)) {
            c.addAndGet(Tipos.getTamano(tipoRetorno));
            if (c.get() > m.get())
                m.set(c.get());
        }

        if (parametros != null)
            for (NodoParametro np : parametros)
                np.calcularMem(c, m);

        if (bloque != null)
            bloque.calcularMem(c, m);

        this.memMax = m.get();
    }

    @Override
    public int asignarDelta(int dirPadre) {
        int dir = dirPadre;

        if (!tipoRetorno.equals(Tipos.VACIO)) {
            this.deltaRetorno = dir;
            varRetorno.asignarDelta(dir);
            dir += Tipos.getTamano(tipoRetorno);
        }

        if (parametros != null)
            for (NodoParametro np : parametros) {
                dir = np.asignarDelta(dir);
            }

        if (bloque != null)
            bloque.asignarDelta(dir);

        return dirPadre;
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        String tab2 = "  ".repeat(indent + 1);

        sb.append(tab).append("(func $").append(identificador);

        // Parámetros WAT, uno por parámetro de nuestro lenguaje
        if (parametros != null) {
            for (NodoParametro p : parametros) {
                if (p.getTipo().equals(Tipos.REAL) && !p.isPorReferencia()) {
                    sb.append(" (param f32)");
                } else {
                    sb.append(" (param i32)");
                }
            }
        }

        // Tipo de retorno WAT
        if (tipoRetorno.equals(Tipos.REAL)) {
            sb.append(" (result f32)");
        } else if (!tipoRetorno.equals(Tipos.VACIO)) {
            sb.append(" (result i32)");
        }
        sb.append("\n");

        // Prólogo de la función
        sb.append(tab2).append(";; Prologo\n");
        sb.append(tab2).append("i32.const ").append(memMax).append("\n");
        sb.append(tab2).append("call $reserveStack\n");
        sb.append(tab2).append("global.get $MP\n");
        sb.append(tab2).append("i32.store\n");

        // Volcamos los parámetros
        if (parametros != null && !parametros.isEmpty()) {
            sb.append(tab2).append(";; Volcar parametros a memoria\n");
            int localIdx = 0;
            for (NodoParametro p : parametros) {
                sb.append(tab2).append("global.get $MP\n");
                sb.append(tab2).append("i32.const ").append(p.getDelta()).append("\n");
                sb.append(tab2).append("i32.add\n");
                sb.append(tab2).append("local.get ").append(localIdx).append("\n");
                if (p.getTipo().equals(Tipos.REAL) && !p.isPorReferencia()) {
                    sb.append(tab2).append("f32.store\n");
                } else {
                    sb.append(tab2).append("i32.store\n");
                }
                localIdx++;
            }
        }

        // Cuerpo de la función
        sb.append(tab2).append(";; Cuerpo\n");
        if (bloque != null)
            bloque.generateCodeInstruccion(sb, indent + 1);

        // Creamos la variable de retorno
        if (!tipoRetorno.equals(Tipos.VACIO)) {
            sb.append(tab2).append(";; Cargar valor de retorno\n");
            sb.append(tab2).append("global.get $MP\n");
            sb.append(tab2).append("i32.const ").append(deltaRetorno).append("\n");
            sb.append(tab2).append("i32.add\n");
            String load = tipoRetorno.equals(Tipos.REAL) ? "f32.load" : "i32.load";
            sb.append(tab2).append(load).append("\n");
        }

        // Epílogo de la función
        sb.append(tab2).append(";; Epilogo\n");
        sb.append(tab2).append("call $freeStack\n");

        // Si tiene retorno, el valor se queda en la cima de la pila después de
        // freeStack
        sb.append(tab).append(")\n");
    }
}