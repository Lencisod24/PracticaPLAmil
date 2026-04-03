package ast;

public class NodoMenorIgual extends ExpresionBinaria {
    public NodoMenorIgual(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MENORIGUAL;
    }

    @Override
    public String toString(String tab) {
        return tab + "Menor o Igual (<=)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}