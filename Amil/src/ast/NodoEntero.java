package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoEntero extends Expresion {

    private String valor;

    public NodoEntero(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.ENTERO;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Entero: " + valor + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        this.setTipo(Tipos.ENTERO);
    }

}