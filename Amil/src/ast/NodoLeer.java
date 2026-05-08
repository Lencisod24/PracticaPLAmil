package ast;

import semantico.TablaSimbolos;

public class NodoLeer extends Instruccion {

    private String identificador; // La variable donde vamos a leer

    public NodoLeer(int fil, int col, String identificador) {
        super(fil, col);
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String toString(String tab) {
        return tab + "LEER (" + identificador + ")\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (ts.buscaId(identificador) == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: La variable '" + identificador + "' no ha sido declarada.");
        }
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeInstruccion'");
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
}