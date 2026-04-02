package ast;

public class NodoLeer extends Instruccion {

    private String identificador; // La variable donde vamos a leer

    public NodoLeer(int fil, int col, String identificador) {
        super(fil, col);
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String toString(String tab) {
        return tab + "LEER (" + identificador + ")\n";
    }
}