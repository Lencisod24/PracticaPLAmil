package ast;

public class NodoIden extends Designador {

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
        return tab + "Identificador: " + nombre + "\n";
    }

    @Override
    public void chequeaTipo() {
        // 1. Buscar en la Tabla de Símbolos
        // 2. Comprobar si existe y es válido como designador
    }
}