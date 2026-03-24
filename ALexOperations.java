package alex;

public class ALexOperations {
  private AnalizadorLexicoExp alex;
  public ALexOperations(AnalizadorLexicoExp alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadDosPuntos() {
	  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DOSPUNTOS, ":");
  }
  
  public UnidadLexica unidadContenidoPuntero(){
    return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CONTPUNT, "@"); 
  }
  
  
  public UnidadLexica unidadOperadorPrinc(){
    return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PRINC);
  }

  // Identificadores
  public UnidadLexica unidadId() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN, alex.lexema());
  }

  // Literales
  public UnidadLexica unidadEnt() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT,alex.lexema());
  }

  public UnidadLexica unidadReal() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL,alex.lexema());
  }

  // Operadores Aritméticos
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

  // Comparación
  public UnidadLexica unidadIgual() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "==");
  }

  public UnidadLexica unidadDistinto() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIST, "!=");
  }

  public UnidadLexica unidadMenor() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
  }

  public UnidadLexica unidadMayor() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR,">");
  }

  public UnidadLexica unidadMenorIgual() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL,"<=");
  }

  public UnidadLexica unidadMayorIgual() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL,">=");
  }

  // Operadores Lógicos
  public UnidadLexica unidadAnd() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "&&");
  }

  public UnidadLexica unidadOr() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "||");
  }

  public UnidadLexica unidadNot() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT,"!");
  }

  // Operadores de asignación
  public UnidadLexica unidadAsignacion() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION, "=");
  }

  public UnidadLexica unidadDireccion() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIRECCION, "&");
  }

  public UnidadLexica unidadAcceso() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ACCESO,".");
  }

  // Paréntesis, corchetes y llaves
  public UnidadLexica unidadPAp() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP, "(");
  }

  public UnidadLexica unidadPCierre() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, ")");
  }

  public UnidadLexica unidadCorcheteAbierto() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CA, "[");
  }

  public UnidadLexica unidadCorcheteCerrado() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CC, "]");
  }

  public UnidadLexica unidadLlaveApertura() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVEAPERTURA, "{");
  }

  public UnidadLexica unidadLlaveCierre() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVECIERRE, "}");
  }
  public UnidadLexica unidadAbrirBloque() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ABREBLOQUE, "ini");
  }
   public UnidadLexica unidadCerrarBloque() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CIERRABLOQUE, "fin");
  }


  // Comas y punto y coma
  public UnidadLexica unidadComa() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
  }

  public UnidadLexica unidadPuntoYComa() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA, ";");
  }

  // Palabras clave
  public UnidadLexica unidadImprimir() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IMPRIMIR, "imprimir");
  }

  public UnidadLexica unidadLeer() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LEER, "leer");
  }

  public UnidadLexica unidadSi() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SI, "si");
  }

  public UnidadLexica unidadSino() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SINO, "sino");
  }

  public UnidadLexica unidadMientras() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MIENTRAS, "mientras");
  }

  public UnidadLexica unidadNuevo() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUEVO, "nuevo");
  }
  
  public UnidadLexica unidadBooleano() {
	    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOLEANO, "booleano");
  }
  
  public UnidadLexica unidadEntero() {
	    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTERO, "entero");
  }
  
  public UnidadLexica unidadVacio() {
	    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VACIO, "vacio");
  }
  
  
  public UnidadLexica unidadClase() {
	    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CLASE, "clase");
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

  // Literales
  public UnidadLexica unidadCierto() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CIERTO, "cierto");
  }

  public UnidadLexica unidadFalso() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSO, "falso");
  }

  public UnidadLexica unidadNull() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "null");
  }

  // EOF
  public UnidadLexica unidadEof() {
    return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
  }
  
  public UnidadLexica unidadThis() {
	 return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.THIS, "this");
  }
  
  public UnidadLexica unidadFlecha() {
	  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FLECHA,"->");
  }
}