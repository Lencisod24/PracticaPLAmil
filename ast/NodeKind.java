package ast;

public enum NodeKind {
    // Tipo genérico 
    EXPRESSION, 

    // Operadores Aritméticos
    SUMA, 
    RESTA, 
    MULTIPLICACION,   
    DIVISION, 

    // Operadores Relacionales/Lógicos
    MAYOR, 
    MENOR, 
    MAYOR_IGUAL, 
    MENOR_IGUAL, 
    IGUAL, 
    DISTINTO,
    AND, 
    OR, 
    NOT,

    // Tipos de valores (Hojas del árbol)
    ENTERO, 
    REAL, 
    BOOLEANO, 
    CADENA, 
    IDEN
}