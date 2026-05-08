package ast;

import java.util.List;

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

        // al hacer bloque otro abreBloque() tendremos que plantear si parametros y
        // variables
        // locales de funcion deben estar al mismo nivel
        // ahora mismo se produce un ocultamiento de los parametros por parte de las
        // variables locales ARREGLADO, YA NO HAY OCULTAMIENTO
        if (bloque != null) {
            for (Instruccion inst : bloque.getInstrucciones()) {
                if (inst != null)
                    inst.chequea(ts);
            }
        }

        ts.cierraBloque();
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeInstruccion'");
    }

    @Override
    public int calcularMem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularMem'");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }

    @Override
    public void asignarTamMemTipos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarTamMemTipos'");
    }
}