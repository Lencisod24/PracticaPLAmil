package ast;

import semantico.TablaSimbolos;

public class NodoAccesoArray extends Designador {

    private Designador array;
    private Expresion indice;

    public NodoAccesoArray(int fil, int col, Designador array, Expresion indice) {
        super(fil, col);
        this.array = array;
        this.indice = indice;
        this.kind = KindE.ACCESO_ARRAY;
    }

    public Designador getArray() {
        return array;
    }

    public Expresion getIndice() {
        return indice;
    }

    @Override
    public String toString(String tab) {

        return tab + "ACCESO ARRAY ([])\n" +
                array.toString(tab + "  ") + "\n" +
                indice.toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (array != null) {
            array.chequea(ts);
        }
        if (indice != null) {
            indice.chequea(ts);
        }
    }
}