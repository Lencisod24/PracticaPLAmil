package ast;

public class Sum extends EBin {
   public Sum(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public KindE kind() {return KindE.SUMA;}
   public String toString() {return "suma("+opnd1().toString()+","+opnd2().toString()+")";}
}
