package ast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import semantico.TablaSimbolos;
import semantico.Tipos;

public class NodoLlamadaFuncion extends Expresion {
    private String id;
    private ArrayList<Expresion> argumentos;
    private NodoFuncion funcion;

    public NodoLlamadaFuncion(int fil, int col, String id, ArrayList<Expresion> argumentos) {
        super(fil, col);
        this.id = id;
        this.argumentos = argumentos;
    }

    @Override
    public String toString(String tab) {
        String s = tab + "LLAMADA_FUNCION: " + id + " (\n";
        if (argumentos != null)
            for (Expresion e : argumentos)
                s += e.toString(tab + "  ");
        s += tab + ")\n";
        return s;
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        if (argumentos != null)
            for (Expresion arg : argumentos)
                if (arg != null)
                    arg.chequea(ts);

        ASTNode def = ts.buscaId(id);
        if (def == null) {
            System.err.println("Error: [" + getFila() + ":" + getColumna() + "]: " + id + " no declarado.");
            this.setTipo(Tipos.ERROR);
            return;
        }
        if (!(def instanceof NodoFuncion)) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El identificador '" + id + "' no corresponde a una función.");
            this.setTipo(Tipos.ERROR);
            return;
        }

        NodoFuncion funcion = (NodoFuncion) def;
        this.funcion=funcion;
        int numArgs = argumentos == null ? 0 : argumentos.size();
        int numParams = funcion.getParametros() == null ? 0 : funcion.getParametros().size();

        if (numArgs != numParams) {
            System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: Número incorrecto de argumentos para '" + id +
                    "'. Esperados: " + numParams + ", Dados: " + numArgs);
            this.setTipo(Tipos.ERROR);
            return;
        }

        boolean err = false;
        for (int i = 0; i < numArgs; i++) {
            Expresion arg = argumentos.get(i);
            NodoParametro param = funcion.getParametros().get(i);
            String tipoArg = arg != null && arg.getTipo() != null ? arg.getTipo() : Tipos.ERROR;
            String tipoParam = param.getTipo();

            if (!tipoArg.equals(Tipos.ERROR) && !tipoArg.equals(tipoParam)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                        "]: Tipo incorrecto en argumento " + (i + 1) + " de '" + id +
                        "'. Esperado: '" + tipoParam + "', Dado: '" + tipoArg + "'.");
                err = true;
            }
        }
        // validación por referencia: el argumento debe ser una variable
        for (int i = 0; i < numArgs; i++) {
            NodoParametro param = funcion.getParametros().get(i);
            Expresion arg = argumentos.get(i);

            if (param.isPorReferencia() && !(arg instanceof NodoIden)) {
                System.err.println("Error Semántico [" + getFila() + ":" + getColumna() +
                    "]: El argumento " + (i+1) + " de '" + id +
                    "' debe ser una variable (parámetro por referencia).");
                err = true;
            }
        }

        this.setTipo(err ? Tipos.ERROR : funcion.getTipo());
    }

    @Override
    public void generateCodeExpresion(StringBuilder sb, int indent) {
         String tab = "  ".repeat(indent);

    // Necesitas acceder a la definición — guarda la referencia en chequea()
    if (argumentos != null && funcion != null) {
        for (int i = 0; i < argumentos.size(); i++) {
            Expresion arg = argumentos.get(i);
            NodoParametro param = funcion.getParametros().get(i);

            if (param.isPorReferencia()) {
                // Pasar la DIRECCIÓN del argumento (debe ser una variable)
                if (arg instanceof NodoIden) {
                    NodoIden nv = (NodoIden) arg;
                    nv.generateCodeDesignador(sb, indent, true);//genera el código de la dirección de la variable;
                } else {
                    // Error: solo se puede pasar por referencia una variable
                    sb.append(tab).append(";; ERROR: argumento por referencia no es variable\n");
                }
            } else {
                // Pasar el VALOR normalmente
                arg.generateCodeExpresion(sb, indent);
            }
        }
    }

    sb.append(tab).append("call $").append(id).append("\n");
}

    @Override
    public void calcularMem(AtomicInteger curr, AtomicInteger max) {
        if (argumentos != null)
            for (Expresion arg : argumentos)
                if (arg != null)
                    arg.calcularMem(curr, max);
    }

    @Override
    public int asignarDelta(int dirPadre) {
        if (argumentos != null)
            for (Expresion arg : argumentos)
                if (arg != null)
                    arg.asignarDelta(dirPadre);
        return dirPadre;
    }
}