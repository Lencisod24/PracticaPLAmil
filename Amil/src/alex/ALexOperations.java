package alex;
import constructorast.*;
public class ALexOperations {
  private AnalizadorLexicoExp alex;
  
  public ALexOperations(AnalizadorLexicoExp alex) {
   this.alex = alex;   
  }

  // ==========================================
  // TIPOS DE DATOS
  // ==========================================
  
 public UnidadLexica unidadTipoEntero() {    
    return new UnidadLexica(alex.fila(), alex.columna(), sym.TIPO_ENTERO, "entero");
}

public UnidadLexica unidadId() {
    return new UnidadLexica(alex.fila(), alex.columna(), sym.IDEN, alex.lexema());
}

  public UnidadLexica unidadTipoReal() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.TIPO_REAL, "real");
  }

  public UnidadLexica unidadTipoBooleano() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.TIPO_BOOLEANO, "booleano");
  }

  public UnidadLexica unidadTipoCaracter() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.TIPO_CARACTER, "caracter");
  }

  public UnidadLexica unidadTipoCadena() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.TIPO_CADENA, "cadena");
  }

  public UnidadLexica unidadVacio() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.VACIO, "vacio");
  }

  // ==========================================
  // PALABRAS RESERVADAS (Estructuras)
  // ==========================================
  public UnidadLexica unidadStruct() {
    return new UnidadLexica(alex.fila(), alex.columna(), sym.STRUCT, "struct");
  }

  public UnidadLexica unidadOperadorPrinc() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.PRINC, "princ");
  }

  public UnidadLexica unidadFunc() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.FUNC, "func");
  }

  public UnidadLexica unidadDe() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.DE, "de");
  }

  public UnidadLexica unidadSi() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.SI, "si");
  }

  public UnidadLexica unidadEntonces() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.ENTONCES, "entonces");
  }

  public UnidadLexica unidadSino() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.SINO, "sino");
  }

  public UnidadLexica unidadMientras() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MIENTRAS, "mientras");
  }

  public UnidadLexica unidadHacer() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.HACER, "hacer");
  }

  public UnidadLexica unidadAbreBloque() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.ABREBLOQUE, "ini");
  }

  public UnidadLexica unidadCierraBloque() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CIERRABLOQUE, "fin");
  }

  public UnidadLexica unidadNuevo() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.NUEVO, "nuevo");
  }

  public UnidadLexica unidadSize() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.SIZE, "size");
  }

  public UnidadLexica unidadVal() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.VAL, "val");
  }

  public UnidadLexica unidadRef() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.REF, "ref");
  }

  public UnidadLexica unidadConst() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CONST, "const");
  }

  public UnidadLexica unidadImprimir() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.IMPRIMIR, "imprimir");
  }

  public UnidadLexica unidadLeer() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.LEER, "leer");
  }

  // ==========================================
  // LITERALES FIJOS
  // ==========================================
  public UnidadLexica unidadCierto() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CIERTO, "cierto");
  }

  public UnidadLexica unidadFalso() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.FALSO, "falso");
  }

  public UnidadLexica unidadNull() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.NULL, "null");
  }

  // ==========================================
  // VALORES DINAMICOS (Envían el lexema leido)
  // ==========================================
  public UnidadLexica unidadEntero() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.ENTERO, alex.lexema());
  }

  public UnidadLexica unidadReal() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.REAL, alex.lexema());
  }

  public UnidadLexica unidadCaracter() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CARACTER, alex.lexema());
  }

  public UnidadLexica unidadCadena() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CADENA, alex.lexema());
  }

  

  // ==========================================
  // OPERADORES MATEMATICOS
  // ==========================================
  public UnidadLexica unidadSuma() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MAS, "+");
  }

  public UnidadLexica unidadResta() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MENOS, "-");
  }

  public UnidadLexica unidadMultiplicacion() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.POR, "*");
  }

  public UnidadLexica unidadDivision() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.DIV, "/");
  }

  public UnidadLexica unidadModulo() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MOD, "%");
  }

  // ==========================================
  // OPERADORES RELACIONALES Y ASIGNACION
  // ==========================================
  public UnidadLexica unidadIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.IGUAL, "==");
  }

  public UnidadLexica unidadDistinto() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.DIST, "!=");
  }

  public UnidadLexica unidadMenorIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MENORIGUAL, "<=");
  }

  public UnidadLexica unidadMayorIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MAYORIGUAL, ">=");
  }

  public UnidadLexica unidadMenor() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MENOR, "<");
  }

  public UnidadLexica unidadMayor() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.MAYOR, ">");
  }

  public UnidadLexica unidadAsignacion() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.ASIGNACION, "=");
  }

  // ==========================================
  // OPERADORES LOGICOS
  // ==========================================
  public UnidadLexica unidadAnd() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.AND, "&&");
  }

  public UnidadLexica unidadOr() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.OR, "||");
  }

  public UnidadLexica unidadNot() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.NOT, "!");
  }

  // ==========================================
  // PUNTEROS Y CLASES
  // ==========================================
  public UnidadLexica unidadDireccion() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.DIRECCION, "&");
  }

  public UnidadLexica unidadAcceso() {
    return new UnidadLexica(alex.fila(), alex.columna(), sym.ACCESO_STRUCT, ".");
 }

  /*public UnidadLexica unidadContenidoPuntero() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CONTPUNT, "@");
  }

  public UnidadLexica unidadFlecha() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.FLECHA, "->");
  }

  public UnidadLexica unidadAcceso() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.ACCESO, ".");
  }*/

  // ==========================================
  // PUNTUACIÓN Y AGRUPACION
  // ==========================================
  public UnidadLexica unidadDosPuntos() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.DOSPUNTOS, ":");
  }

  public UnidadLexica unidadPuntoYComa() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.PUNTOYCOMA, ";");
  }

  public UnidadLexica unidadComa() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.COMA, ",");
  }

  public UnidadLexica unidadPAp() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.PAP, "(");
  }

  public UnidadLexica unidadPCierre() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.PCIERRE, ")");
  }

  public UnidadLexica unidadCAp() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CA, "[");
  }

  public UnidadLexica unidadCCierre() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.CC, "]");
  }

  public UnidadLexica unidadLlaveApertura() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.LLAVEAPERTURA, "{");
  }

  public UnidadLexica unidadLlaveCierre() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.LLAVECIERRE, "}");
  }

  // ==========================================
  // FIN DE FICHERO
  // ==========================================
  public UnidadLexica unidadEof() {
	  return new UnidadLexica(alex.fila(), alex.columna(), sym.EOF, "<EOF>");
  }
}