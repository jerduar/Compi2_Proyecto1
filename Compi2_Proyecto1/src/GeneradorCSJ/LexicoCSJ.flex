package GeneradorCSJ;

import java_cup.runtime.Symbol;

//Scanner para generar C3D
%%
%{
    //Código de usuario
    

%}

%cup
%class scanner
%public
%line
%char
%column
%full
%ignorecase

//Comentarios
LineTerminator = \r|\n|\r\n
    InputCharacter = [^\r\n]

    /* comments */
    Comentario = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

    TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    // Comment can be the last line of the file, without line terminator.
    EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
    DocumentationComment = "/**" {CommentContent} "*"+ "/"
    CommentContent       = ( [^*] | \*+ [^/*] )*


//Operadores Aritméticos

MAS		= "+"
MENOS		= "-"
MULT       	= "*"
DIV             = "/"
INCREMENTO      = "++"
DECREMENTO      = "--"

//Operadores Relacionales

IGUAL           = "=="
DIFERENTE       = "!="
MAYORQUE        = ">"
MENORQUE        = "<"
MAYORIGUALQUE   = ">="
MENORIGUALQUE   = "<="

//Operadores Lógicos

AND             = "&&"
OR              = "||"
NOT             = "!"

//Signos de Agrupación

PAR_IZQ         = "("
PAR_DER         = ")"
LLA_IZQ         = "{"
LLA_DER         = "}"

//Signos de Puntuacion
PUNTO           = "."
PUNTO_COMA      = ";"
COMA            = ","
DOS_PUNTOS      = ":"

//Expresiones Regulares

ID              = [_A-Za-z][_0-9A-Za-z]*
NUMERICO        = [0-9]+("\."[0-9]+)?
FECHA           = [0-3][0-9]"/"[0-1][0-9]"/"[0-9][0-9][0-9][0-9]
DATE            = "'"{FECHA}"'"
DATE_T          = "'"{FECHA}" "[0-9][0-9]":"[0-9][0-9]":"[0-9][0-9]"'"
CADENA          = "\""[^\"]*"\""

SPACE   = [\ \r\t\f\t]
ENTER   = [\ \n]

%%

//PALABRAS RESERVADAS
<YYINITIAL> "mientras"      { return new Symbol(sym.MIENTRAS, yyline, yycolumn, yytext()); }
<YYINITIAL> "'True'"        { return new Symbol(sym.TRUE, yyline, yycolumn, yytext()); }
<YYINITIAL> "'False'"       { return new Symbol(sym.FALSE, yyline, yycolumn, yytext()); }
<YYINITIAL> "funcion"       { return new Symbol(sym.FUNCION, yyline, yycolumn, yytext()); }
<YYINITIAL> "para"          { return new Symbol(sym.PARA, yyline, yycolumn, yytext()); }
<YYINITIAL> "si"            { return new Symbol(sym.SI, yyline, yycolumn, yytext()); }
<YYINITIAL> "sino"          { return new Symbol(sym.SINO, yyline, yycolumn, yytext()); }
<YYINITIAL> "imprimir"      { return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext()); }
<YYINITIAL> "DimV"          { return new Symbol(sym.DIMV, yyline, yycolumn, yytext()); }
<YYINITIAL> "Retornar"      { return new Symbol(sym.RETORNAR, yyline, yycolumn, yytext()); }
<YYINITIAL> "Detener"       { return new Symbol(sym.DETENER, yyline, yycolumn, yytext()); }
<YYINITIAL> "Selecciona"    { return new Symbol(sym.SELECCIONAR, yyline, yycolumn, yytext()); }
<YYINITIAL> "Caso"          { return new Symbol(sym.CASO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Defecto"       { return new Symbol(sym.DEFECTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Documento"     { return new Symbol(sym.DOCUMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Observador"    { return new Symbol(sym.OBSERVADOR, yyline, yycolumn, yytext()); }
<YYINITIAL> "aTexto"        { return new Symbol(sym.ATEXTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "SetElemento"   { return new Symbol(sym.SETELEMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Mensaje"       { return new Symbol(sym.MENSAJE, yyline, yycolumn, yytext()); }
<YYINITIAL> "Mensaje"       { return new Symbol(sym.MENSAJE, yyline, yycolumn, yytext()); }
<YYINITIAL> "Obtener"       { return new Symbol(sym.OBTENER, yyline, yycolumn, yytext()); }
<YYINITIAL> "Conteo"       { return new Symbol(sym.CONTEO, yyline, yycolumn, yytext()); }

<YYINITIAL> {ID}            { return new Symbol(sym.ID, yyline, yycolumn, yytext()); }
<YYINITIAL> {CADENA}        { return new Symbol(sym.CADENA, yyline, yycolumn, yytext()); }
<YYINITIAL> {NUMERICO}      { return new Symbol(sym.NUMERICO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DATE}          { return new Symbol(sym.DATE, yyline, yycolumn, yytext()); }
<YYINITIAL> {DATE_T}        { return new Symbol(sym.DATE_T, yyline, yycolumn, yytext()); }



<YYINITIAL> {MAS}           { return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOS}	    { return new Symbol(sym.MENOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MULT}          { return new Symbol(sym.MULT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIV}	    { return new Symbol(sym.DIV, yyline, yycolumn, yytext()); }
<YYINITIAL> {INCREMENTO}    { return new Symbol(sym.INCREMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DECREMENTO}    { return new Symbol(sym.DECREMENTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {IGUAL}	    { return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERENTE}	    { return new Symbol(sym.DIFERENTE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORQUE}	    { return new Symbol(sym.MAYORQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORQUE}	    { return new Symbol(sym.MENORQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORIGUALQUE} { return new Symbol(sym.MAYORIGUALQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORIGUALQUE} { return new Symbol(sym.MENORIGUALQUE, yyline, yycolumn, yytext()); }

<YYINITIAL> {AND}           { return new Symbol(sym.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {OR}            { return new Symbol(sym.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT}           { return new Symbol(sym.NOT, yyline, yycolumn, yytext()); }

<YYINITIAL> {PAR_IZQ}	    { return new Symbol(sym.PAR_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR_DER}	    { return new Symbol(sym.PAR_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLA_DER}	    { return new Symbol(sym.LLA_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLA_IZQ}	    { return new Symbol(sym.LLA_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO}	    { return new Symbol(sym.PUNTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {COMA}	    { return new Symbol(sym.COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO_COMA}    { return new Symbol(sym.PUNTO_COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOS_PUNTOS}    { return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn, yytext()); }

//COMENTARIO
{Comentario}                      { /* ignore */ }

<YYINITIAL> {SPACE}     { /*Espacios en blanco, ignorados*/ }
<YYINITIAL> {ENTER}     { /*Saltos de linea, ignorados*/}

<YYINITIAL>. {
        String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.err.println(errLex);
}