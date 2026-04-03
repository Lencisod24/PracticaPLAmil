package ast;

public abstract class ExpresionBinaria extends Expresion {

    private Expresion opIzq;
    private Expresion opDer;

    public ExpresionBinaria(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    public Expresion opIzq() {
        return opIzq;
    }

    public Expresion opDer() {
        return opDer;
    }
}