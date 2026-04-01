package ast;

public class NodoAsignacion extends Instruccion {
    
    private String identificador;
    private Expresion expresion;

    public NodoAsignacion(int fil, int col, String identificador, Expresion expresion) {
        super(fil, col);
        this.identificador = identificador;
        this.expresion = expresion;
        
        
        
    }

    public String getIdentificador() { return identificador; }
    public Expresion getExpresion() { return expresion; }

    @Override
    public String toString(String tab) {
        // Imprimimos el nombre de la variable y, justo debajo, la expresión
        return tab + "Asignacion a variable: " + identificador + "\n" + 
               expresion.toString(tab + "  ");
    }
}