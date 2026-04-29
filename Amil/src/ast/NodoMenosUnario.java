package ast;

import semantico.TablaSimbolos;

public class NodoMenosUnario extends ExpresionUnaria {

    public NodoMenosUnario(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.MENOS_UNARIO;
    }

    @Override
    public String toString(String tab) {
        return tab + "MENOS UNARIO (-)\n" + operando().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (operando() != null) {
        operando().chequea(ts);
    }   
    }
}