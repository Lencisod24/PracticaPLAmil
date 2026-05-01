package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoBooleano extends Expresion {

    private String valor;

    public NodoBooleano(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.BOOLEANO;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Booleano: " + valor + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        this.setTipo(Tipos.BOOLEANO);
    }
}