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

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        sb.append("  ".repeat(indent))
                .append("i32.const ")
                .append(valor)
                .append("\n");

    }

    @Override
    public int calcularMem() {
        return 0;
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return dirPadre;
    }
}