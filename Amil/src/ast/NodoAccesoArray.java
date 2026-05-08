package ast;

import java.util.List;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoAccesoArray extends Designador {

    private Designador array;
    private List<Expresion> indices;

    public NodoAccesoArray(int fil, int col, Designador array, List<Expresion> indice) {
        super(fil, col);
        this.array = array;
        this.indices = indice;
        this.kind = KindE.ACCESO_ARRAY;
    }

    public Designador getArray() {
        return array;
    }

    public List<Expresion> getIndices() {
        return indices;
    }

    @Override
    public String toString(String tab) {
        String devolver =tab + "ACCESO ARRAY ([])\n" +
                array.toString(tab + "  ") + "\n";
        String index="";
        for(Expresion e: this.indices){
            index+=e.toString(tab + "  ");
        }
        devolver+=index;
        return devolver;
    }

    public void chequea(TablaSimbolos ts) {
        // Evaluamos los hijos de forma ascendente
        if (array != null)
            array.chequea(ts);
        if (indices != null){

            for(Expresion e : indices){
                e.chequea(ts);
            }
        }
            

        // Extraemos los tipos
        String tipoArray = (array != null && array.getTipo() != null) ? array.getTipo() : Tipos.ERROR;
        
        boolean hayError = false;

        for (Expresion e : indices) {
            String tipoIndice = (e.getTipo() != null) ? e.getTipo() : Tipos.ERROR;

            // Comprobamos que el índice sea entero
            if (!tipoIndice.equals(Tipos.ERROR) && !tipoIndice.equals(Tipos.ENTERO)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: El índice del array debe ser de tipo 'entero', pero se encontró '" + tipoIndice + "'.");
                hayError = true;
            }

        
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
    public void generateCodeDesignador(StringBuilder sb, int indent) {
        String t = " ".repeat(indent);
        
        array.generateCodeDesignador(sb, indent);

        if (indices != null && !indices.isEmpty()) {
            indices.get(0).generateCodeExpresion(sb, indent);

            for (int i = 1; i < indices.size(); i++) {//TODO: AQUI TENGO QUE PREGUNTAR A GONZALO O ALGUIEN A QUÉ SE REFIERE CON DIM EN LAS DIAPOS
                sb.append(t).append("i32.const ").append(getDimension(i)).append("\n");
                sb.append(t).append("i32.mul\n");
                
                indices.get(i).generateCodeExpresion(sb, indent);
                sb.append(t).append("i32.add\n");
            }
        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String t = " ".repeat(indent);
        generateCodeDesignador(sb, indent);
        sb.append(t).append("i32.load\n");
    }

    @Override
    public void generateCodeDesignador(StringBuilder sb, int indent, boolean izquierda) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeDesignador'");
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

    @Override
    public void asignarTamMemTipos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarTamMemTipos'");
    }
}