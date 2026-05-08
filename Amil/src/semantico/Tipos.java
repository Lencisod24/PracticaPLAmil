package semantico;

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

    public static boolean esPuntero(String tipo) {
        return tipo.startsWith("puntero");
    }

    public static int getTamano(String tipo) {
        if (tipo == null || tipo.equals(ERROR) || tipo.equals(VACIO)) {
            return 0;
        }

        // 1. Tipos básicos (Asumiendo 4 bytes para casi todos en WASM/32-bit)
        if (tipo.equals(ENTERO) || tipo.equals(REAL) || tipo.equals(BOOLEANO) || tipo.equals(CARACTER)) {
            return 4;
        }

        // 2. Punteros (En una arquitectura de 32 bits, una dirección siempre ocupa 4
        // bytes)
        if (esPuntero(tipo)) {
            return 4;
        }

        // 3. Arrays (Formato esperado: "[10] entero")
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

        // 4. Cadenas (Depende de tu implementación, a veces son punteros a char)
        if (tipo.equals(CADENA)) {
            return 4; // Si se trata como un puntero/referencia
        }

        return 0;
    }
}