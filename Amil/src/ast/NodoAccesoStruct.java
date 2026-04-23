package ast;

public class NodoAccesoStruct extends Designador {

    private Designador variableStruct;
    private String campo;

    public NodoAccesoStruct(int fil, int col, Designador variableStruct, String campo) {
        super(fil, col);
        this.variableStruct = variableStruct;
        this.campo = campo;
        this.kind = KindE.ACCESO_STRUCT;
    }

    public Designador getVariableStruct() {
        return variableStruct;
    }

    public String getCampo() {
        return campo;
    }

    @Override
    public String toString(String tab) {
        return tab + "ACCESO A STRUCT (.)\n" +
                variableStruct.toString(tab + "  ") +
                tab + "  Campo: " + campo + "\n";
    }

    public void chequeaTipo() {
    }
}