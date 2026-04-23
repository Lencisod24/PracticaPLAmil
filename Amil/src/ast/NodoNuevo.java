package ast;

public class NodoNuevo extends Expresion {

    private Expresion expresionTamano;

    public NodoNuevo(int fil, int col, Expresion expresionTamano) {
        super(fil, col);
        this.expresionTamano = expresionTamano;
        this.kind = KindE.NUEVO;
    }

    public Expresion getExpresionTamano() {
        return expresionTamano;
    }

    @Override
    public String toString(String tab) {
        return tab + "RESERVA MEMORIA (nuevo)\n" +
                expresionTamano.toString(tab + "  ");
    }
}