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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeExpresion'");
    }
}