package ast;

import semantico.TablaSimbolos;

public class NodoResta extends ExpresionBinaria {
    public NodoResta(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.RESTA;
    }

    @Override
    public String toString(String tab) {
        return tab + "Resta (-)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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