package ast;

public abstract class Declaracion extends Instruccion {

    public Declaracion(int fil, int col) {
        super(fil, col);
    }

    public abstract String getIdentificador();

    public abstract String getTipo();
}