package ast;

import semantico.*;

public class NodoSize extends Expresion {

    private String tipoArg;
    private int tamano;

    public NodoSize(int fil, int col, String tipoArg) {
        super(fil, col);
        this.tipoArg = tipoArg;
        this.kind = KindE.SIZE;
        this.tamano = 0;
    }

    @Override
    public String toString(String tab) {
        return tab + "OPERADOR SIZE (Tipo: " + tipoArg + ") \n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Comprobamos que el tipo existe
        if (!ComprobadorTipos.esPresentable(tipoArg)
                && !Tipos.esPuntero(tipoArg)
                && !ts.esStructDefinido(tipoArg)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: No se puede calcular el tamaño de '" + tipoArg + "' porque el tipo no existe.");
            this.setTipo(Tipos.ERROR);
        } else {
            // Resultado siempre es un entero si el tipo es válido
            this.setTipo(Tipos.ENTERO);

            if (ComprobadorTipos.esPresentable(tipoArg) || Tipos.esPuntero(tipoArg)) {
                // Tipos primitivos y punteros ocupan 4 bytes de 32 bits
                this.tamano = 4;
            } else if (ts.esStructDefinido(tipoArg)) {
                // Calculamos el tamaño del struct en la tabla de símbolos
                this.tamano = ts.getTamanoStruct(tipoArg);
            }
        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "\t".repeat(indent);
        sb.append(tab).append("i32.const ").append(this.tamano).append("\n");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return dirPadre;
    }
}