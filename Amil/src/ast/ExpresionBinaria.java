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
    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        this.delta = curr.intValue();
        //curr.addAndGet(opIzq.calcularMem(curr, max)); //curr += Tipos.getTamano(tipo)
        if(curr.get() > max.get()) max.set(curr.get());
    }
}