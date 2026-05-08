package ast;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoSuma extends ExpresionBinaria {

    public NodoSuma(int fil, int col, Expresion opIzq, Expresion opDer) {

        super(fil, col, opIzq, opDer);
        this.kind = KindE.SUMA;
    }

    @Override
    public String toString(String tab) {
        String resultado = tab + "Suma (+)\n";
        String nuevoTab = tab + "  ";

        resultado += opIzq().toString(nuevoTab);
        resultado += opDer().toString(nuevoTab);

        return resultado;
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // 1. Evaluación ascendente
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

        // Ambos deben ser tipos numéricos
        if (!ComprobadorTipos.esNumerico(tipoDer) || !ComprobadorTipos.esNumerico(tipoIzq)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Operación aritmética '+' inválida. Se esperaban tipos numéricos, pero se encontraron '" +
                    tipoIzq + "' y '" + tipoDer + "'.");
            this.setTipo(Tipos.ERROR);
            return;
        }

        // Asignamos el tipo resultante
        this.setTipo(ComprobadorTipos.inferirTipoAritmetico(tipoIzq, tipoDer));
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        String tab = "  ".repeat(indent);
        opIzq().generateCodeExpresion(sb, indent);
        opDer().generateCodeExpresion(sb, indent);
        if (opIzq().getTipo().equals(Tipos.ENTERO)) {
            sb.append(tab).append("i32.add\n");
        } else if (opIzq().getTipo().equals(Tipos.REAL)) {
            sb.append(tab).append("f32.add\n");
        }
    }
    
    
    @Override
    public int asignarDelta(int dirPadre) {
        int dirLocal = opIzq().asignarDelta(dirPadre);
        dirLocal = opDer().asignarDelta(dirLocal);
        return dirLocal;
    }
}