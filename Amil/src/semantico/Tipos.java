package semantico;

import java.util.HashMap;
import java.util.Map;

public class Tipos {
    public static final String ENTERO = "entero";
    public static final String REAL = "real";
    public static final String BOOLEANO = "booleano";
    public static final String CADENA = "cadena";
    public static final String CARACTER = "caracter";
    public static final String VACIO = "vacio";
    public static final String ERROR = "error";
    public static final String NULL = "null";
    public static final String PUNTERO_NUEVO = "puntero_nuevo";

    //registro de los structs
    private static Map<String, Integer> structSizes = new HashMap<>();
    private Tipos() {
         
    }

    public static String tipoDelPuntero(String puntero) {
        return puntero.replaceFirst("puntero a ", "").trim();
    }

    public static String tipoDelArray(String array) {
        return array.substring(array.indexOf("]") + 1).trim();
    }

    public static boolean esArray(String tipo) {
        return tipo.startsWith("[");
    }

    public static boolean esStruct(String tipo){
        return tipo.startsWith("struct");
    }

    public static boolean esPuntero(String tipo) {
        return tipo.startsWith("puntero");
    }

    public static void registrarTamanoStruct(String nombre, int tamano) {
        structSizes.put(nombre, tamano);
    }

    public static int getTamano(String tipo) {
        if (tipo == null || tipo.equals(ERROR) || tipo.equals(VACIO)) {
            return 0;
        }

        // Asumimos 4 bytes para todos los tipos básicos en WASM/32-bit
        // Usaremos i32 y f32 en la generación de código
        if (tipo.equals(ENTERO) || tipo.equals(REAL) || tipo.equals(BOOLEANO) || tipo.equals(CARACTER)) {
            return 4;
        }

        // Los punteros en esta arquitectura son una dirección que siempre ocupa 4 bytes
        if (esPuntero(tipo)) {
            return 4;
        }

        if (esArray(tipo)) {
            try {
                // Extraemos el número entre corchetes
                int inicio = tipo.indexOf("[") + 1;
                int fin = tipo.indexOf("]");
                int numElementos = Integer.parseInt(tipo.substring(inicio, fin));

                // Obtenemos el tipo base (lo que hay después del ']')
                String tipoBase = tipoDelArray(tipo);

                // El tamaño total es: número de elementos * tamaño del tipo base
                return numElementos * getTamano(tipoBase);
            } catch (Exception e) {
                System.err.println("Error al calcular tamaño del array: " + tipo);
                return 0;
            }
        }


        // TODO: structs
        if (structSizes.containsKey(tipo)) return structSizes.get(tipo);
        // Cadenas, habría que revisar cómo las implementamos
        if (tipo.equals(CADENA)) {
            return 4;
        }
        return 0;
    }
}