package ast;

import java.util.ArrayList;
import java.util.List;

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
}