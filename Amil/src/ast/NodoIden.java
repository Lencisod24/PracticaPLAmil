package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoIden extends Designador {

    private String nombre;

    public NodoIden(int fil, int col, String nombre) {
        super(fil, col);
        this.nombre = nombre;

        this.kind = KindE.IDEN;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(String tab) {
        return tab + "Identificador: " + nombre + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        ASTNode nodo = ts.buscaId(nombre);

        if (nodo == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: '" + nombre + "' no declarado.");
            this.setTipo(Tipos.ERROR);
        } else if (nodo instanceof Declaracion) {
            Declaracion dec = (Declaracion) nodo;
            this.setVinculo(dec);
            this.setTipo(dec.getTipo());
        } else {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: '" + nombre + "' no es una declaración válida.");
            this.setTipo(Tipos.ERROR);
        }
    }

    @Override
    public void generateCodeDesignador(StringBuilder sb, int indent, boolean izquierda) {
        this.getVinculo().getTipo();
        int delta = 0; // <-- AQUÍ irá el offset cuando lo tengas

        // Dirección base del marco de memoria (X)
        // TODO: sustituir por la dirección base real si no es 0
        int X = 0;

        // i32.const δ(*id) → empuja el offset
        sb.append(indent).append("i32.const ").append(delta).append("\n");
        // i32.const X → empuja la dirección base del marco
        sb.append(indent).append("i32.const ").append(X).append("\n");
        // i32.add → dirección final = base + offset
        sb.append(indent).append("i32.add").append("\n");
    }

    @Override
    public int calcularMem() {
        return this.tam_mem;
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return this.calcularMem() + dirPadre;
    }

    @Override
    public void asignarTamMemTipos() {

        this.tam_mem = this.getVinculo().tam_mem;
    }
}