package ast;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoMayor extends ExpresionBinaria {
    public NodoMayor(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MAYOR;
    }

    @Override
    public String toString(String tab) {
        return tab + "Mayor (>)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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
                    "]: Tipos no comparables con '>'. No se puede comparar '" +
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
        if (opIzq().getTipo().equals(Tipos.ENTERO)) {
            sb.append(tab).append("i32.g_s\n");
        } else if (opIzq().getTipo().equals(Tipos.REAL)) {
            sb.append(tab).append("f64.g\n");
        }
    }

    @Override
    public int calcularMem() {
        return 0; // esto no reserva memoria en si;
    }

    @Override
    public int asignarDelta(int dirPadre) {
        opIzq().asignarDelta(dirPadre);
        opDer().asignarDelta(dirPadre);
        return dirPadre; // Devolvemos la misma dirección porque no hemos consumido nada
    }
}