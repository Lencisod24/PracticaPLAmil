package ast;

public abstract class Declaracion extends ASTNode {

    public Declaracion(int fil, int col) {
        super(fil, col, NodeKind.DECLARACION);
    }

}