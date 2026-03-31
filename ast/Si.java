package ast;

public class Si extends Instruccion {
    private E condicion; // La expresión a evaluar 
    private Instruccion cuerpoVerdadero; // Lo que hace si es CIERTO (el ENTONCES)
    private Instruccion cuerpoFalso; // Lo que hace si es FALSO (el SINO)

    // Constructor para un SI con SINO
    public Si(E condicion, Instruccion cuerpoVerdadero, Instruccion cuerpoFalso) {
        this.condicion = condicion;
        this.cuerpoVerdadero = cuerpoVerdadero;
        this.cuerpoFalso = cuerpoFalso;
    }

    // Constructor para un SI simple 
    public Si(E condicion, Instruccion cuerpoVerdadero) {
        this.condicion = condicion;
        this.cuerpoVerdadero = cuerpoVerdadero;
        this.cuerpoFalso = null; 
    }

    public E getCondicion() {
        return condicion;
    }

    public Instruccion getCuerpoVerdadero() {
        return cuerpoVerdadero;
    }

    public Instruccion getCuerpoFalso() {
        return cuerpoFalso;
    }

    public String toString() {
        return "SI(" + condicion + ") ENTONCES {" + cuerpoVerdadero + "} SINO {" + cuerpoFalso + "}";
    }
}