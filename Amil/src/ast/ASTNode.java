package ast;

import semantico.TablaSimbolos;

public abstract class ASTNode {
    // ponemos fila y columna para luego encontrar los errores y el tipo de Nodo
    // para hacer el binding más adelante
    private int fil;
    private int col;
    public int tam_mem;
    public int delta;
    
    private NodeKind tipoNodo;

    public ASTNode(int fil, int col, NodeKind tipoNodo) {
        this.fil = fil;
        this.col = col;
        this.tipoNodo = tipoNodo;
    }

    public int getFila() {
        return fil;
    }

    public int getColumna() {
        return col;
    }

    public NodeKind nodeKind() {
        return tipoNodo;
    }

    public abstract String toString(String tab);
    
    /*public abstract void generate_code_instr(StringBuilder code_builder,int identado); 
    public abstract void generate_code_desig(StringBuilder code_builder,int identado);
    public abstract void generate_code_expr(StringBuilder code_builder,int identado);*/
    public int getDelta(){
        return this.delta;
    }
    public int asignarTamMemMarcos() {
		return 0;
	}
    public abstract int calcularMem();
    public abstract int asignarDelta(int dirPadre);
    public abstract void chequea(TablaSimbolos ts);
    public abstract void  asignarTamMemTipos();
}