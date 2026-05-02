package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoSi extends Instruccion {

    private Expresion condicion;
    private Instruccion bloqueThen; // Normalmente es un NodoBloque
    private Instruccion bloqueElse; // Puede ser null

    // Constructor para el "SI"
    public NodoSi(int fil, int col, Expresion condicion, Instruccion bloqueThen) {
        this(fil, col, condicion, bloqueThen, null);
    }

    // Constructor para el "SI-SINO"
    public NodoSi(int fil, int col, Expresion condicion, Instruccion bloqueThen, Instruccion bloqueElse) {
        super(fil, col);
        this.condicion = condicion;
        this.bloqueThen = bloqueThen;
        this.bloqueElse = bloqueElse;

    }

    public Expresion getCondicion() {
        return condicion;
    }

    public Instruccion getBloqueThen() {
        return bloqueThen;
    }

    public Instruccion getBloqueElse() {
        return bloqueElse;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();

        sb.append(tab).append("SI\n");
        sb.append(condicion.toString(tab + "    "));

        sb.append(tab).append("ENTONCES\n");
        sb.append(bloqueThen.toString(tab + "    "));

        if (bloqueElse != null) {
            sb.append(tab).append("SINO\n");
            sb.append(bloqueElse.toString(tab + "    "));
        }

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

        // Validamos los bloques, que si son NodoBloque abrirán y cerrarán su propio
        // ámbito
        if (bloqueThen != null) {
            bloqueThen.chequea(ts);
        }

        if (bloqueElse != null) {
            bloqueElse.chequea(ts);
        }
    }
}