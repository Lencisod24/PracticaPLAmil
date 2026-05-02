package ast;

import semantico.*;

public class NodoSize extends Expresion {

    private String tipo;

    public NodoSize(int fil, int col, String tipo) {
        super(fil, col);
        this.tipo = tipo;
        this.kind = KindE.SIZE;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString(String tab) {
        return tab + "OPERADOR SIZE (Tipo: " + tipo + ")\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Comprobamos que el tipo existe
        if (!ComprobadorTipos.esPresentable(tipo) && !ts.esStructDefinido(tipo)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: No se puede calcular el tamaño de '" + tipo + "' porque el tipo no existe.");
            this.setTipo(Tipos.ERROR);
        } else {
            // Resultado siempre es un entero si el tipo es válido
            this.setTipo(Tipos.ENTERO);
        }
    }
}