package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoNull extends Expresion {

    public NodoNull(int fil, int col) {
        super(fil, col);
        this.kind = KindE.NULL;
    }

    @Override
    public String toString(String tab) {
        return tab + "LITERAL NULL\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        this.setTipo(Tipos.NULL);
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        sb.append("  ".repeat(indent)).append("i32.const 0\n");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return dirPadre;
    }
}