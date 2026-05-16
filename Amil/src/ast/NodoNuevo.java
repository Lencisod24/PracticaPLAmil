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
        String tab = "  ".repeat(indent);

        // Calcula NP - tamaño
        sb.append(tab).append("global.get $NP\n");
        expresionTamano.generateCodeExpresion(sb, indent);
        sb.append(tab).append("i32.sub\n");
        sb.append(tab).append("global.set $NP\n");

        // Comprobar colisión SP > NP
        sb.append(tab).append("global.get $SP\n");
        sb.append(tab).append("global.get $NP\n");
        sb.append(tab).append("i32.gt_u\n");
        sb.append(tab).append("if\n");
        sb.append(tab).append("  i32.const 3\n");
        sb.append(tab).append("  call $exception\n");
        sb.append(tab).append("end\n");

        // Deja la dirección reservada en la pila
        sb.append(tab).append("global.get $NP\n");
    }

    

    @Override
    public int asignarDelta(int dirPadre) {
        return this.expresionTamano.asignarDelta(dirPadre);
    }
}