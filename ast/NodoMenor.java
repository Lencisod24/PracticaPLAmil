package ast;

public class NodoMenor extends ExpresionBinaria {
    public NodoMenor(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MENOR;
    }

    @Override
    public String toString(String tab) {
        return tab + "Menor (<)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}