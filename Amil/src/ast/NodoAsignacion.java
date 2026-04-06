package ast;

public class NodoAsignacion extends Instruccion {

    private Expresion variableDestino; 
    private Expresion expresion;

    public NodoAsignacion(int fila, int columna, Expresion variableDestino, Expresion expresion) {
        super(fila, columna);
        this.variableDestino = variableDestino;
        this.expresion = expresion;
    }

    public Expresion getIdentificador() {
        return variableDestino;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    @Override
    public String toString(String tab) {
        // Imprimimos el nombre de la variable y, justo debajo, la expresión
        return tab + "Asignacion a variable: " + '\n'+variableDestino.toString(tab + "  ") + "\n" +
                expresion.toString(tab + "  ")+ "\n";
    }
}