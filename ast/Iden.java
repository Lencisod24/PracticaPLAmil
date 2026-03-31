package ast;

public class Iden extends E {
    private String nombre;

    public Iden(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public KindE kind() {
        return KindE.IDEN;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}