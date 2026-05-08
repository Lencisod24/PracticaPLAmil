package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoReal extends Expresion {

    private String valor;

    public NodoReal(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.REAL;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Real: " + valor + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        this.setTipo(Tipos.REAL);
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        sb.append("  ".repeat(indent))
                .append("f64.const ")
                .append(valor)
                .append("\n");
    }

    @Override
    public int calcularMem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularMem'");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }

    @Override
    public void asignarTamMemTipos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarTamMemTipos'");
    }
}
