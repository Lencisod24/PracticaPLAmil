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

    public NodoFuncion(int fil, int col, String tipoRetorno, String identificador, List<NodoParametro> parametros,
            NodoBloque bloque) {
        super(fil, col);
        this.tipoRetorno = tipoRetorno;
        this.identificador = identificador;
        this.parametros = parametros;
        this.bloque = bloque;
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getTipo() {
        return this.tipoRetorno;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("FUNCION ").append(tipoRetorno).append(" ").append(identificador).append("() {\n");
        for (NodoParametro p : parametros) {
            sb.append(p.toString(tab + "  "));
        }
        sb.append(bloque.toString(tab + "  "));
        sb.append(tab).append("}\n");
        return sb.toString();
    }

    public List<NodoParametro> getParametros() {
        return parametros;
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Comprobamos que el valor de retorno sea válido
        if (!ComprobadorTipos.esTipoValido(tipoRetorno, ts) && !tipoRetorno.equals(Tipos.VACIO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El tipo de retorno '" + tipoRetorno + "' de la función '" + identificador
                    + "' no está definido.");
        }

        boolean insertado = ts.insertaId(identificador, this);
        if (!insertado) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: La función '" + identificador + "' ya ha sido declarada en este ámbito.");
        }

        ts.abreBloque();

        if (parametros != null) {
            for (NodoParametro param : parametros) {
                if (param != null) {
                    param.chequea(ts);
                }
            }
        }

        if (bloque != null) {
            for (Instruccion inst : bloque.getInstrucciones()) {
                if (inst != null)
                    inst.chequea(ts);
            }
        }

        ts.cierraBloque();
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        // curr.set(0);
        // max.set(0);
        for (NodoParametro np : this.parametros) {
            np.calcularMem(curr, max);
        }
        this.bloque.calcularMem(curr, max);

    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        String tab = "\t".repeat(indent);
        String tab2 = tab + "\t";

        // 1. Calcular el tamaño total de la memoria que necesitará esta función
        AtomicInteger curr = new AtomicInteger(4); // Offset 0-3 está reservado para guardar el viejo $MP
        AtomicInteger max = new AtomicInteger(4);

        if (parametros != null) {
            for (NodoParametro np : parametros)
                np.calcularMem(curr, max);
        }
        if (bloque != null) {
            bloque.calcularMem(curr, max);
        }
        int tamFrame = max.get();

        // 2. Generar la firma de la función Wasm
        sb.append(tab).append("(func $").append(identificador);

        // Añadir parámetros a la firma (WebAssembly los recibe en su pila interna)
        if (parametros != null) {
            for (NodoParametro p : parametros) {
                if (p.getTipo().equals("real") && !p.isPorReferencia()) {
                    sb.append(" (param f32)");
                } else {
                    sb.append(" (param i32)"); // Punteros, structs, enteros y booleanos usan i32
                }
            }
        }

        // Añadir tipo de retorno
        if (tipoRetorno.equals("real")) {
            sb.append(" (result f32)");
        } else if (!tipoRetorno.equals(Tipos.VACIO)) { // Asumo que usas tu constante VACIO
            sb.append(" (result i32)");
        }
        sb.append("\n");

        // 3. Prólogo: Reservar memoria y crear el enlace dinámico ($MP anterior)
        sb.append(tab2).append(";; Prologo\n");
        sb.append(tab2).append("i32.const ").append(tamFrame).append("\n");
        sb.append(tab2).append("call $reserveStack\n");
        sb.append(tab2).append("global.get $MP\n");
        sb.append(tab2).append("i32.store  ;; Guardar viejo $MP\n");

        // 4. Trasladar los parámetros Wasm a nuestra memoria lineal
        if (parametros != null && !parametros.isEmpty()) {
            sb.append(tab2).append(";; Volcar parametros a memoria\n");
            int localIdx = 0;
            for (NodoParametro p : parametros) {
                sb.append(tab2).append("global.get $MP\n");
                sb.append(tab2).append("i32.const ").append(p.getDelta()).append("\n");
                sb.append(tab2).append("i32.add\n");
                sb.append(tab2).append("local.get ").append(localIdx).append("\n");

                if (p.getTipo().equals("real") && !p.isPorReferencia()) {
                    sb.append(tab2).append("f32.store\n");
                } else {
                    sb.append(tab2).append("i32.store\n");
                    // Nota: Si pasas un Struct por valor, esto solo guarda la dirección base.
                    // Funciona perfectamente como referencia temporal dentro de la función.
                }
                localIdx++;
            }
        }

        // 5. Cuerpo de la función
        if (bloque != null) {
            sb.append(tab2).append(";; Cuerpo de la funcion\n");
            bloque.generateCodeInstruccion(sb, indent + 1);
        }

        // 6. Epílogo: Restaurar la pila del llamador
        sb.append(tab2).append(";; Epilogo\n");
        sb.append(tab2).append("call $freeStack\n");
        sb.append(tab).append(")\n");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        int dir = dirPadre; // Vale 4
        if (parametros != null) {
            for (NodoParametro np : parametros)
                dir = np.asignarDelta(dir);
        }
        if (bloque != null) {
            bloque.asignarDelta(dir);
        }
        return dirPadre;
    }
}