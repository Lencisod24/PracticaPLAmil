package ast;

public abstract class Designador extends Expresion {

    private Declaracion vinculo; // Donde fue declarado el designador

    public Designador(int fil, int col) {
        super(fil, col);
    }

    public void setVinculo(Declaracion dec) {
        this.vinculo = dec;
    }

    public Declaracion getVinculo() {
        return this.vinculo;
    }
}