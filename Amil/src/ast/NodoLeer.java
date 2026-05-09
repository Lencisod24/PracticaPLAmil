package ast;

import semantico.TablaSimbolos;

public class NodoLeer extends Instruccion {

    private Designador identificador; // La variable donde vamos a leer

    public NodoLeer(int fil, int col, Designador identificador) {
        super(fil, col);
        this.identificador = identificador;
    }

    public Designador getIdentificador() {
        return identificador;
    }

    @Override
    public String toString(String tab) {
        return tab + "LEER (" + identificador + ")\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        identificador.chequea(ts);
        if (ts.buscaId(identificador.getIden()) == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: La variable '" + identificador + "' no ha sido declarada.");
        }
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        identificador.generateCodeDesignador(sb, indent, true);
        sb.append("call $read\n"); // valor leído en la pila
        sb.append("i32.store\n"); // guarda en x
    }

    @Override
    public int asignarDelta(int dirPadre) {
        int dirLocal = identificador.asignarDelta(dirPadre);
        return dirLocal;
    }
}