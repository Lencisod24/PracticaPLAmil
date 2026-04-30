package ast;

import semantico.TablaSimbolos;

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

    public void chequea(TablaSimbolos ts) {
        if (ts.buscaId(nombre) == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El identificador '" + nombre + "' no ha sido declarado.");
        }
    }
}