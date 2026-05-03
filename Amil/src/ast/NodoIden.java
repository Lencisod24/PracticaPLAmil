package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

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
    public void chequea(TablaSimbolos ts) {
        ASTNode nodo = ts.buscaId(nombre);

        if (nodo == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: '" + nombre + "' no declarado.");
            this.setTipo(Tipos.ERROR);
        } else if (nodo instanceof Declaracion) {
            Declaracion dec = (Declaracion) nodo;
            this.setVinculo(dec);
            this.setTipo(dec.getTipo());
        } else {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: '" + nombre + "' no es una declaración válida.");
            this.setTipo(Tipos.ERROR);
        }
    }
}