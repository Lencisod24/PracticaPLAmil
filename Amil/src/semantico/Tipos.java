package semantico;

public class Tipos {
    public static final String ENTERO = "entero";
    public static final String REAL = "real";
    public static final String BOOLEANO = "booleano";
    public static final String CADENA = "cadena";
    public static final String CARACTER = "caracter";
    public static final String VACIO = "vacio";
    public static final String ERROR = "error";

    private Tipos() {
    }

    public static String tipoDelPuntero(String puntero) {
        return puntero.replace("puntero a ", "").trim();
    }

    public static String tipoDelArray(String array) {
        return array.substring(array.lastIndexOf("]") + 1).trim();
    }

    public static boolean esArray(String tipo) {
        return tipo.startsWith("[");
    }

    public static boolean esPuntero(String tipo) {
        return tipo.startsWith("puntero");
    }
}