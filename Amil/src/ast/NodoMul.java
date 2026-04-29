package ast;

import semantico.TablaSimbolos;

public class NodoMul extends ExpresionBinaria {
    public NodoMul(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MUL;
    }

    @Override
    public String toString(String tab) {
        return tab + "Multiplicacion (*)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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