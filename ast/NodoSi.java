package ast;

public class NodoSi extends Instruccion {
    
    private Expresion condicion;
    private Instruccion bloqueThen;
    private Instruccion bloqueElse; // Puede ser null

    // Constructor para el "SI" (sin sino)
    public NodoSi(int fil, int col, Expresion condicion, Instruccion bloqueThen) {
        this(fil, col, condicion, bloqueThen, null); 
        
    }

    // Constructor para el "SI-SINO" 
    public NodoSi(int fil, int col, Expresion condicion, Instruccion bloqueThen, Instruccion bloqueElse) {
        super(fil, col);
        this.condicion = condicion;
        this.bloqueThen = bloqueThen;
        this.bloqueElse = bloqueElse;
        
    }

    public Expresion getCondicion() { return condicion; }
    public Instruccion getBloqueThen() { return bloqueThen; }
    public Instruccion getBloqueElse() { return bloqueElse; }

    @Override
    public String toString(String tab) {
        String res = tab + "SI (" + condicion.toString("") + ") {\n" + 
                     bloqueThen.toString(tab + "  ") + tab + "}";
        
        if (bloqueElse != null) {
            res += "\n" + tab + "SINO {\n" + 
                   bloqueElse.toString(tab + "  ") + tab + "}";
        }
        
        return res + "\n";
    }
}