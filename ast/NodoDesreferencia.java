package ast;

public class NodoDesreferencia extends ExpresionUnaria {

    public NodoDesreferencia(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.DESREFERENCIA;
    }

    @Override
    public String toString(String tab) {
        return tab + "DESREFERENCIA (*)\n" + operando().toString(tab + "  ");
    }
}