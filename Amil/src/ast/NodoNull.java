package ast;

public class NodoNull extends Expresion {

    public NodoNull(int fil, int col) {
        super(fil, col);
        this.kind = KindE.NULL;
    }

    @Override
    public String toString(String tab) {
        return tab + "LITERAL NULL\n";
    }
}