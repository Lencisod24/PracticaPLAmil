package ast;

public class Asig extends Instruccion {
    private Iden var;
    private E exp;

    public Asig(Iden var, E exp) {
        this.var = var;
        this.exp = exp;
    }

    public Iden getVar() { return var; }
    public E getExp() { return exp; }

    public String toString() {
        return var + " = " + exp;
    }
}