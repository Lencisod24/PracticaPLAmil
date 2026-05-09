package ast;

import java.util.concurrent.atomic.AtomicInteger;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoMientras extends Instruccion {

    private Expresion condicion;
    private NodoBloque bloque;

    public NodoMientras(int fil, int col, Expresion condicion, NodoBloque bloque) {
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
                        "]: La condición de la instrucción 'MIENTRAS' debe ser de tipo booleano. Se encontró: " +
                        condicion.getTipo());
            }
        }

        // Validamos el bloque, que si es NodoBloque abrirá y cerrará su propio ámbito
        if (bloque != null) {
            bloque.chequea(ts);
        }
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        String t = "  ".repeat(indent);
        sb.append(t).append("block\n"); // abre block
        sb.append(t).append("loop\n"); // abre loop
        if (condicion != null)
            condicion.generateCodeExpresion(sb, indent + 1);
        sb.append(t).append("  i32.eqz\n"); // Comprobamos si la condición es cierta
        sb.append(t).append("  br_if 1\n"); // Entra si es cierta
        if (bloque != null)
            bloque.generateCodeInstruccion(sb, indent + 1);
        sb.append(t).append("  br 0\n");
        sb.append(t).append("end\n"); // cierra loop
        sb.append(t).append("end\n"); // cierra block
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        this.bloque.calcularMem(curr, max);
    }

    @Override
    public int asignarDelta(int dirPadre) {
        bloque.asignarDelta(dirPadre);
        return dirPadre; // el while no ocupa espacio en el marco del padre
    }
}