package ast;

import java.util.ArrayList;
import java.util.List;

import semantico.TablaSimbolos;

public class NodoBloque extends Instruccion {

    private List<Instruccion> instrucciones;

    public NodoBloque(int fil, int col) {
        super(fil, col);
        this.instrucciones = new ArrayList<>();
    }

    public NodoBloque(int fil, int col, List<Instruccion> instrucciones) {
        super(fil, col);
        this.instrucciones = instrucciones;
    }

    public void addInstruccion(Instruccion inst) {
        if (inst != null) {
            this.instrucciones.add(inst);
        }
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public String toString(String tab) {
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("BLOQUE {\n");
        for (Instruccion inst : instrucciones) {
            sb.append(inst.toString(tab + "  "));
        }
        sb.append(tab).append("}\n");
        return sb.toString();
    }

    @Override
    public void chequea(TablaSimbolos ts) {
        ts.abreBloque();

        if (instrucciones != null) {
            for (Instruccion inst : instrucciones) {
                if (inst != null) {
                    inst.chequea(ts);
                }
            }
        }

        ts.cierraBloque();
    }

    @Override
    public void generateCodeInstruccion(StringBuilder sb, int indent) {
        
        for(Instruccion i:this.instrucciones){
            i.generateCodeInstruccion(sb, indent);
        }
    }
}