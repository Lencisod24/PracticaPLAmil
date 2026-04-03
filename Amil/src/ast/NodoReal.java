package ast;

public class NodoReal extends Expresion {

    private String valor;

    public NodoReal(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.REAL;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Real: " + valor + "\n";
    }
}
