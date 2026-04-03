package ast;

public abstract class Expresion extends ASTNode {
    protected KindE kind; // esto es el tipo de la expresion, por ejemplo 3+4 es SUMA
    private String tipoDato; // esto es el tipo que devuelve la expresion Ej: 3+4 devuelve int (esto es para
                             // comprobar las cosas más tarde)

    public Expresion(int fil, int col) {
        super(fil, col, NodeKind.EXPRESION);
        this.tipoDato = null;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public KindE kind() {
        return kind;
    }
}