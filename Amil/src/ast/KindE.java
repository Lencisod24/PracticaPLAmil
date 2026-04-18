package ast;

public enum KindE {
  // Operaciones matemáticas
  SUMA, RESTA, MUL, DIV, MOD, MENOS_UNARIO, MAS_UNARIO,

  // Operaciones relacionales
  MAYOR, MENOR, MAYORIGUAL, MENORIGUAL, IGUAL, DISTINTO,

  // Operaciones lógicas
  AND, OR, NOT,

  // Operaciones de acceso
  ACCESO_STRUCT, ACCESO_ARRAY, DIRECCION, DESREFERENCIA,

  // Tipos de valores
  ENTERO, REAL, CARACTER, CADENA, IDEN, BOOLEANO
}
