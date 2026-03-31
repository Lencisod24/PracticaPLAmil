package ast;

public class Real extends E {
    private String valorReal;

    public Real(String valorReal) {
        this.valorReal = valorReal;
    }

    public KindE kind() {
        return KindE.REAL; 
    }
    @Override
    public String num() {
        return valorReal;
    }
    public String toString() {
        return valorReal;
    }
}