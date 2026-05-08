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

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        sb.append(tab).append("i32.const ")
                .append(valor.equals("cierto") ? "1" : "0")
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