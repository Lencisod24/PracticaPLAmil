package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

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
        this.setTipo(Tipos.CARACTER);
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        // El carácter viene con comillas simples ej: 'a', sacamos el char del medio
        char c = valor.charAt(1);
        sb.append(tab).append("i32.const ").append((int) c).append("\n");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return dirPadre; // los literales no ocupan espacio en el marco
    }
}