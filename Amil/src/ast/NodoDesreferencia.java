package ast;

public class NodoDesreferencia extends Designador {

    private Designador operando;

    public NodoDesreferencia(int fil, int col, Designador operando) {
        super(fil, col);
        this.operando = operando;
        this.kind = KindE.DESREFERENCIA;
    }

    public Designador getOperando() {
        return operando;
    }

    @Override
    public String toString(String tab) {

        return tab + "DESREFERENCIA (*)\n" + operando.toString(tab + "  ");
    }

    public void chequeaTipo() {
    }
}