package ast;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import semantico.TablaSimbolos;

public class NodoPrograma extends ASTNode {

    private List<Declaracion> declaracionesGlobales;
    private List<Declaracion> funcionesYStructs;
    private NodoBloque bloquePrincipal;
    private int memSize;

    public NodoPrograma(int fil, int col, List<Declaracion> declaracionesGlobales, List<Declaracion> funcionesYStructs,
            NodoBloque bloquePrincipal) {
        super(fil, col, NodeKind.PROGRAMA);
        this.declaracionesGlobales = declaracionesGlobales;
        this.funcionesYStructs = funcionesYStructs;
        this.bloquePrincipal = bloquePrincipal;
        this.memSize = 0;
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

    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        
        // Definición de datos y tipos
        sb.append("(data (i32.const 64) \"\\22\\00\\00\\00\") ;; 34\n");
        sb.append("(data (i32.const 68) \"#\\00\\00\\00\")   ;; 35\n");
        sb.append("(export \"init\" (func $init))\n");
        sb.append("(type $_sig_void (func ))\n");
        sb.append("(type $_sig_i32 (func (param i32)))\n");
        sb.append("(type $_sig_ri32 (func (result i32)))\n");
        sb.append("(type $_sig_i32i32ri32ri32 (func (param i32 i32)(result i32 i32)))\n");

        // Configuración de tabla y memoria
        sb.append("(start $init)\n");
        sb.append("(elem $funcmap (i32.const 0) $init)\n");
        sb.append("(import \"runtime\" \"print\" (func $print (type $_sig_i32)))\n");
        sb.append("(import \"runtime\" \"read\" (func $read (type $_sig_ri32)))\n");
        sb.append("(table $funcmap 1 1 funcref)\n");
        sb.append("(global $smd i32 (i32.const 64)) ;; points to start of memory data\n");
        sb.append("(memory 2000)\n");
        // ESTO NO SE LO QUE ES
        sb.append("(global $SP (mut i32) (i32.const 0))\n");
        sb.append("(global $MP (mut i32) (i32.const 0))\n");
        sb.append("(global $NP (mut i32) (i32.const 131071))\n");

        // Función reserveStack
        sb.append("(func $reserveStack (param $size i32) (result i32)\n");
        sb.append("  global.get $MP\n");
        sb.append("  global.get $SP\n");
        sb.append("  global.set $MP\n");
        sb.append("  global.get $SP\n");
        sb.append("  local.get $size\n");
        sb.append("  i32.add\n");
        sb.append("  global.set $SP\n");
        sb.append("  global.get $SP\n");
        sb.append("  global.get $NP\n");
        sb.append("  i32.gt_u\n");
        sb.append("  if\n");
        sb.append("    i32.const 3\n");
        sb.append("    call $exception\n");
        sb.append("  end\n");
        sb.append(")\n");

        // Función freeStack
        sb.append("(func $freeStack (type $_sig_void)\n");
        sb.append("  global.get $MP\n");
        sb.append("  global.set $SP\n");
        sb.append("  global.get $MP\n");
        sb.append("  i32.load\n");
        sb.append("  global.set $MP\n");
        sb.append(")\n");
        for(Declaracion d: declaracionesGlobales) d.generateCodeInstruccion(sb,indent);
        for(Declaracion d: funcionesYStructs) d.generateCodeInstruccion(sb,indent);
        

        sb.append("(func $init\n");
        sb.append("  i32.const ").append(memSize).append("\n");
        sb.append("  call $reserveStack\n");
        sb.append("  global.get $MP\n");
        sb.append("  i32.store\n");
        bloquePrincipal.generateCodeInstruccion(sb, 1);
        sb.append("  call $freeStack\n");
        sb.append(")\n");
    };

    @Override 
    public void calcularMem(AtomicInteger curr, AtomicInteger  max) {
        AtomicInteger curr1 = new AtomicInteger(4);
        AtomicInteger max1 = new AtomicInteger(4);
        bloquePrincipal.calcularMem(curr1, max1);
        memSize = max1.get();
    }

    @Override
    public int asignarDelta(int dirPadre) {
        int dirLocal = 0;
        
        for (Declaracion d : this.declaracionesGlobales)
            dirLocal = d.asignarDelta(dirLocal);
        for (Declaracion d : this.funcionesYStructs)
            dirLocal = d.asignarDelta(dirLocal);
        dirLocal = bloquePrincipal.asignarDelta(dirLocal);
        return dirLocal;
    }

    

}