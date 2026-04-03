package ast;

public class NodoCadena extends Expresion {

    private String valor;

    public NodoCadena(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.CADENA;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Cadena: " + valor + "\n";
    }
}