package ast;

public class NodoOr extends ExpresionBinaria {
    public NodoOr(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.OR;
    }

    @Override
    public String toString(String tab) {
        return tab + "OR (||)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}