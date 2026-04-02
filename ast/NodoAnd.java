package ast;

public class NodoAnd extends ExpresionBinaria {
    public NodoAnd(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.AND;
    }

    @Override
    public String toString(String tab) {
        return tab + "AND (&&)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}