package ast;

import java.util.List;

public class NodoStruct extends Declaracion {

    private String identificador;
    private List<Declaracion> atributos; 

    public NodoStruct(int fil, int col, String identificador, List<Declaracion> atributos) {
        super(fil, col);
        this.identificador = identificador;
        this.atributos = atributos;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("STRUCT ").append(identificador).append(" {\n");
        
        for (Declaracion d : atributos) {
            sb.append(d.toString(tab + "  "));
        }
        
        sb.append(tab).append("}\n");
        return sb.toString();
    }
}