package ast;

import semantico.TablaSimbolos;

public class NodoMientras extends Instruccion {

    private Expresion condicion;
    private Instruccion bloque; // Normalmente es un NodoBloque

    public NodoMientras(int fil, int col, Expresion condicion, Instruccion bloque) {
        super(fil, col);
        this.condicion = condicion;
        this.bloque = bloque;
    }

    public Expresion getCondicion() {
        return condicion;
    }

    public Instruccion getBloque() {
        return bloque;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("MIENTRAS\n");
        sb.append(condicion.toString(tab + "    "));
        sb.append(tab).append("HACER\n");
        sb.append(bloque.toString(tab + "    "));
        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (condicion != null) {
            condicion.chequea(ts);
        }
        if (bloque != null) {
            bloque.chequea(ts);
        }
    }
}