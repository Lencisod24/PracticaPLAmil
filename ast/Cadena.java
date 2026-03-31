package ast;

public class Cadena extends E {
    private String valorCadena;

    public Cadena(String valorCadena) {
        this.valorCadena = valorCadena;
    }

    public KindE kind() {
        return KindE.CADENA; 
    }
    
    public String toString() {
        return valorCadena;
    }
}