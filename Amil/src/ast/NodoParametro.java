package ast;

import java.util.concurrent.atomic.AtomicInteger;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

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
            System.err.println("Error Semántico: El identificador '[" + getFila() + ":" + getColumna() + "]: "
                    + identificador + "' ya existe.");
        }
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        this.delta = curr.get();
        if (!porReferencia) {
            curr.addAndGet(Tipos.getTamano(this.tipo)); // ocupa el tamaño de su tipo
        } else {
            curr.addAndGet(4); // siempre 4 bytes, es solo una dirección
        }
        if (curr.get() > max.get())
            max.set(curr.get());
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        // No genera código, su valor se define en la llamada a la función
    }

    @Override
    public int asignarDelta(int dirPadre) {
        this.delta = dirPadre;
        if (!porReferencia) {
            return dirPadre + Tipos.getTamano(tipo);
        } else {
            return dirPadre + 4; // solo una dirección
        }
    }
}