package ast;

import java.util.List;


public class NodoPrograma extends ASTNode {

    private List<Declaracion> declaracionesGlobales;
    private List<Declaracion> funcionesYClases;
    private NodoBloque bloquePrincipal;

    public NodoPrograma(int fil, int col, List<Declaracion> declaracionesGlobales, List<Declaracion> funcionesYClases,
            NodoBloque bloquePrincipal) {
        super(fil, col, NodeKind.PROGRAMA);
        this.declaracionesGlobales = declaracionesGlobales;
        this.funcionesYClases = funcionesYClases;
        this.bloquePrincipal = bloquePrincipal;
    }

    public List<Declaracion> getDeclaracionesGlobales() {
        return declaracionesGlobales;
    }

    public List<Declaracion> getFuncionesYClases() {
        return funcionesYClases;
    }

    public NodoBloque getBloquePrincipal() {
        return bloquePrincipal;
    }

    @Override
    public String toString(String tab) {    
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("PROGRAMA\n");

        if (declaracionesGlobales != null && !declaracionesGlobales.isEmpty()) {
            sb.append(tab).append("  [Variables Globales]\n");
            for (Declaracion dec : declaracionesGlobales) {
                sb.append(dec.toString(tab + "    "));
            }
        }

        if (funcionesYClases != null && !funcionesYClases.isEmpty()) {
            sb.append(tab).append("  [Clases y Funciones]\n");
            for (Declaracion dec : funcionesYClases) {
                sb.append(dec.toString(tab + "    "));
            }
        }

        sb.append(tab).append("  [Bloque Principal (princ)]\n");
        sb.append(bloquePrincipal.toString(tab + "    "));

        return sb.toString();
    }



}