package ast;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoAsignacion extends Instruccion {

    private Designador variableDestino;
    private Expresion expresion;

    public NodoAsignacion(int fila, int columna, Designador variableDestino, Expresion expresion) {
        super(fila, columna);
        this.variableDestino = variableDestino;
        this.expresion = expresion;
    }

    public Expresion getIdentificador() {
        return variableDestino;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    @Override
    public String toString(String tab) {
        return tab + "Asignacion a variable: " + '\n' + variableDestino.toString(tab + "  ") +
                expresion.toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Chequeamos los nodos hijos con evaluación bottom-up
        if (variableDestino != null) {
            variableDestino.chequea(ts);
        }
        if (expresion != null) {
            expresion.chequea(ts);
        }

        // Extraemos los tipos de los hijos para validarlos
        String tipoDestino = (variableDestino != null && variableDestino.getTipo() != null)
                ? variableDestino.getTipo()
                : Tipos.ERROR;

        String tipoExp = (expresion != null && expresion.getTipo() != null)
                ? expresion.getTipo()
                : Tipos.ERROR;

        // Verificamos la compatibilidad solo si ambos hijos son correctos
        if (!tipoDestino.equals(Tipos.ERROR) && !tipoExp.equals(Tipos.ERROR)) {
            if (!ComprobadorTipos.sonCompatibles(tipoDestino, tipoExp)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: Tipos incompatibles en la asignación. No se puede asignar '" + tipoExp
                        + "' a una variable de tipo '" + tipoDestino + "'.");
            }
        }
    }
}