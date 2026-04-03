package ast;

public class NodoMayor extends ExpresionBinaria {
    public NodoMayor(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MAYOR;
    }

    @Override
    public String toString(String tab) {
        return tab + "Mayor (>)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}