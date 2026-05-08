package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoMod extends ExpresionBinaria {
    public NodoMod(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.MOD;
    }

    @Override
    public String toString(String tab) {
        return tab + "Módulo (%)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Evaluación ascendente
        if (opIzq() != null)
            opIzq().chequea(ts);
        if (opDer() != null)
            opDer().chequea(ts);

        // Extraemos los tipos
        String tipoIzq = (opIzq() != null && opIzq().getTipo() != null) ? opIzq().getTipo() : Tipos.ERROR;
        String tipoDer = (opDer() != null && opDer().getTipo() != null) ? opDer().getTipo() : Tipos.ERROR;

        if (tipoIzq.equals(Tipos.ERROR) || tipoDer.equals(Tipos.ERROR)) {
            this.setTipo(Tipos.ERROR);
            return;
        }

        boolean hayError = false;

        // Ambos operandos deben ser enteros
        if (!tipoIzq.equals(Tipos.ENTERO) || !tipoDer.equals(Tipos.ENTERO)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El operador módulo ('%') solo admite operandos de tipo 'entero'. Se encontraron '" +
                    tipoIzq + "' y '" + tipoDer + "'.");
            hayError = true;
        }

        // Resultado siempre entero
        if (hayError) {
            this.setTipo(Tipos.ERROR);
        } else {
            this.setTipo(Tipos.ENTERO);
        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        opIzq().generateCodeExpresion(sb, indent);
        opDer().generateCodeExpresion(sb, indent);
        sb.append(tab).append("i32.rem_s\n");
    }

    @Override
    public int calcularMem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularMem'");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }
}