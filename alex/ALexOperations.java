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
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_ENTERO, "entero");
  }

  public UnidadLexica unidadTipoReal() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_REAL, "real");
  }

  public UnidadLexica unidadTipoBooleano() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_BOOLEANO, "booleano");
  }

  public UnidadLexica unidadTipoCaracter() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_CARACTER, "caracter");
  }

  public UnidadLexica unidadTipoCadena() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TIPO_CADENA, "cadena");
  }

  public UnidadLexica unidadVacio() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VACIO, "vacio");
  }

  // ==========================================
  // PALABRAS RESERVADAS (Estructuras)
  // ==========================================
  public UnidadLexica unidadOperadorPrinc() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PRINC, "princ");
  }

  public UnidadLexica unidadFunc() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FUNC, "func");
  }

  public UnidadLexica unidadDe() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DE, "de");
  }

  public UnidadLexica unidadSi() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SI, "si");
  }

  public UnidadLexica unidadEntonces() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTONCES, "entonces");
  }

  public UnidadLexica unidadSino() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SINO, "sino");
  }

  public UnidadLexica unidadMientras() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MIENTRAS, "mientras");
  }

  public UnidadLexica unidadHacer() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.HACER, "hacer");
  }

  public UnidadLexica unidadAbreBloque() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ABREBLOQUE, "ini");
  }

  public UnidadLexica unidadCierraBloque() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CIERRABLOQUE, "fin");
  }

  public UnidadLexica unidadClase() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CLASE, "clase");
  }

  public UnidadLexica unidadNuevo() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUEVO, "nuevo");
  }

  public UnidadLexica unidadSize() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SIZE, "size");
  }

  public UnidadLexica unidadVal() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VAL, "val");
  }

  public UnidadLexica unidadRef() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REF, "ref");
  }

  public UnidadLexica unidadConst() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CONST, "const");
  }

  public UnidadLexica unidadImprimir() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IMPRIMIR, "imprimir");
  }

  public UnidadLexica unidadLeer() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LEER, "leer");
  }

  public UnidadLexica unidadThis() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.THIS, "this");
  }

  // ==========================================
  // LITERALES FIJOS
  // ==========================================
  public UnidadLexica unidadCierto() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CIERTO, "cierto");
  }

  public UnidadLexica unidadFalso() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSO, "falso");
  }

  public UnidadLexica unidadNull() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "null");
  }
  
  // ==========================================
  // VALORES DINÁMICOS (Envían el lexema leído)
  // ==========================================
  public UnidadLexica unidadEntero() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTERO, alex.lexema());
  }

  public UnidadLexica unidadReal() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL, alex.lexema());
  }

  public UnidadLexica unidadCaracter() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CARACTER, alex.lexema());
  }

  public UnidadLexica unidadCadena() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
  }

  public UnidadLexica unidadId() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN, alex.lexema());
  }

  // ==========================================
  // OPERADORES MATEMÁTICOS
  // ==========================================
  public UnidadLexica unidadSuma() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+");
  }

  public UnidadLexica unidadResta() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-");
  }

  public UnidadLexica unidadMultiplicacion() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR, "*");
  }

  public UnidadLexica unidadDivision() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "/");
  }

  public UnidadLexica unidadModulo() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD, "%");
  }

  // ==========================================
  // OPERADORES RELACIONALES Y ASIGNACIÓN
  // ==========================================
  public UnidadLexica unidadIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "==");
  }

  public UnidadLexica unidadDistinto() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIST, "!=");
  }

  public UnidadLexica unidadMenorIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL, "<=");
  }

  public UnidadLexica unidadMayorIgual() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL, ">=");
  }

  public UnidadLexica unidadMenor() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
  }

  public UnidadLexica unidadMayor() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, ">");
  }

  public UnidadLexica unidadAsignacion() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION, "=");
  }

  // ==========================================
  // OPERADORES LÓGICOS
  // ==========================================
  public UnidadLexica unidadAnd() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "&&");
  }

  public UnidadLexica unidadOr() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "||");
  }

  public UnidadLexica unidadNot() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "!");
  }

  // ==========================================
  // PUNTEROS Y CLASES
  // ==========================================
  public UnidadLexica unidadDireccion() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIRECCION, "&");
  }

  public UnidadLexica unidadContenidoPuntero() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CONTPUNT, "@");
  }

  public UnidadLexica unidadFlecha() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FLECHA, "->");
  }

  public UnidadLexica unidadAcceso() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ACCESO, ".");
  }

  // ==========================================
  // PUNTUACIÓN Y AGRUPACIÓN
  // ==========================================
  public UnidadLexica unidadDosPuntos() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOSPUNTOS, ":");
  }

  public UnidadLexica unidadPuntoYComa() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA, ";");
  }

  public UnidadLexica unidadComa() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
  }

  public UnidadLexica unidadPAp() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP, "(");
  }

  public UnidadLexica unidadPCierre() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, ")");
  }

  public UnidadLexica unidadCAp() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CA, "[");
  }

  public UnidadLexica unidadCCierre() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CC, "]");
  }

  public UnidadLexica unidadLlaveApertura() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVEAPERTURA, "{");
  }

  public UnidadLexica unidadLlaveCierre() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVECIERRE, "}");
  }

  // ==========================================
  // FIN DE FICHERO
  // ==========================================
  public UnidadLexica unidadEof() {
	  return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
  }
}