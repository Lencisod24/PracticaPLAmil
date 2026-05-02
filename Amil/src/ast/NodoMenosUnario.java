package ast;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoMenosUnario extends ExpresionUnaria {

    public NodoMenosUnario(int fil, int col, Expresion operando) {
        super(fil, col, operando);
        this.kind = KindE.MENOS_UNARIO;
    }

    @Override
    public String toString(String tab) {
        return tab + "MENOS UNARIO (-)\n" + operando().toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Evaluación ascendente
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

        // El operando debe ser numérico
        if (!ComprobadorTipos.esNumerico(tipo)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El operador unario '-' solo admite tipos numéricos (entero o real). Se encontró '" + tipo
                    + "'.");

            this.setTipo(Tipos.ERROR);
        } else {
            // El resultado es el tipo original
            this.setTipo(tipo);
        }
    }
}