package ast;

public class NodoSi extends Instruccion {

    private Expresion condicion;
    private Instruccion bloqueThen; // Normalmente será un NodoBloque
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

    public Expresion getCondicion() {
        return condicion;
    }

    public Instruccion getBloqueThen() {
        return bloqueThen;
    }

    public Instruccion getBloqueElse() {
        return bloqueElse;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(tab).append("SI\n");
        sb.append(condicion.toString(tab + "    "));
        
        sb.append(tab).append("ENTONCES\n");
        sb.append(bloqueThen.toString(tab + "    "));
        
        if (bloqueElse != null) {
            sb.append(tab).append("SINO\n");
            sb.append(bloqueElse.toString(tab + "    "));
        }
        
        return sb.toString();
    }
}