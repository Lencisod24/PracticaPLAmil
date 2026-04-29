package ast;

import java.util.List;

import semantico.TablaSimbolos;

public class NodoDecVariable extends Declaracion {

    
    private String tipo;
    private String identificador;
    private boolean esConstante;
    private List<Expresion> dimensionesArray; // Será null si no es un array

    public NodoDecVariable(int fil, int col, String tipo, String identificador, boolean esConstante,
            List<Expresion> dimensionesArray) {
        super(fil, col);
        this.tipo = tipo;
        this.identificador = identificador;
        this.esConstante = esConstante;
        this.dimensionesArray = dimensionesArray;
    }

    @Override
    public String toString(String tab) {
        String pre = esConstante ? "CONST " : "";
        String arr = (dimensionesArray != null && !dimensionesArray.isEmpty()) ? "[ARRAY] " : "";
        return tab + "DECLARACION VARIABLE: " + pre + arr + tipo + " " + identificador + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (dimensionesArray != null) {
            for (Expresion dim : dimensionesArray) {
                if (dim != null) {
                    dim.chequea(ts);
                }
            }
        }
        boolean insertado = ts.insertaId(identificador, this);

        if (!insertado) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() + 
                               "]: El identificador '" + identificador + "' ya ha sido declarado en este ámbito.");
        }
    }
}