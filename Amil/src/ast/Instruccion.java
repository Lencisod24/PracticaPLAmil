package ast;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Instruccion extends ASTNode {

    public Instruccion(int fil, int col) {
        super(fil, col, NodeKind.INSTRUCCION);
    }

    public abstract void generateCodeInstruccion(StringBuilder sb, int indent);

    public void calcularMem(AtomicInteger curr, AtomicInteger max){
    }
}