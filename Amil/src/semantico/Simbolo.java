package semantico;

public class Simbolo {
    public String iden;
    public String tipo;
    public int nivelContexto;

    public int indiceWasm;

    public Simbolo(String nombre, String tipo, int nivelContexto) {
        this.iden = nombre;
        this.tipo = tipo;
        this.nivelContexto = nivelContexto;
        this.indiceWasm = -1; // Se asignará más adelante
    }
}