package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;
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
        // Evaluamos los hijos con bottom-up
        if (opIzq() != null) {
            opIzq().chequea(ts);
        }
        if (opDer() != null) {
            opDer().chequea(ts);
        }

        // Extraemos los tipos
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

        // Vemos si son comparables
        if (!ComprobadorTipos.tiposComparables(tipoIzq, tipoDer)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Tipos no comparables con '=='. No se puede comparar '" +
                    tipoIzq + "' con '" + tipoDer + "'.");
            this.setTipo(Tipos.ERROR);
        } else {
            this.setTipo(Tipos.BOOLEANO);
        }
    }
    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        opIzq().generateCodeExpresion(sb, indent);
        opDer().generateCodeExpresion(sb, indent);
        if (opIzq().getTipo().equals(Tipos.REAL)) {
            sb.append(tab).append("f64.eq\n");
        } else {
            sb.append(tab).append("i32.eq\n");
        }
    }
}
