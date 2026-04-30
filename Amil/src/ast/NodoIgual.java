package ast;

import semantico.TablaSimbolos;
import semantico.ComprobadorTipos;

public class NodoIgual extends ExpresionBinaria {
    public NodoIgual(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.IGUAL;
    }

    @Override
    public String toString(String tab) {
        return tab + "Igualdad (==)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        opIzq().chequea(ts);
        opDer().chequea(ts);

        if (opIzq().getTipo() == null || opDer().getTipo() == null ||
                opIzq().getTipo().equals("error") || opDer().getTipo().equals("error")) {
            this.setTipo("error");
            return;
        }

        if (ComprobadorTipos.tiposComparables(opIzq().getTipo(), opDer().getTipo())) {
            this.setTipo("booleano");
        } else {
            System.err.println("ERROR SEMÁNTICO (Fila " + this.getFila() + "): Tipos no comparables. " +
                    "No se puede comparar '" + opIzq().getTipo() + "' con '" + opDer().getTipo() + "'.");
            this.setTipo("error");
        }
    }
}
