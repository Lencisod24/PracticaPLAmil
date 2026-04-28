package ast;

public abstract class Designador extends Expresion {

    protected String tipoInferido;

    public Designador(int fil, int col) {
        super(fil, col);
    }

    public String gettipoInferido() {
        return tipoInferido;
    }

    public void settipoInferido(String tipoInferido) {
        this.tipoInferido = tipoInferido;
    }
}