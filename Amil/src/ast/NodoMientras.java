package ast;

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

    // TODO: revisar aqui el indent
    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        // 1. Abrimos el block y el loop
        sb.append("    block\n");
        sb.append("    loop\n");

        // 2. code_E(e): Generamos el código que evalúa la condición
        if (condicion != null) {
            condicion.generateCodeExpresion(sb, indent);
        }

        // Comprobamos si la condición es falsa (0)
        sb.append("    i32.eqz\n");

        // Si es falsa (0), saltamos al final del 'block' (nivel de profundidad 1)
        sb.append("    br_if 1\n");

        if (bloque != null) {
            bloque.generateCodeInstruccion(sb, indent);
        }

        // Volvemos incondicionalmente al inicio del 'loop' (nivel de profundidad 0)
        sb.append("    br 0\n");

        sb.append("    end\n"); // Cierra el loop
        sb.append("    end\n"); // Cierra el block

    }

    @Override
    public int calcularMem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularMem'");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }
}