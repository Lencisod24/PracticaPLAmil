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
        String t = " ".repeat(indent);
        // generateCodeDesignador(sb, indent);
        sb.append(t).append("i32.load\n");
    }

    @Override
    public void generateCodeDesignador(StringBuilder sb, int indent, boolean izquierda) {
        // String t = " ".repeat(indent);

        // array.generateCodeDesignador(sb, indent);
        /*
         * if (indices != null && !indices.isEmpty()) {
         * indices.get(0).generateCodeExpresion(sb, indent);
         * 
         * for (int i = 1; i < indices.size(); i++) {// TODO: AQUI TENGO QUE PREGUNTAR A
         * GONZALO O ALGUIEN A QUÉ SE
         * // REFIERE CON DIM EN LAS DIAPOS
         * // sb.append(t).append("i32.const ").append(getDimension(i)).append("\n");
         * sb.append(t).append("i32.mul\n");
         * 
         * indices.get(i).generateCodeExpresion(sb, indent);
         * sb.append(t).append("i32.add\n");
         * }
         * }
         */ // Modificar porque ya no usamos la lista
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