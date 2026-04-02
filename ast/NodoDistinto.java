package ast;

public class NodoDistinto extends ExpresionBinaria {
    public NodoDistinto(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.DISTINTO;
    }

    @Override
    public String toString(String tab) {
        return tab + "Distinto (!=)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }
}