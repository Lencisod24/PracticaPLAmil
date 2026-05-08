package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoNuevo extends Expresion {

    private Expresion expresionTamano;

    public NodoNuevo(int fil, int col, Expresion expresionTamano) {
        super(fil, col);
        this.expresionTamano = expresionTamano;
        this.kind = KindE.NUEVO;
    }

    public Expresion getExpresionTamano() {
        return expresionTamano;
    }

    @Override
    public String toString(String tab) {
        return tab + "RESERVA MEMORIA (nuevo)\n" +
                expresionTamano.toString(tab + "  ");
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (expresionTamano != null) {
            // Evaluamos la expresión ascendentemente
            expresionTamano.chequea(ts);

            // Comprobamos que sea de tipo entero
            if (expresionTamano.getTipo() != null && !expresionTamano.getTipo().equals(Tipos.ENTERO)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: La expresión de tamaño para la reserva de memoria (nuevo) debe ser de tipo entero. Se encontró: "
                        + expresionTamano.getTipo());
                this.setTipo(Tipos.ERROR);
            } else {
                // Devolvemos un puntero genérico al que se asignará un tipo una vez se asigne a
                // una variable
                this.setTipo(Tipos.PUNTERO_NUEVO);
            }
        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeExpresion'");
    }

    @Override
    public int calcularMem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularMem'");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }

    @Override
    public void asignarTamMemTipos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarTamMemTipos'");
    }
}