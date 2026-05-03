package ast;

import semantico.ComprobadorTipos;
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
    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public String toString(String tab) {
        String ref = porReferencia ? "ref" : "val";
        return tab + "PARAMETRO: (" + ref + ") " + tipo + " " + identificador + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Comprobamos que existe el tipo (los presentables son los primitivos)
        if (!ComprobadorTipos.esTipoValido(tipo, ts)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El tipo '" + tipo + "' no está definido.");
        }

        // Lo añadimos a la tabla aunque el tipo sea inválido para reservar el
        // identificador en el ámbito
        if (!ts.insertaId(identificador, this)) {
            System.err.println("Error Semántico: El identificador '" + identificador + "' ya existe.");
        }
    }
}