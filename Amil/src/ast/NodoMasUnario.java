package ast;

public class NodoMasUnario extends ExpresionUnaria {

    public NodoMasUnario(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.MAS_UNARIO;
    }

    @Override
    public String toString(String tab) {
        return tab + "MAS UNARIO (+)\n" + operando().toString(tab + "  ");
    }
}