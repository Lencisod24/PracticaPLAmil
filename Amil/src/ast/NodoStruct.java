package ast;

import java.util.List;

import semantico.TablaSimbolos;

public class NodoStruct extends Declaracion {

    private String identificador;
    private List<Declaracion> campos;

    public NodoStruct(int fil, int col, String identificador, List<Declaracion> campos) {
        super(fil, col);
        this.identificador = identificador;
        this.campos = campos;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("STRUCT ").append(identificador).append(" {\n");

        for (Declaracion d : campos) {
            sb.append(d.toString(tab + "  "));
        }

        sb.append(tab).append("}\n");
        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        boolean insertado = ts.insertaId(identificador, this);
        if (!insertado) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() + 
                            "]: El struct '" + identificador + "' ya ha sido declarado en este ámbito.");
        }
        
        ts.abreBloque();
        
        if (campos != null) {
            for (Declaracion campo : campos) {
                if (campo != null) {
                    campo.chequea(ts);
                }
            }
        }
        
        ts.cierraBloque();
    }
}