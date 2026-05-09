package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoOr extends ExpresionBinaria {
    public NodoOr(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.OR;
    }

    @Override
    public String toString(String tab) {
        return tab + "OR (||)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Chequeamos los nodos hijos con evaluación bottom-up
        if (opIzq() != null) {
            opIzq().chequea(ts);
        }
        if (opDer() != null) {
            opDer().chequea(ts);
        }

        // Extraemos los tipos de los operandos
        String tipoIzq = (opIzq() != null && opIzq().getTipo() != null)
                ? opIzq().getTipo()
                : Tipos.ERROR;

        String tipoDer = (opDer() != null && opDer().getTipo() != null)
                ? opDer().getTipo()
                : Tipos.ERROR;

        if (tipoIzq.equals(Tipos.ERROR) || tipoDer.equals(Tipos.ERROR)) {
            this.setTipo(Tipos.ERROR);
            return;
        }

        // Ambos operandos deben ser booleanos
        boolean hayError = false;

        if (!tipoIzq.equals(Tipos.BOOLEANO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Operando izquierdo de '||' inválido. Se esperaba 'booleano', pero se encontró '" + tipoIzq
                    + "'.");
            hayError = true;
        }

        if (!tipoDer.equals(Tipos.BOOLEANO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Operando derecho de '||' inválido. Se esperaba 'booleano', pero se encontró '" + tipoDer
                    + "'.");
            hayError = true;
        }

        // Asignación del tipo resultante a la expresión
        if (hayError) {
            this.setTipo(Tipos.ERROR);
        } else {
            this.setTipo(Tipos.BOOLEANO);
        }
    }

    @Override
    protected String opcodeBooleano() {
        return "i32.or";
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }
}