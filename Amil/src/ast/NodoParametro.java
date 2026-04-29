package ast;

import semantico.TablaSimbolos;

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

    @Override
    public void chequea(TablaSimbolos ts) {
        boolean insertado = ts.insertaId(identificador, this);
        if (!insertado) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() + 
                            "]: El parámetro '" + identificador + "' ya ha sido declarado o su nombre choca con otro identificador en este ámbito.");
        }
    }
}