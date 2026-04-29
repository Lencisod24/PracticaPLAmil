package ast;

import semantico.TablaSimbolos;

public class NodoMayorIgual extends ExpresionBinaria {
    public NodoMayorIgual(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MAYORIGUAL;
    }

    @Override
    public String toString(String tab) {
        return tab + "Mayor o Igual (>=)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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