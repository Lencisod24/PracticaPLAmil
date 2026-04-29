package ast;

import semantico.TablaSimbolos;

public class NodoMenorIgual extends ExpresionBinaria {
    public NodoMenorIgual(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MENORIGUAL;
    }

    @Override
    public String toString(String tab) {
        return tab + "Menor o Igual (<=)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (opIzq() != null) {
            opIzq().chequea(ts);
        }
        if (opDer() != null) {
            opDer().chequea(ts);
        }
    }
}