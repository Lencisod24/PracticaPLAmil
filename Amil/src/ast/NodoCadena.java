package ast;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoCadena extends Expresion {

    private String valor;

    public NodoCadena(int fil, int col, String valor) {
        super(fil, col);
        this.valor = valor;
        this.kind = KindE.CADENA;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(String tab) {
        return tab + "Cadena: " + valor + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        this.setTipo(Tipos.CADENA);
    }

    // TODO: aquí hay que revisar esto también, por lo visto hay que hacer algo de
    // memoria
    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        // Las cadenas se almacenan en memoria y se deja su dirección en la pila
        // Esto se completará cuando implementes la sección de datos del módulo wasm
        String tab = "  ".repeat(indent);
        sb.append(tab).append(";; cadena: ").append(valor).append("\n");
        sb.append(tab).append("i32.const 0 ;; TODO: dirección de la cadena en memoria\n");
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