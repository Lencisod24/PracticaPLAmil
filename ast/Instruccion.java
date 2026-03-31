package ast;

// Implementamos NodeAST para que sea un nodo válido del árbol general
public abstract class Instruccion implements ASTNode {
    
    // Todas las instrucciones devuelven su categoría general
    public NodeKind nodeKind() {
        return NodeKind.INSTRUCCION;
    }
}