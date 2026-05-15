package ast;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import semantico.TablaSimbolos;
import semantico.Tipos;

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

    public int getTamanoTotal() {
        int total = 0;
        for (Declaracion campo : campos)
            total += Tipos.getTamano(campo.getTipo());
        return total;
    }

    public int getOffsetCampo(String nombre) {
        int offset = 0;
        for (Declaracion campo : campos) {
            if (campo.getIdentificador().equals(nombre))
                return offset;
            offset += Tipos.getTamano(campo.getTipo());
        }
        return -1;
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
        Map<String, String> mapaCampos = new LinkedHashMap<>();
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

        Tipos.registrarTamanoStruct(identificador, getTamanoTotal());

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
        int juan = 1000;
        return juan;
    }

}