package ast;

public class NodoDireccion extends ExpresionUnaria {

    public NodoDireccion(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.DIRECCION;
    }

    @Override
    public String toString(String tab) {
        return tab + "DIRECCION (&)\n" + operando().toString(tab + "  ");
    }
}