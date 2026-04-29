package ast;

import semantico.TablaSimbolos;

public class NodoInstruccionExpresion extends Instruccion {
    
    private Expresion exp;

    public NodoInstruccionExpresion(int fil, int col, Expresion exp) {
        super(fil, col); 
        this.exp = exp;
        
        
    }

    public Expresion expresion() {
        return exp;
    }

    @Override
    public String toString(String tab) {
        return tab + "EVALUAR_EXPRESION\n" + exp.toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (exp != null) {
            exp.chequea(ts);
        }
    }
}