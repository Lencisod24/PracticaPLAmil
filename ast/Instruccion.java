package ast;

public abstract class Instruccion extends ASTNode {
    
    
    public Instruccion(int fil, int col) {
        
        super(fil, col, NodeKind.INSTRUCCION);
    }

    // No hace falta poner el método toString() aquí porque 
    // al ser abstracta, ya obliga a sus hijos a implementarlo.
}