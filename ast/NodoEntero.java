package ast;

public class NodoEntero extends Expresion {

    private String valor;

    public NodoEntero(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.ENTERO;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Entero: " + valor + "\n";
    }

}