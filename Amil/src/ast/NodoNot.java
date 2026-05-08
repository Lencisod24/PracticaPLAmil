package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoNot extends ExpresionUnaria {

    public NodoNot(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.NOT;
    }

    @Override
    public String toString(String tab) {
        return tab + "NOT (!)\n" + operando().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Chequeamos el nodo hijo con evaluación bottom-up
        if (operando() != null) {
            operando().chequea(ts);
        }

        // Extraemos el tipo del operando
        String tipo = (operando() != null && operando().getTipo() != null)
                ? operando().getTipo()
                : Tipos.ERROR;

        if (tipo.equals(Tipos.ERROR)) {
            this.setTipo(Tipos.ERROR);
            return;
        }

        // El operando debe ser booleano
        boolean hayError = false;

        if (!tipo.equals(Tipos.BOOLEANO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Operando de '!' inválido. Se esperaba 'booleano', pero se encontró '" + tipo
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
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        operando().generateCodeExpresion(sb, indent);
        sb.append(tab).append("i32.eqz\n");
    }

    

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }
}