package ast;

import java.util.concurrent.atomic.AtomicInteger;

import semantico.Tipos;

public abstract class ExpresionBinaria extends Expresion {

    private Expresion opIzq;
    private Expresion opDer;

    public ExpresionBinaria(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col);
        this.opIzq = opIzq;
        this.opDer = opDer;
    }

    public Expresion opIzq() {
        return opIzq;
    }

    public Expresion opDer() {
        return opDer;
    }

    // Funciones implementadas en las clases concretas
    // Devolverán el código correspondiente a la operación
    protected String opcodeEntero() {
        return null;
    }

    protected String opcodeReal() {
        return null;
    }

    protected String opcodeBooleano() {
        return null;
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String t = "  ".repeat(indent);
        opIzq().generateCodeExpresion(sb, indent);
        opDer().generateCodeExpresion(sb, indent);

        String opcode = null;
        if (opIzq().getTipo().equals(Tipos.ENTERO)) {
            opcode = opcodeEntero();
        } else if (opIzq().getTipo().equals(Tipos.REAL)) {
            opcode = opcodeReal();
        } else if (opIzq().getTipo().equals(Tipos.BOOLEANO)) {
            opcode = opcodeBooleano();
        }
        if (opcode != null) {
            sb.append(t).append(opcode).append("\n");
        }
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        this.delta = curr.intValue();
        // curr.addAndGet(opIzq.calcularMem(curr, max)); //curr += Tipos.getTamano(tipo)
        if (curr.get() > max.get())
            max.set(curr.get());
    }
}