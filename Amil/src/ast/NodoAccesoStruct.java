package ast;

public class NodoAccesoStruct extends ExpresionBinaria {

    public NodoAccesoStruct(int fil, int col, Designador objeto, Expresion atributo) {
        super(fil, col, objeto, atributo);
        this.kind = KindE.ACCESO_STRUCT; 
    }

    @Override
    public String toString(String tab) {
        return tab + "ACCESO A STRUCT (.)\n" +
                opIzq().toString(tab + "  ") +
                opDer().toString(tab + "  ");
    }
}