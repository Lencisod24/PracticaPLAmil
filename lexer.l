package alex;

import errors.GestionErroresExp;

%%
%cup
%column
%line
%class AnalizadorLexicoExp
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresExp errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

// ==========================================
// 1. EXPRESIONES REGULARES BÁSICAS
// ==========================================
letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}* | 0)
parteDecimal = {digito}* {digitoPositivo}
other_id_char = [_]

// Identificadores y Literales dinámicos
identifier = {letra}({letra}|{digito}|{other_id_char})*
numeroEntero = {parteEntera}
numeroReal = {parteEntera}\.{parteDecimal}
literalCaracter = \'[^\']\'
literalCadena = \"[^\"]*\"

// ==========================================
// 2. PALABRAS RESERVADAS (Tipos y Estructuras)
// ==========================================
// Tipos de datos
tipoEntero = entero
tipoReal = real
tipoBooleano = booleano
tipoCaracter = caracter
tipoCadena = cadena
tipoVacio = vacio

// Estructuras y Control
kwPrinc = princ
kwFunc = func
kwDe = de
kwSi = si
kwEntonces = entonces
kwSino = sino
kwMientras = mientras
kwHacer = hacer
kwIni = ini
kwFin = fin
kwClase = clase
kwNuevo = nuevo
kwSize = size
kwVal = val
kwRef = ref
kwConst = const
kwImprimir = imprimir
kwLeer = leer

// Literales Fijos
litCierto = cierto
litFalso = falso
litNull = null

%%
// ==========================================
// 3. REGLAS LÉXICAS
// ==========================================

  // --- Ignorar espacios y saltos de línea ---
  [ \t\r\b\n]+              { /* Ignorar espacios y tabulaciones */ }

  // --- Comentarios ---
  "//" [^\r\n]* { /* Ignorar comentarios de una línea */ }
  "/*" [^*]* "*"+ ([^/*] [^*]* "*"+)* "/" { /* Ignorar comentarios de bloque */ }

  // --- Tipos de Datos ---
  {tipoEntero}              { return ops.unidadTipoEntero(); }
  {tipoReal}                { return ops.unidadTipoReal(); }
  {tipoBooleano}            { return ops.unidadTipoBooleano(); }
  {tipoCaracter}            { return ops.unidadTipoCaracter(); }
  {tipoCadena}              { return ops.unidadTipoCadena(); }
  {tipoVacio}               { return ops.unidadVacio(); }

  // --- Palabras Reservadas ---
  {kwPrinc}                 { return ops.unidadOperadorPrinc(); }
  {kwFunc}                  { return ops.unidadFunc(); }
  {kwDe}                    { return ops.unidadDe(); }
  {kwSi}                    { return ops.unidadSi(); }
  {kwEntonces}              { return ops.unidadEntonces(); }
  {kwSino}                  { return ops.unidadSino(); }
  {kwMientras}              { return ops.unidadMientras(); }
  {kwHacer}                 { return ops.unidadHacer(); }
  {kwIni}                   { return ops.unidadAbreBloque(); } 
  {kwFin}                   { return ops.unidadCierraBloque(); }
  {kwClase}                 { return ops.unidadClase(); }
  {kwNuevo}                 { return ops.unidadNuevo(); }
  {kwSize}                  { return ops.unidadSize(); }
  {kwVal}                   { return ops.unidadVal(); }
  {kwRef}                   { return ops.unidadRef(); }
  {kwConst}                 { return ops.unidadConst(); }
  {kwImprimir}              { return ops.unidadImprimir(); }
  {kwLeer}                  { return ops.unidadLeer(); }

  // --- Literales Fijos ---
  {litCierto}               { return ops.unidadCierto(); }
  {litFalso}                { return ops.unidadFalso(); }
  {litNull}                 { return ops.unidadNull(); }

  // --- Literales Dinámicos y Variables ---
  {numeroEntero}            { return ops.unidadEntero(); }
  {numeroReal}              { return ops.unidadReal(); }
  {literalCaracter}         { return ops.unidadCaracter(); }
  {literalCadena}           { return ops.unidadCadena(); }
  {identifier}              { return ops.unidadId(); }

  // --- Operadores Matemáticos ---
  "+"                       { return ops.unidadSuma(); }
  "-"                       { return ops.unidadResta(); }
  "*"                       { return ops.unidadMultiplicacion(); }
  "/"                       { return ops.unidadDivision(); }
  "%"                       { return ops.unidadModulo(); }

  // --- Operadores Relacionales y Asignación ---
  "=="                      { return ops.unidadIgual(); }
  "!="                      { return ops.unidadDistinto(); }
  "<="                      { return ops.unidadMenorIgual(); }
  ">="                      { return ops.unidadMayorIgual(); }
  "<"                       { return ops.unidadMenor(); }
  ">"                       { return ops.unidadMayor(); }
  "="                       { return ops.unidadAsignacion(); }

  // --- Operadores Lógicos ---
  "&&"                      { return ops.unidadAnd(); }
  "||"                      { return ops.unidadOr(); }
  "!"                       { return ops.unidadNot(); }

  // --- Operadores de Punteros y Clases ---
  "&"                       { return ops.unidadDireccion(); }
  "@"                       { return ops.unidadContenidoPuntero(); } 
  "->"                      { return ops.unidadFlecha(); }
  "."                       { return ops.unidadAcceso(); }

  // --- Signos de Puntuación y Agrupación ---
  ":"                       { return ops.unidadDosPuntos(); }
  ";"                       { return ops.unidadPuntoYComa(); }
  ","                       { return ops.unidadComa(); }
  "("                       { return ops.unidadPAp(); }
  ")"                       { return ops.unidadPCierre(); }
  "["                       { return ops.unidadCAp(); }
  "]"                       { return ops.unidadCCierre(); }
  "{"                       { return ops.unidadLlaveApertura(); }
  "}"                       { return ops.unidadLlaveCierre(); }

  // --- Manejo de Errores ---
  [^]                       { errores.errorLexico(fila(), columna(), lexema()); }