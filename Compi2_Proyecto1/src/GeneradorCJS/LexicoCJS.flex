package GeneradorCJS;

import java_cup.runtime.Symbol;

//Scanner para generar C3D
%%
%{
    //Código de usuario
    

%}

%cupsym symCJS
%cup
%class scannerCJS
%public
%line
%char
%column
%full
%ignorecase

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*
/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

MAS		= "+"
MENOS		= "-"
MULT       	= "*"
DIV             = "/"
INCREMENTO      = "++"
DECREMENTO      = "--"

IGUAL           = "=="
DIFERENTE       = "!="
MAYORQUE        = ">"
MENORQUE        = "<"
MAYORIGUALQUE   = ">="
MENORIGUALQUE   = "<="

AND             = "&&"
OR              = "||"
NOT             = "!"

PAR_IZQ         = "("
PAR_DER         = ")"
LLA_IZQ         = "{"
LLA_DER         = "}"

PUNTO           = "."
PUNTO_COMA      = ";"
COMA            = ","
DOS_PUNTOS      = ":"

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
<YYINITIAL> "mientras"      { return new Symbol(symCJS.MIENTRAS, yyline, yycolumn, yytext()); }
<YYINITIAL> "'True'"        { return new Symbol(symCJS.TRUE, yyline, yycolumn, yytext()); }
<YYINITIAL> "'False'"       { return new Symbol(symCJS.FALSE, yyline, yycolumn, yytext()); }
<YYINITIAL> "funcion"       { return new Symbol(symCJS.FUNCION, yyline, yycolumn, yytext()); }
<YYINITIAL> "para"          { return new Symbol(symCJS.PARA, yyline, yycolumn, yytext()); }
<YYINITIAL> "si"            { return new Symbol(symCJS.SI, yyline, yycolumn, yytext()); }
<YYINITIAL> "sino"          { return new Symbol(symCJS.SINO, yyline, yycolumn, yytext()); }
<YYINITIAL> "imprimir"      { return new Symbol(symCJS.IMPRIMIR, yyline, yycolumn, yytext()); }
<YYINITIAL> "DimV"          { return new Symbol(symCJS.DIMV, yyline, yycolumn, yytext()); }
<YYINITIAL> "Retornar"      { return new Symbol(symCJS.RETORNAR, yyline, yycolumn, yytext()); }
<YYINITIAL> "Detener"       { return new Symbol(symCJS.DETENER, yyline, yycolumn, yytext()); }
<YYINITIAL> "Selecciona"    { return new Symbol(symCJS.SELECCIONAR, yyline, yycolumn, yytext()); }
<YYINITIAL> "Caso"          { return new Symbol(symCJS.CASO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Defecto"       { return new Symbol(symCJS.DEFECTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Documento"     { return new Symbol(symCJS.DOCUMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Observador"    { return new Symbol(symCJS.OBSERVADOR, yyline, yycolumn, yytext()); }
<YYINITIAL> "aTexto"        { return new Symbol(symCJS.ATEXTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "SetElemento"   { return new Symbol(symCJS.SETELEMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Mensaje"       { return new Symbol(symCJS.MENSAJE, yyline, yycolumn, yytext()); }
<YYINITIAL> "Mensaje"       { return new Symbol(symCJS.MENSAJE, yyline, yycolumn, yytext()); }
<YYINITIAL> "Obtener"       { return new Symbol(symCJS.OBTENER, yyline, yycolumn, yytext()); }
<YYINITIAL> "Conteo"       { return new Symbol(symCJS.CONTEO, yyline, yycolumn, yytext()); }

<YYINITIAL> {ID}            { return new Symbol(symCJS.ID, yyline, yycolumn, yytext()); }
<YYINITIAL> {CADENA}        { return new Symbol(symCJS.CADENA, yyline, yycolumn, yytext()); }
<YYINITIAL> {NUMERICO}      { return new Symbol(symCJS.NUMERICO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DATE}          { return new Symbol(symCJS.DATE, yyline, yycolumn, yytext()); }
<YYINITIAL> {DATE_T}        { return new Symbol(symCJS.DATE_T, yyline, yycolumn, yytext()); }



<YYINITIAL> {MAS}           { return new Symbol(symCJS.MAS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOS}	    { return new Symbol(symCJS.MENOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MULT}          { return new Symbol(symCJS.MULT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIV}	    { return new Symbol(symCJS.DIV, yyline, yycolumn, yytext()); }
<YYINITIAL> {INCREMENTO}    { return new Symbol(symCJS.INCREMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DECREMENTO}    { return new Symbol(symCJS.DECREMENTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {IGUAL}	    { return new Symbol(symCJS.IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERENTE}	    { return new Symbol(symCJS.DIFERENTE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORQUE}	    { return new Symbol(symCJS.MAYORQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORQUE}	    { return new Symbol(symCJS.MENORQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORIGUALQUE} { return new Symbol(symCJS.MAYORIGUALQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORIGUALQUE} { return new Symbol(symCJS.MENORIGUALQUE, yyline, yycolumn, yytext()); }

<YYINITIAL> {AND}           { return new Symbol(symCJS.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {OR}            { return new Symbol(symCJS.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT}           { return new Symbol(symCJS.NOT, yyline, yycolumn, yytext()); }

<YYINITIAL> {PAR_IZQ}	    { return new Symbol(symCJS.PAR_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR_DER}	    { return new Symbol(symCJS.PAR_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLA_DER}	    { return new Symbol(symCJS.LLA_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLA_IZQ}	    { return new Symbol(symCJS.LLA_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO}	    { return new Symbol(symCJS.PUNTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {COMA}	    { return new Symbol(symCJS.COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO_COMA}    { return new Symbol(symCJS.PUNTO_COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOS_PUNTOS}    { return new Symbol(symCJS.DOS_PUNTOS, yyline, yycolumn, yytext()); }

//COMENTARIO
<YYINITIAL>{Comment}        { /*Comentarios Ignorados*/ }

<YYINITIAL> {SPACE}     { /*Espacios en blanco, ignorados*/ }
<YYINITIAL> {ENTER}     { /*Saltos de linea, ignorados*/}

<YYINITIAL>. {
        String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.err.println(errLex);
}