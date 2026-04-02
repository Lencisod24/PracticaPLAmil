package ast;

public class NodoBooleano extends Expresion {

    private String valor;

    public NodoBooleano(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.BOOLEANO;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Booleano: " + valor + "\n";
    }
}