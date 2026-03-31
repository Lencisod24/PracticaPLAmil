package ast;

public class Entero extends E {
    private String valorEntero;

    public Entero(String valorEntero) {
        this.valorEntero = valorEntero;
    }

    public KindE kind() {
        return KindE.ENTERO; 
    }
    @Override
    public String num() {
        return valorEntero;
    }
    public String toString() {
        return valorEntero;
    }
}