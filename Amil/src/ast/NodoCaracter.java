package ast;

import semantico.TablaSimbolos;

public class NodoCaracter extends Expresion {

    private String valor;

    public NodoCaracter(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.CARACTER;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Carácter: " + valor + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
    }
}