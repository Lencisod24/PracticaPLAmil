package ast;

public abstract class Expresion extends ASTNode {
    protected KindE kind; // esto es el tipo de la expresion, por ejemplo 3+4 es SUMA
    private String tipo; // esto es el tipo que devuelve la expresion Ej: 3+4 devuelve int (esto es para
                         // comprobar las cosas más tarde)

    public Expresion(int fil, int col) {
        super(fil, col, NodeKind.EXPRESION);
        this.tipo = null;    //se setea desde cada sitio en el chequea
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipoDato) {
        this.tipo = tipoDato;
    }

    public KindE kind() {
        return kind;
    }
}