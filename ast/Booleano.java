package ast;

public class Booleano extends E {
    private String valorBooleano;

    public Booleano(String valorBooleano) {
        this.valorBooleano = valorBooleano;
    }

    public KindE kind() {
        return KindE.BOOLEANO; 
    }
    
    public String toString() {
        return valorBooleano;
    }
}