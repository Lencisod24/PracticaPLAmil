package ast;
import java.util.ArrayList;

public class NodoLlamadaFuncion extends Expresion {
    private String id;
    private ArrayList<Expresion> argumentos;

    public NodoLlamadaFuncion(int fil, int col, String id, ArrayList<Expresion> argumentos) {
        super(fil, col);
        this.id = id;
        this.argumentos = argumentos;
    }

    @Override
    public String toString(String tab) {
        String s = tab + "LLAMADA_FUNCION: " + id + " (\n";
        if (argumentos != null) {
            for (Expresion e : argumentos) {
                s += e.toString(tab + "  ") + "\n";
            }
        }
        s += tab + ")";
        return s;
    }
}