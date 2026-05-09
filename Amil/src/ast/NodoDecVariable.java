package ast;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import semantico.ComprobadorTipos;
import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoDecVariable extends Declaracion {

    private String tipo;
    private String identificador;
    private boolean esConstante;
    private List<Expresion> dimensionesArray; // Será null si no es un array
    private Expresion valorInicial; // Será null si no lo hay

    public NodoDecVariable(int fil, int col, String tipo, String identificador, boolean esConstante,
            List<Expresion> dimensionesArray, Expresion valorInicial) {
        super(fil, col);
        this.tipo = tipo;
        this.identificador = identificador;
        this.esConstante = esConstante;
        this.dimensionesArray = dimensionesArray;
        this.valorInicial = valorInicial;
    }

    public boolean esConstante() {
        return this.esConstante;
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getTipo() {
        if (dimensionesArray != null && !dimensionesArray.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int dims = dimensionesArray.size();
            for (int i = 0; i < dims; i++) {
                sb.append("[]");
            }
            sb.append(this.tipo);
            return sb.toString();
        }
        return this.tipo;
    }

    @Override
    public String toString(String tab) {
        String pre = esConstante ? "CONST " : "";
        StringBuilder arr = new StringBuilder();
        if (dimensionesArray != null && !dimensionesArray.isEmpty()) {
            int dims = dimensionesArray.size();
            for (int i = 0; i < dims; i++) {
                arr.append("[ARRAY]");
            }
            arr.append(" ");
        }
        String res = tab + "DECLARACION VARIABLE: " + pre + arr + tipo + " " + identificador;
        if (valorInicial != null) {
            res += " CON VALOR INICIAL:\n" + valorInicial.toString(tab + "  ");
        } else {
            res += " (SIN VALOR INICIAL)\n";
        }
        return res;
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Si es un array, chequeamos que las dimensiones sean enteros
        if (dimensionesArray != null) {
            for (Expresion dim : dimensionesArray) {
                if (dim != null) {
                    dim.chequea(ts);
                    if (dim.getTipo() != null && !dim.getTipo().equals(Tipos.ENTERO)) {
                        System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                                "]: La dimensión debe ser de tipo entero.");
                    }
                }
            }
        }

        // Validamos que el tipo base existe
        if (!ComprobadorTipos.esTipoValido(tipo, ts)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El tipo '" + tipo + "' no está definido.");
        }

        // Validar la inicialización en caso de haberla
        if (valorInicial != null) {
            // Evaluación ascendente del valor inicial
            valorInicial.chequea(ts);

            // Comprobamos compatibilidad de tipos entre la variable y su valor inicial
            if (valorInicial.getTipo() != null && !ComprobadorTipos.sonCompatibles(this.tipo, valorInicial.getTipo())) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: Tipos incompatibles. No se puede inicializar un '" + this.tipo +
                        "' con un valor de tipo '" + valorInicial.getTipo() + "'.");
            }
        } else {
            // Si es constante sin valor inicial, es un error semántico
            if (this.esConstante) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: La constante '" + identificador + "' debe ser inicializada.");
            }
        }

        // Insertamos en la tabla de símbolos
        if (!ts.insertaId(identificador, this)) {
            System.err.println("Error Semántico: '[" + getFila() + ":" + getColumna() + "]: " + identificador
                    + "' ya declarado en este ámbito.");
        }
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        if (valorInicial != null) {
            String t = "  ".repeat(indent);
            sb.append(t).append("global.get $MP\n");
            sb.append(t).append("i32.const ").append(this.delta).append("\n");
            sb.append(t).append("i32.add\n");
            valorInicial.generateCodeExpresion(sb, indent);
            String store = tipo.equals(Tipos.REAL) ? "f32.store" : "i32.store";
            sb.append(t).append(store).append("\n");
        }
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        this.delta = curr.get();
        curr.addAndGet(Tipos.getTamano(tipo)); // curr += Tipos.getTamano(tipo)
        if (curr.get() > max.get())
            max.set(curr.get());
    }

    @Override
    public int asignarDelta(int dirPadre) {
        this.delta = dirPadre;
        return dirPadre + Tipos.getTamano(tipo);
    }
}