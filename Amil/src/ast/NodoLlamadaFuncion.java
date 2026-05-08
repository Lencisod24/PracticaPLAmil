package ast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoLlamadaFuncion extends Expresion {
    private String id;
    private ArrayList<Expresion> argumentos;
    private ASTNode declaracion;

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
            this.declaracion=funcion;

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

                // 3. Asignamos el tipo a la LlamadaFuncion
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

        if (argumentos != null) {
            for (Expresion arg : argumentos) {
                if (arg != null) {
                    arg.generateCodeExpresion(sb, indent);
                }
            }
        }

        for (int i = 0; i < indent; i++)
            sb.append("  ");
        sb.append("call $").append(id).append("\n");
    }

    
    public void calcularMem(AtomicInteger curr, AtomicInteger aux){
       // declaracion.calcularMem(curr,aux);
       //TODO: mr cago en pl;
        
    }
    @Override
    public int asignarDelta(int dirPadre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDelta'");
    }
}