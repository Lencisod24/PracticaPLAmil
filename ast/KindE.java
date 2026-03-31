package ast;

public enum KindE {
  // Operaciones matemáticas
    SUMA, RESTA, MUL, DIV,
    
    // Operaciones relacionales
    MAYOR, MENOR, MAYORIGUAL, MENORIGUAL, IGUAL, DISTINTO,
    
    // Operaciones lógicas
    AND, OR, NOT,
    
    // Tipos de valores
    NUM, ENTERO, REAL, CARACTER, CADENA, IDEN, BOOLEANO  
}
