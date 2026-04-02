package ast;

public class NodoDiv extends ExpresionBinaria {
    public NodoDiv(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.DIV;
    }

    @Override
    public String toString(String tab) {
        return tab + "División (/)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}