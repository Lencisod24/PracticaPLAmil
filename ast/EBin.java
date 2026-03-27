package ast;

public class EBin extends E {
   private E opnd1;
   private E opnd2;
   private KindE kindE;
   public EBin(E opnd1, E opnd2, KindE kindE) {
     this.opnd1 = opnd1;
     this.opnd2 = opnd2;
     this.kindE = kindE;
     
   }
   public E opnd1() {return opnd1;}
   public E opnd2() {return opnd2;}   
   @Override
   public String toString() {
     String op = "";
        if (kindE == KindE.SUMA) { op = "+"; }
        else if (kindE == KindE.MUL) { op = "*"; }
        
        return "(" + opnd1.toString() + " " + op + " " + opnd2.toString() + ")";
   }

   // Este es el método que te pedía E.java en tu última imagen
   @Override
   public KindE kind() {
     return kindE; 
   }
}
