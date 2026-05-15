package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoInstruccionExpresion extends Instruccion {

    private Expresion exp;

    public NodoInstruccionExpresion(int fil, int col, Expresion exp) {
        super(fil, col);
        this.exp = exp;
    }

    public Expresion expresion() {
        return exp;
    }

    @Override
    public String toString(String tab) {
        return tab + "EVALUAR_EXPRESION\n" + (exp != null ? exp.toString(tab + "  ") : "");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (exp != null) {
            exp.chequea(ts);
        }
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        if (exp != null) {
            // Generamos el código de la expresión
            exp.generateCodeExpresion(sb, indent);
            String tab = "\t".repeat(indent);
            if (exp.getTipo() != null && !exp.getTipo().equals(Tipos.VACIO)) {
                // Si la instrucción devuelve algo a la pila, lo quitamos para que no se quede
                // flotando innecesariamente
                sb.append(tab).append("drop\n");
            }
        }
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // Delegamos en la expresión por si necesitara propagar el delta.
        if (exp != null) {
            return exp.asignarDelta(dirPadre);
        }
        return dirPadre;
    }
}