package ast;

import java.util.List;

import semantico.TablaSimbolos;

public class NodoPrograma extends ASTNode {

    private List<Declaracion> declaracionesGlobales;
    private List<Declaracion> funcionesYStructs;
    private NodoBloque bloquePrincipal;

    public NodoPrograma(int fil, int col, List<Declaracion> declaracionesGlobales, List<Declaracion> funcionesYStructs,
            NodoBloque bloquePrincipal) {
        super(fil, col, NodeKind.PROGRAMA);
        this.declaracionesGlobales = declaracionesGlobales;
        this.funcionesYStructs = funcionesYStructs;
        this.bloquePrincipal = bloquePrincipal;
    }

    public List<Declaracion> getDeclaracionesGlobales() {
        return declaracionesGlobales;
    }

    public List<Declaracion> getfuncionesYStructs() {
        return funcionesYStructs;
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

        if (funcionesYStructs != null && !funcionesYStructs.isEmpty()) {
            sb.append(tab).append("  [Clases y Funciones]\n");
            for (Declaracion dec : funcionesYStructs) {
                sb.append(dec.toString(tab + "    "));
            }
        }

        if (bloquePrincipal != null) {
            sb.append(tab).append("  [Bloque Principal (princ)]\n");
            sb.append(bloquePrincipal.toString(tab + "    "));
        }

        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {

        if (declaracionesGlobales != null) {
            for (Declaracion dec : declaracionesGlobales) {
                if (dec != null) {
                    dec.chequea(ts);
                }
            }
        }

        if (funcionesYStructs != null) {
            for (Declaracion func : funcionesYStructs) {
                if (func != null) {
                    func.chequea(ts);
                }
            }
        }

        if (bloquePrincipal != null) {
            bloquePrincipal.chequea(ts);
        }
    }

    @Override
    public int calcularMem() {
        int t = 0;
        t += this.bloquePrincipal.calcularMem();
        for (Declaracion d : this.declaracionesGlobales)
            t += d.calcularMem();
        for (Declaracion d : this.funcionesYStructs)
            t += d.calcularMem();
        
        this.tam_mem = t;
        return t;
    }

    @Override
    public int asignarDelta(int dirPadre) {
        int dirLocal=0;
        dirLocal=bloquePrincipal.asignarDelta(dirLocal);
        for (Declaracion d : this.declaracionesGlobales)
            dirLocal = d.asignarDelta(dirLocal);
        for (Declaracion d : this.funcionesYStructs)
            dirLocal = d.asignarDelta(dirLocal);
        return dirLocal;  
    }

    @Override
    public void asignarTamMemTipos() {
        bloquePrincipal.asignarTamanosMemTipos();;
        for (Declaracion d : this.declaracionesGlobales)
            d.asignarTamMemTipos();;
        for (Declaracion d : this.funcionesYStructs)
             d.asignarTamMemTipos();;
        
    }

}