package ast;

import java.util.List;

public class NodoClase extends Declaracion {

    private String identificador;
    private List<Declaracion> cuerpo; // Para almacenar atributos y métodos

    public NodoClase(int fil, int col, String identificador, List<Declaracion> cuerpo) {
        super(fil, col);
        this.identificador = identificador;
        this.cuerpo = cuerpo;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("CLASE ").append(identificador).append(" {\n");
        for (Declaracion d : cuerpo) {
            sb.append(d.toString(tab + "  "));
        }
        sb.append(tab).append("}\n");
        return sb.toString();
    }
}