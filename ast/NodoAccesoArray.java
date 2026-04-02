package ast;

public class NodoAccesoArray extends ExpresionBinaria {

    // opIzq será el array (ej. "lista")
    // opDer será el índice (ej. "0" o "i+1")
    public NodoAccesoArray(int fil, int col, Expresion array, Expresion indice) {
        super(fil, col, array, indice);
        this.kind = KindE.ACCESO_ARRAY;
    }

    @Override
    public String toString(String tab) {
        return tab + "ACCESO ARRAY ([])\n" +
                opIzq().toString(tab + "  ") +
                opDer().toString(tab + "  ");
    }
}