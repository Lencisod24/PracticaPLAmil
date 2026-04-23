package ast;

import java.util.List;

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
}