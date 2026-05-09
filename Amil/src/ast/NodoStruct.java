package ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import semantico.TablaSimbolos;

public class NodoStruct extends Declaracion {

    private String identificador;
    private List<Declaracion> campos;

    public NodoStruct(int fil, int col, String identificador, List<Declaracion> campos) {
        super(fil, col);
        this.identificador = identificador;
        this.campos = campos;
    }

    @Override
    public String getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getTipo() {
        return this.identificador;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("STRUCT ").append(identificador).append(" {\n");

        for (Declaracion d : campos) {
            sb.append(d.toString(tab + "  "));
        }

        sb.append(tab).append("}\n");
        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        // Abrimos un bloque para chequear los campos
        ts.abreBloque();

        // Preparamos el mapa que guardará los campos en la Tabla de Símbolos
        Map<String, String> mapaCampos = new HashMap<>();
        if (campos != null) {
            for (Declaracion campo : campos) {
                if (campo != null) {
                    // Validamos el campo
                    campo.chequea(ts);

                    // Buscamos el campo y su tipo en la tabla de símbolos, y los añadimos al mapa
                    // de campos
                    String nombreCampo = campo.getIdentificador();
                    String tipoCampo = campo.getTipo();
                    mapaCampos.put(nombreCampo, tipoCampo);
                }
            }
        }

        // Cerramos el bloque
        ts.cierraBloque();

        // Guardamos la definición del struct en la tabla de símbolos
        if (!ts.registrarStruct(identificador, mapaCampos)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El struct '" + identificador + "' ya ha sido definido anteriormente.");
        }
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeInstruccion'");
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }
}