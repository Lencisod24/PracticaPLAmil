package ast;

public class NodoIden extends Expresion {

    private String nombre;

    public NodoIden(int fil, int col, String nombre) {
        super(fil, col);
        this.nombre = nombre;

        this.kind = KindE.IDEN;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(String tab) {
        return tab + "Identificador: " + nombre + "\n" ;
    }
}