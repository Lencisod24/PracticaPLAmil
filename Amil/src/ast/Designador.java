package ast;

public abstract class Designador extends Expresion {

    private Declaracion vinculo; // Donde fue declarado el designador

    public Designador(int fil, int col) {
        super(fil, col);
    }

    public void setVinculo(Declaracion dec) {
        this.vinculo = dec;
    }

    public Declaracion getVinculo() {
        return this.vinculo;
    }

    public void generateCodeExpresion(StringBuilder sb, int indent) {

        generateCodeDesignador(sb, indent, false);
        // cuando sb ya tiene la direccion esto coge lo que hay en esa direccion
        sb.append("  ".repeat(indent)).append("i32.load\n");

    }

    public abstract void generateCodeDesignador(StringBuilder sb, int indent, boolean izquierda);
}