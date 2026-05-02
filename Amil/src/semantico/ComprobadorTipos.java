package semantico;

import ast.ASTNode;
import ast.NodoDecVariable;
import ast.NodoParametro;

public class ComprobadorTipos {

    // Nos indica si el tipo se puede imprimir, es decir, si es primitivo
    public static boolean esPresentable(String tipo) {
        if (tipo == null || tipo.equals(Tipos.ERROR))
            return false;
        return tipo.equals(Tipos.ENTERO) || tipo.equals(Tipos.REAL) || tipo.equals(Tipos.BOOLEANO)
                || tipo.equals(Tipos.CARACTER)
                || tipo.equals(Tipos.CADENA);
    }

    // Nos indica si dos tipos son iguales para las asignaciones
    public static boolean sonCompatibles(String tipoDestino, String tipoValor) {
        if (tipoDestino == null || tipoValor == null || tipoDestino.equals(Tipos.ERROR)
                || tipoValor.equals(Tipos.ERROR)) {
            return false;
        }

        if (tipoDestino.equals(tipoValor)) {
            return true;
        }

        // Para poder inicializar punteros a null
        if (Tipos.esPuntero(tipoDestino) && tipoValor.equals(Tipos.NULL)) {
            return true;
        }

        // Para poder asignar enteros a reales
        if (tipoDestino.equals(Tipos.REAL) && tipoValor.equals(Tipos.ENTERO)) {
            return true;
        }

        return false;
    }

    // Comprueba que un tipo sea numérico
    public static boolean esNumerico(String tipo) {
        return tipo.equals(Tipos.ENTERO) || tipo.equals(Tipos.REAL);
    }

    // Comprueba si dos tipos son comparables para operaciones de igualdad y
    // relacionales
    public static boolean tiposComparables(String tipo1, String tipo2) {
        if (tipo1 == null || tipo2 == null || tipo1.equals(Tipos.ERROR) || tipo2.equals(Tipos.ERROR)) {
            return true;
        }

        // Si son del mismo tipo o ambos son numéricos
        if (tipo1.equals(tipo2) || ((esNumerico(tipo1)) && (esNumerico(tipo2)))) {
            return true;
        }

        return false;
    }

    // Comprueba si dos operandos son compatibles en una operación aritmética
    public static String inferirTipoAritmetico(String tipo1, String tipo2) {
        if (tipo1 == null || tipo2 == null || tipo1.equals(Tipos.ERROR) || tipo2.equals(Tipos.ERROR)) {
            return Tipos.ERROR;
        }

        // Si los dos son enteros el resultado es entero, y desde que uno sea real el
        // resultado es real
        if (tipo1.equals(Tipos.ENTERO) && tipo2.equals(Tipos.ENTERO)) {
            return Tipos.ENTERO;
        }
        if ((tipo1.equals(Tipos.REAL) || tipo1.equals(Tipos.ENTERO))
                && (tipo2.equals(Tipos.REAL) || tipo2.equals(Tipos.ENTERO))) {
            return Tipos.REAL;
        }

        // Error al intentar sumar tipos no numéricos
        return Tipos.ERROR;
    }

    // Comprueba si un identificador es un array
    public static boolean esArray(String tipo) {
        if (tipo == null)
            return false;
        return Tipos.esArray(tipo);
    }

    // Comprueba si un identificador es un puntero
    public static boolean esPuntero(String tipo) {
        if (tipo == null)
            return false;
        return Tipos.esPuntero(tipo);
    }

    // Obtiene el tipo base de arrays y punteros
    public static String obtenerTipoBase(String tipoCompleto) {
        // si es puntero pasa de "puntero a tipo" a "tipo"
        if (esPuntero(tipoCompleto)) {
            return Tipos.tipoDelPuntero(tipoCompleto);
        }
        // Si es un array pasa de "[N] tipo" a "tipo"
        if (esArray(tipoCompleto)) {
            return Tipos.tipoDelArray(tipoCompleto);
        }
        return tipoCompleto;
    }

    // Comrpueba si un identificador es válido como designador
    public static boolean validoComoDesignador(ASTNode vinculo) {
        // Solo es válido como designador si es una variable o un parámetro
        return (vinculo instanceof NodoDecVariable) || (vinculo instanceof NodoParametro);
    }
}