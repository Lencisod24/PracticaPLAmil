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
        // Buscamos el identificador en la pila del ámbito
        ASTNode nodo = ts.buscaId(nombre);

        if (nodo == null) {
            // El identificador no existe
            System.err.println("Error: " + nombre + " no declarado.");
            this.setTipo(Tipos.ERROR);
        } else if (nodo instanceof Declaracion) {
            // Es una declaración válida.
            Declaracion dec = (Declaracion) nodo;
            // El tipo del identificador es el de la declaración donde fue declarado
            this.setVinculo(dec);
            this.setTipo(dec.getTipo());
        } else {
            // El nodo no es una declaración
            System.err.println("Error: '" + nombre + "' no es una declaración válida.");
            this.setTipo(Tipos.ERROR);
        }
    }
}