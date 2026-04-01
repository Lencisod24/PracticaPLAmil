package ast;

public class NodoSuma extends ExpresionBinaria {

    public NodoSuma(int fil, int col, Expresion opIzq, Expresion opDer) {
        
        super(fil, col, opIzq, opDer); 
        this.kind = KindE.SUMA;
    }

    @Override
    public String toString(String tab) {
        String resultado = tab + "Suma (+)\n";
        String nuevoTab = tab + "  ";
        
        
        resultado += opIzq().toString(nuevoTab);
        resultado += opDer().toString(nuevoTab);
        
        return resultado;
    }
    
}