package ast;

public class Mul extends EBin {
   public Mul(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public KindE kind() {return KindE.MUL;}
   public String toString() {return "mul("+opnd1().toString()+","+opnd2().toString()+")";}
}
