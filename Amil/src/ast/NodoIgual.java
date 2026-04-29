package ast;

import semantico.TablaSimbolos;

public class NodoIgual extends ExpresionBinaria {
    public NodoIgual(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.IGUAL;
    }

    @Override
    public String toString(String tab) {
        return tab + "Igualdad (==)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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