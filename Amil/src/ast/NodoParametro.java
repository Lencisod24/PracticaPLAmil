package ast;

public class NodoParametro extends Declaracion {

    
    private String tipo;
    private String identificador;
    private boolean porReferencia;

    public NodoParametro(int fil, int col, String tipo, String identificador, boolean porReferencia) {
        super(fil, col);
        this.tipo = tipo;
        this.identificador = identificador;
        this.porReferencia = porReferencia;
    }

    @Override
    public String toString(String tab) {
        String ref = porReferencia ? "ref" : "val";
        return tab + "PARAMETRO: (" + ref + ") " + tipo + " " + identificador + "\n";
    }
}