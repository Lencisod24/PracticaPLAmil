package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoMientras extends Instruccion {

    private Expresion condicion;
    private Instruccion bloque; // Normalmente es un NodoBloque

    public NodoMientras(int fil, int col, Expresion condicion, Instruccion bloque) {
        super(fil, col);
        this.condicion = condicion;
        this.bloque = bloque;
    }

    public Expresion getCondicion() {
        return condicion;
    }

    public Instruccion getBloque() {
        return bloque;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("MIENTRAS\n");
        sb.append(condicion.toString(tab + "    "));
        sb.append(tab).append("HACER\n");
        sb.append(bloque.toString(tab + "    "));
        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Validamos ascendentemente la condición, que debe ser booleana
        if (condicion != null) {
            condicion.chequea(ts);
            if (condicion.getTipo() != null && !condicion.getTipo().equals(Tipos.BOOLEANO)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: La condición de la instrucción 'SI' debe ser de tipo booleano. Se encontró: " +
                        condicion.getTipo());
            }
        }

        // Validamos el bloque, que si es NodoBloque abrirá y cerrará su propio ámbito
        if (bloque != null) {
            bloque.chequea(ts);
        }
    }
}