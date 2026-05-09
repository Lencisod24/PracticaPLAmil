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
        String tab = "  ".repeat(indent);
        // Obtenemos el delta de la declaración a la que está vinculada la variable
        int delta = ((NodoDecVariable) this.getVinculo()).getDelta(); // Empujamos la dirección base del marco actual
                                                                      // ($MP)
        sb.append(tab).append("global.get $MP\n");
        // i32.const δ(*id) → empuja el offset
        sb.append(tab).append("i32.const ").append(delta).append("\n");
        // i32.add → dirección final = base + offset
        sb.append(tab).append("i32.add").append("\n");
        // Aparece a la derecha como expresión, así que necesitamos su valor
        String load = this.getTipo().equals(Tipos.REAL) ? "f32.load" : "i32.load";
        if (!izquierda)
            sb.append(tab).append(load).append("\n");
    }

    // Para cuando el identificador se lee como valor (ej: 1 + x)
    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        // Por defecto los designadores como expresión están a la derecha
        generateCodeDesignador(sb, indent, false);
    }

    @Override
    public int asignarDelta(int dirPadre) {
        return dirPadre;
    }
}