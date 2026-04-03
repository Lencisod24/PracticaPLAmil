package ast;

public abstract class ExpresionUnaria extends Expresion {

    private Expresion operando;

    public ExpresionUnaria(int fil, int col, Expresion operando) {
        super(fil, col);
        this.operando = operando;
    }

    public Expresion operando() {
        return operando;
    }
}