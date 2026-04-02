package ast;

public class NodoAccesoClase extends ExpresionBinaria {

    // opIzq será el objeto (ej. "a")
    // opDer será el atributo o método (ej. "longitud")
    public NodoAccesoClase(int fil, int col, Expresion objeto, Expresion atributo) {
        super(fil, col, objeto, atributo);
        this.kind = KindE.ACCESO_CLASE;
    }

    @Override
    public String toString(String tab) {
        return tab + "ACCESO CLASE (.)\n" +
                opIzq().toString(tab + "  ") +
                opDer().toString(tab + "  ");
    }
}