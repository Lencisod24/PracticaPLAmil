package ast;

import semantico.TablaSimbolos;

public class NodoDesreferencia extends Designador {

    private Designador operando;

    public NodoDesreferencia(int fil, int col, Designador operando) {
        super(fil, col);
        this.operando = operando;
        this.kind = KindE.DESREFERENCIA;
    }
    
    

    public Designador getOperando() {
        return operando;
    }

    @Override
    public String toString(String tab) {

        return tab + "DESREFERENCIA (*)\n" + operando.toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (operando != null) {
            operando.chequea(ts);
        }
    }

    
}