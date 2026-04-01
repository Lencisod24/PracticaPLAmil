package ast;

public class NodoImprimir extends Instruccion {
    
    private Expresion expresion;

    public NodoImprimir(int fil, int col, Expresion e) {
        super(fil, col); 
        this.expresion = e;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    @Override
    public String toString(String tab) {
        // Imprime su nombre y luego manda a imprimir la expresión que tiene dentro
        // añadiéndole un poco más de tab
        return tab + "Imprimir\n" + 
               expresion.toString(tab + "  ");
    }
}