package ast;

import semantico.TablaSimbolos;

public class NodoMod extends ExpresionBinaria {
    public NodoMod(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MOD; 
    }

    @Override
    public String toString(String tab) {
        return tab + "Módulo (%)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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