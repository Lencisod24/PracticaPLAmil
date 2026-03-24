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

// Identificadores y literales
letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}* | 0)
parteDecimal = {digito}* {digitoPositivo}
separador = [\t\r\b\n]
other_id_char   = [_]
identifier      = {letra}({letra}|{digito}|{other_id_char})*
numeroEntero = {parteEntera}
numeroReal = {parteEntera}\.{parteDecimal}

// Comentarios
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Comments = {LineComment} | {BlockComment}
LineComment = "//" [^\n]* 
BlockComment =  "/*" [^*] ~"*/" | "/*" "*"+ "/"

// Espacios en blanco y salto de línea
BlankSpace = {LineTerminator} | [ \t\f]


// Definición de expresiones regulares para los operadores y palabras clave
operadorCaracter = caracter
operadorCadena = cadena
operadorImprimir = imprimir
operadorLeer = leer
operadorSi = si
operadorSino = sino
operadorEntonces = entonces

operadorMientras = mientras


operadorNuevo = nuevo
operadorCierto = cierto
operadorFalso = falso
operadorBooleano = booleano
operadorEntero = entero
operadorVacio = void

operadorClase = clase
operadorPrinc = princ
operadorSize = size
operadorValor = val 
operadorRef = ref

operadorConstante = const

// Operadores
asignacion = \=
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
operadorModulo = \%

igual = \==
distinto = \!=
operadorMenor = <
operadorMayor = >
operadorMenorIgual = <=
operadorMayorIgual = >=

this = this
flecha = ->

operadorAnd = &&
operadorOr = \|\|
operadorNot = \!

operadorDireccion = &
operadorContenidoPuntero = @
operadorAcceso = \.

// Paréntesis, corchetes y llaves

parentesisApertura = \(
parentesisCierre = \)
corcheteAbierto = \[
corcheteCerrado = \]
llaveApertura = "{"
llaveCierre = "}"
abrirBloque = ini
cerrarBloque = fin

coma = ,
puntoYComa = ;
dosPuntos = :




// Reglas léxicas

%%
    {separador}             {}
    {Comments}              { /* ignorar comentarios */ }
    {BlankSpace}            { /* ignorar espacios */ }
    {operadorPrinc} 		{ return ops.unidadOperadorPrinc(); }
    {numeroEntero}             { return ops.unidadEnt(); }  
    {numeroReal}               { return ops.unidadReal(); }
    {dosPuntos} 			 {return ops.unidadDosPuntos();}
  // Palabras clave
  {operadorMenor}          { return ops.unidadMenor(); }
  {operadorMayor}          { return ops.unidadMayor(); }
  {operadorMenorIgual}     { return ops.unidadMenorIgual(); }
  {operadorMayorIgual}     { return ops.unidadMayorIgual(); }

  {operadorAnd}            { return ops.unidadAnd(); }
  {operadorOr}             { return ops.unidadOr(); }
  {operadorNot}            { return ops.unidadNot(); }

  {operadorDireccion}      { return ops.unidadDireccion(); }
  {operadorContenidoPuntero}   { return ops.unidadContenidoPuntero(); }
	

  {operadorAcceso}         { return ops.unidadAcceso(); }

  {corcheteAbierto}        { return ops.unidadCorcheteAbierto(); }
  {corcheteCerrado}        { return ops.unidadCorcheteCerrado(); }
  {parentesisApertura}     { return ops.unidadPAp(); }
  {parentesisCierre}       { return ops.unidadPCierre(); }
  {llaveApertura}         { return ops.unidadLlaveApertura(); }
  {llaveCierre}           { return ops.unidadLlaveCierre(); }
  {abrirBloque}             {return ops.unidadAbrirBloque();}
  {cerrarBloque}             {return ops.unidadCerrarBloque();}
  {coma}                  { return ops.unidadComa(); }
  {puntoYComa}            { return ops.unidadPuntoYComa(); }
  {asignacion}            { return ops.unidadAsignacion(); }
  {this}                  { return ops.unidadThis();   }
  {flecha}                { return ops.unidadFlecha(); }
  
  {operadorChar} { errores.errorLexico(fila(),columna(),lexema()); }
  {operadorString}  {errores.errorLexico(fila(),columna(),lexema()); }
  {operadorImprimir}           { return ops.unidadImprimir(); }
  {operadorLeer}            { return ops.unidadLeer(); }
  {operadorBooleano}           { return ops.unidadBooleano(); }
  {operadorSi}             { return ops.unidadSi(); }
  {operadorSino}           { return ops.unidadSino(); }
  {operadorMientras}          { return ops.unidadMientras(); }
  {operadorNuevo}            { return ops.unidadNuevo(); }
  {operadorCierto}           { return ops.unidadCierto(); }
  {operadorFalso}          { return ops.unidadFalso(); }
  {operadorEntero}            { return ops.unidadEntero(); }
  {operadorVacio}           { return ops.unidadVacio(); }
  {operadorClase}           { return ops.unidadClase(); }
  {operadorSize}            {return ops.unidadSize();}
  {operadorValor}           {return ops.unidadValor();}
  {operadorRef}            {return ops.unidadReferencia();}   
  {operadorConstante}      { return ops.unidadConst(); }
  {identifier}             { return ops.unidadId(); }

  // Operadores
  {operadorSuma}           { return ops.unidadSuma(); }
  {operadorResta}          { return ops.unidadResta(); }
  {operadorMultiplicacion} { return ops.unidadMultiplicacion(); }
  {operadorDivision}       { return ops.unidadDivision(); }
  {operadorModulo}         { return ops.unidadModulo(); }

  {igual}                  { return ops.unidadIgual(); }
  {distinto}               { return ops.unidadDistinto(); }
  


  // Errores léxicos
  [^] { errores.errorLexico(fila(),columna(),lexema()); }


