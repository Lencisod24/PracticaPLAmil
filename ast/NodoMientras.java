package ast;

public class NodoMientras extends Instruccion {

    private Expresion condicion;
    private Instruccion bloque; // Normalmente será un NodoBloque

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
        return tab + "MIENTRAS (" + condicion.toString("") + ") HACER \n" +
                bloque.toString(tab + "  ");
    }
}