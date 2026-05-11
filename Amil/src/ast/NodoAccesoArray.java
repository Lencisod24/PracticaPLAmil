package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoAccesoArray extends Designador {

    private Designador array;
    private Expresion indice;

    public NodoAccesoArray(int fil, int col, Designador array, Expresion indice) {
        super(fil, col);
        this.array = array;
        this.indice = indice;
        this.kind = KindE.ACCESO_ARRAY;
    }

    public Designador getArray() {
        return array;
    }

    public Expresion getIndice() {
        return indice;
    }

    @Override
    public String toString(String tab) {
        return tab + "ACCESO ARRAY ([])\n" + array.toString(tab + "  ") + indice.toString(tab + "  ");
    }

    public void chequea(TablaSimbolos ts) {
        // Evaluamos los hijos de forma ascendente
        if (array != null)
            array.chequea(ts);
        if (indice != null)
            indice.chequea(ts);

        // Extraemos los tipos
        String tipoArray = (array != null && array.getTipo() != null) ? array.getTipo() : Tipos.ERROR;
        String tipoIndice = (indice != null && indice.getTipo() != null) ? indice.getTipo() : Tipos.ERROR;
        boolean hayError = false;

        // Comprobamos que el índice sea entero
        if (!tipoIndice.equals(Tipos.ERROR) && !tipoIndice.equals(Tipos.ENTERO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El índice del array debe ser de tipo 'entero', pero se encontró '" + tipoIndice + "'.");
            hayError = true;
        }

        // Comprobamos que el designador corresponda a un array
        if (!tipoArray.equals(Tipos.ERROR)) {
            if (!Tipos.esArray(tipoArray)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: Se intentó indexar un elemento de tipo '" + tipoArray + "', no un array.");
                hayError = true;
                tipoArray = Tipos.ERROR;
            } else {
                tipoArray = Tipos.tipoDelArray(tipoArray);
            }
        } else {
            hayError = true;
        }

        // Asignamos el tipo resultante a este nodo
        if (hayError) {
            this.setTipo(Tipos.ERROR);
        } else {
            this.setTipo(tipoArray);
        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        generateCodeDesignador(sb, indent, false);
    }

    @Override
    public void generateCodeDesignador(StringBuilder sb, int indent, boolean izquierda) {
        String t = "  ".repeat(indent);

        array.generateCodeDesignador(sb, indent, true);

        indice.generateCodeExpresion(sb, indent);

        int elementSize = Tipos.getTamano(this.getTipo());
        sb.append(t).append("i32.const ").append(elementSize).append("\n");

        sb.append(t).append("i32.mul\n");

        sb.append(t).append("i32.add\n");

        if (!izquierda) {
            String load = this.getTipo().equals(Tipos.REAL) ? "f32.load" : "i32.load";
            sb.append(t).append(load).append("\n");
        }
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return 0;
    }
}