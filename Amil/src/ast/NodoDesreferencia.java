package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoDesreferencia extends Designador {

    private Designador operando;

    public NodoDesreferencia(int fil, int col, Designador operando) {
        super(fil, col);
        this.operando = operando;
        this.kind = KindE.DESREFERENCIA;
    }

    public Designador getOperando() {
        return operando;
    }

    @Override
    public String toString(String tab) {

        return tab + "DESREFERENCIA (*)\n" + operando.toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (operando != null) {
            operando.chequea(ts);
            String tipoOp = operando.getTipo();
            if (tipoOp == null || tipoOp.equals(Tipos.ERROR)) {
                this.setTipo(Tipos.ERROR);
            } else if (!Tipos.esPuntero(tipoOp)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: Se intentó desreferenciar una variable de tipo '" + tipoOp + "', que no es un puntero.");
                this.setTipo(Tipos.ERROR);
            } else {
                this.setTipo(Tipos.tipoDelPuntero(tipoOp));
            }
        }
    }
}