package ast;

import java.util.List;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

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
    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public String toString(String tab) {
        String pre = esConstante ? "CONST " : "";
        String arr = (dimensionesArray != null && !dimensionesArray.isEmpty()) ? "[ARRAY] " : "";
        return tab + "DECLARACION VARIABLE: " + pre + arr + tipo + " " + identificador + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Validamos las expresiones de las dimensiones
        if (dimensionesArray != null) {
            for (Expresion dim : dimensionesArray) {
                if (dim != null) {
                    // Evaluación ascendente del nodo de la dimensión
                    dim.chequea(ts);

                    // Las dimensiones deben ser estrictamente enteras
                    if (dim.getTipo() != null && !dim.getTipo().equals(Tipos.ENTERO)) {
                        System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                                "]: La dimensión del array '" + identificador +
                                "' debe ser de tipo entero. Se encontró: " + dim.getTipo());
                    }
                }
            }
        }

        // Validamos que el tipo base existe
        if (!ComprobadorTipos.esPresentable(tipo) && !ts.esStructDefinido(tipo)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El tipo '" + tipo + "' de la variable '" + identificador +
                    "' no está definido (no es un tipo básico ni un struct conocido).");
        }

        // Insertamos en la tabla de símbolos
        if (!ts.insertaId(identificador, this)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El identificador '" + identificador +
                    "' ya ha sido declarado en este ámbito.");
        }
    }
}