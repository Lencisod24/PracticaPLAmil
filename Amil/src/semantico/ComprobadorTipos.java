package semantico;

import ast.ASTNode;
import ast.NodoDecVariable;
import ast.NodoParametro;

public class ComprobadorTipos {

    // Nos indica si el tipo se puede imprimir, es decir, si es primitivo
    public static boolean esPresentable(String tipo) {
        if (tipo == null || tipo.equals("error"))
            return false;
        return tipo.equals("entero") || tipo.equals("real") || tipo.equals("booleano") || tipo.equals("caracter")
                || tipo.equals("cadena");
    }

    // Nos indica si dos tipos son iguales para las asignaciones
    public static boolean sonCompatibles(String tipoDestino, String tipoValor) {
        if (tipoDestino == null || tipoValor == null || tipoDestino.equals("error") || tipoValor.equals("error")) {
            return false;
        }

        if (tipoDestino.equals(tipoValor)) {
            return true;
        }
        // Para poder asignar enteros a reales
        if (tipoDestino.equals("real") && tipoValor.equals("entero")) {
            return true;
        }
        return false;
    }

    // Comprueba si dos tipos son comparables para operaciones de igualdad y
    // relacionales
    public static boolean tiposComparables(String tipo1, String tipo2) {
        if (tipo1 == null || tipo2 == null || tipo1.equals("error") || tipo2.equals("error")) {
            return true;
        }

        // Si son del mismo tipo o ambos son numéricos
        if (tipo1.equals(tipo2) || ((tipo1.equals("entero") || tipo1.equals("real"))
                && (tipo2.equals("entero") || tipo2.equals("real")))) {
            return true;
        }

        return false;
    }

    // Comprueba si dos operandos son compatibles en una operación aritmética
    public static String inferirTipoAritmetico(String tipo1, String tipo2) {
        if (tipo1 == null || tipo2 == null || tipo1.equals("error") || tipo2.equals("error")) {
            return "error";
        }

        // Si los dos son enteros el resultado es entero, y desde que uno sea real el
        // resultado es real
        if (tipo1.equals("entero") && tipo2.equals("entero")) {
            return "entero";
        }
        if ((tipo1.equals("real") || tipo1.equals("entero")) && (tipo2.equals("real") || tipo2.equals("entero"))) {
            return "real";
        }

        // Error al intentar sumar tipos no numéricos
        return "error";
    }

    // Comprueba si un identificador es un array
    public static boolean esArray(String tipo) {
        if (tipo == null)
            return false;
        return tipo.startsWith("[");
    }

    // Comprueba si un identificador es un puntero
    public static boolean esPuntero(String tipo) {
        if (tipo == null)
            return false;
        return tipo.startsWith("puntero");
    }

    // Obtiene el tipo base de arrays y punteros
    public static String obtenerTipoBase(String tipoCompleto) {
        // si es puntero pasa de "puntero a tipo" a "tipo"
        if (esPuntero(tipoCompleto)) {
            return tipoCompleto.replace("puntero a ", "").trim();
        }
        // Si es un array pasa de "[N] tipo" a "tipo"
        if (esArray(tipoCompleto)) {
            return tipoCompleto.substring(tipoCompleto.lastIndexOf("]") + 1).trim();
        }
        return tipoCompleto;
    }

    // Comrpueba si un identificador es válido como designador
    public static boolean validoComoDesignador(ASTNode vinculo) {
        // Solo es válido como designador si es una variable o un parámetro
        return (vinculo instanceof NodoDecVariable) || (vinculo instanceof NodoParametro);
    }
}