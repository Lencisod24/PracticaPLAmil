package ast;
import java.util.ArrayList;

import semantico.TablaSimbolos;

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
                s += e.toString(tab + "  ");
            }
        }
        s += tab + ")\n";
        return s;
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (ts.buscaId(id) == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() + 
                            "]: La función '" + id + "' no está declarada.");
        }
        
        if (argumentos != null) {
            for (Expresion arg : argumentos) {
                if (arg != null) {
                    arg.chequea(ts);
                }
            }
        }
    }
}