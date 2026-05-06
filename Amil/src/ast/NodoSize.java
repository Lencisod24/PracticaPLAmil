package ast;

import semantico.*;

public class NodoSize extends Expresion {

    private String tipoArg;

    public NodoSize(int fil, int col, String tipoArg) {
        super(fil, col);
        this.tipoArg = tipoArg;
        this.kind = KindE.SIZE;
    }

    @Override
    public String toString(String tab) {
        return tab + "OPERADOR SIZE (Tipo: " + tipoArg + ") \n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Comprobamos que el tipo existe
        if (!ComprobadorTipos.esPresentable(tipoArg)
                && !Tipos.esPuntero(tipoArg)
                && !ts.esStructDefinido(tipoArg)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: No se puede calcular el tamaño de '" + tipoArg + "' porque el tipo no existe.");
            this.setTipo(Tipos.ERROR);
        } else {
            // Resultado siempre es un entero si el tipo es válido
            this.setTipo(Tipos.ENTERO);
        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeExpresion'");
    }
}