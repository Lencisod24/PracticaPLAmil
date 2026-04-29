package ast;

import java.util.List;

import semantico.TablaSimbolos;

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

    @Override
    public void chequea(TablaSimbolos ts) {
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
        
        //al hacer bloque otro abreBloque() tendremos que plantear si parametros y variables
        //locales de funcion deben estar al mismo nivel
        if (bloque != null) {
            bloque.chequea(ts);
        }
        
        ts.cierraBloque();
    }
}