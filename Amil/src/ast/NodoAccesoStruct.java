package ast;

import java.util.Map;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoAccesoStruct extends Designador {

    private Designador variableStruct;
    private String campo;
    private int offsetCampo;

    public NodoAccesoStruct(int fil, int col, Designador variableStruct, String campo) {
        super(fil, col);
        this.variableStruct = variableStruct;
        this.campo = campo;

        this.kind = KindE.ACCESO_STRUCT;
    }

    public Designador getVariableStruct() {
        return variableStruct;
    }

    public String getCampo() {
        return campo;
    }

    @Override
    public String toString(String tab) {
        return tab + "ACCESO A STRUCT (.)\n" +
                variableStruct.toString(tab + "  ") +
                tab + "  Campo: " + campo + "\n";
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Chequeamos el designador con evaluación bottom-up
        if (variableStruct != null) {
            variableStruct.chequea(ts);
        }

        // Extraemos su tipo
        String tipoVar = (variableStruct != null && variableStruct.getTipo() != null)
                ? variableStruct.getTipo()
                : Tipos.ERROR;

        if (tipoVar.equals(Tipos.ERROR)) {
            this.setTipo(Tipos.ERROR);
            return;
        }

        // Comprobamos que sea un struct
        if (!ts.esStructDefinido(tipoVar)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Se intentó acceder al campo '" + campo + "' en una variable de tipo '" +
                    tipoVar + "', que no es un struct.");
            this.setTipo(Tipos.ERROR);
            return;
        }

        // Comprobamos que el struct tenga ese campo
        String tipoCampo = ts.getTipoCampoDeStruct(tipoVar, campo);

        if (tipoCampo == null) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El struct '" + tipoVar + "' no contiene el campo '" + campo + "'.");
            this.setTipo(Tipos.ERROR);
            return;
        }

        int offset = 0;
        for (Map.Entry<String, String> entry : ts.getCamposDeStruct(tipoVar).entrySet()) {
            if (entry.getKey().equals(campo)) break;
            offset += Tipos.getTamano(entry.getValue());
        }
        this.offsetCampo = offset;
        // Asignamos     al nodo el tipo del campo
        this.setTipo(tipoCampo);
    }
    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        generateCodeDesignador(sb, indent, false);
    }

    @Override
    public void generateCodeDesignador(StringBuilder sb, int indent, boolean izquierda) {
        String t = "  ".repeat(indent);
        variableStruct.generateCodeDesignador(sb, indent, true);
        sb.append(t).append("i32.const ").append(offsetCampo).append("\n");
        sb.append(t).append("i32.add\n");
        if (!izquierda) {
            String load = getTipo().equals(Tipos.REAL) ? "f32.load" : "i32.load";
            sb.append(t).append(load).append("\n");
        }
    }


    @Override
    public int asignarDelta(int dirPadre) {
        int juan = 1000;
        return juan;
    }
}