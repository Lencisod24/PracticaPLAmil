package ast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoLlamadaFuncion extends Expresion {
    private String id;
    private ArrayList<Expresion> argumentos;

    public NodoLlamadaFuncion(int fil, int col, String id, ArrayList<Expresion> argumentos) {
        super(fil, col);
        this.id = id;
        this.argumentos = argumentos;
    }

    @Override
    public String toString(String tab) {
        String s = tab + "LLAMADA_FUNCION: " + id + " (\n";
        if (argumentos != null) {
            for (Expresion e : argumentos) {
                s += e.toString(tab + "  ");
            }
        }
        s += tab + ")\n";
        return s;
    }

    @Override
    public void chequea(TablaSimbolos ts) {

        if (argumentos != null) {
            for (Expresion arg : argumentos) {
                if (arg != null) {
                    arg.chequea(ts);
                }
            }
        }
        ASTNode def = ts.buscaId(id);
        if (def == null) {
            System.err.println("Error: [" + getFila() + ":" + getColumna() + "]: " + id + " no declarado.");
            this.setTipo(Tipos.ERROR);
        } else if (!(def instanceof NodoFuncion)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El identificador '" + id + "' no corresponde a una función.");
            this.setTipo(Tipos.ERROR);
            return;
        } else {// valida
            NodoFuncion funcion = (NodoFuncion) def;
            int numArgs = (argumentos == null) ? 0 : argumentos.size();
            int numParams = (funcion.getParametros() == null) ? 0 : funcion.getParametros().size();

            // Comprobamos la cantidad de parámetros
            if (numArgs != numParams) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: Número incorrecto de argumentos para la función '" + id +
                        "'. Esperados: " + numParams + ", Dados: " + numArgs);
                this.setTipo(Tipos.ERROR);
            } else {
                boolean err = false;
                // primero comprobamos los argumentos
                for (int i = 0; i < numArgs; i++) {
                    Expresion arg = argumentos.get(i);
                    NodoParametro param = funcion.getParametros().get(i);

                    String tipoArg = (arg != null && arg.getTipo() != null) ? arg.getTipo() : Tipos.ERROR;
                    String tipoParam = param.getTipo();

                    if (!tipoArg.equals(Tipos.ERROR) && !tipoArg.equals(tipoParam)) {
                        System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                                "]: Tipo de argumento incorrecto en la posición " + (i + 1) +
                                " de la función '" + id + "'. Esperado: '" + tipoParam +
                                "', Dado: '" + tipoArg + "'.");
                        err = true;
                    }
                }

                // Asignamos el tipo a la LlamadaFuncion
                if (err) {
                    this.setTipo(Tipos.ERROR);
                } else {
                    this.setTipo(funcion.getTipo());
                }

            }

        }
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
        // Evaluamos todos los argumentos y los empujamos a la pila
        if (argumentos != null) {
            for (Expresion arg : argumentos) {
                if (arg != null) {
                    arg.generateCodeExpresion(sb, indent);
                }
            }
        }

        // Ejecutamos la llamada, que consumirá los argumentos de la pila
        String tab = "\t".repeat(indent);
        sb.append(tab).append("call $").append(id).append("\n");
    }

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        // Propagamos por si los argumentos necesitan memoria
        if (argumentos != null) {
            for (Expresion arg : argumentos) {
                if (arg != null)
                    arg.calcularMem(curr, max);
            }
        }
    }

    @Override
    public int asignarDelta(int dirPadre) {
        // Delegamos el delta a sus argumentos
        if (argumentos != null) {
            for (Expresion arg : argumentos) {
                if (arg != null)
                    arg.asignarDelta(dirPadre);
            }
        }
        return dirPadre;
    }
}