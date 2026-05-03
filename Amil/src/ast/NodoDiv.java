package ast;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoDiv extends ExpresionBinaria {
    public NodoDiv(int fil, int col, Expresion opIzq, Expresion opDer) {
        super(fil, col, opIzq, opDer);
        this.kind = KindE.DIV;
    }

    @Override
    public String toString(String tab) {
        return tab + "División (/)\n" + opIzq().toString(tab + "  ") + opDer().toString(tab + "  ");
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
        if (!ComprobadorTipos.esNumerico(tipoDer) || !ComprobadorTipos.esNumerico(tipoDer)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Operación aritmética '+' inválida. Se esperaban tipos numéricos, pero se encontraron '" +
                    tipoIzq + "' y '" + tipoDer + "'.");
            this.setTipo(Tipos.ERROR);
            return;
        }
        //controlamos la división entre 0;
        
        if(opDer().toString()=="0"){
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Operación aritmética '+' inválida. No se puede dividir entre 0");
            this.setTipo(Tipos.ERROR);
        }

        // Asignamos el tipo resultante
        this.setTipo(ComprobadorTipos.inferirTipoAritmetico(tipoIzq, tipoDer));
    }
}