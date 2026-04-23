package ast;

public class NodoSize extends Expresion {

    private String tipo;

    public NodoSize(int fil, int col, String tipo) {
        super(fil, col);
        this.tipo = tipo;
        this.kind = KindE.SIZE;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString(String tab) {
        return tab + "OPERADOR SIZE (Tipo: " + tipo + ")\n";
    }
}