package ast;

public abstract class Instruccion extends ASTNode {

    public Instruccion(int fil, int col) {
        super(fil, col, NodeKind.INSTRUCCION);
    }

}