package ast;

public class NodoMod extends ExpresionBinaria {
    public NodoMod(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MOD; 
    }

    @Override
    public String toString(String tab) {
        return tab + "Módulo (%)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}