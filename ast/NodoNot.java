package ast;

public class NodoNot extends ExpresionUnaria {

    public NodoNot(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.NOT;
    }

    @Override
    public String toString(String tab) {
        return tab + "NOT (!)\n" + operando().toString(tab + "  ");
    }
}