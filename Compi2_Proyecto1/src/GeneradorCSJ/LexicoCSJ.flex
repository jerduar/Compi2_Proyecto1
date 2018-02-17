package GeneradorCSJ;

import java_cup.runtime.Symbol;

//Scanner para generar C3D
%%
%{
    //Código de usuario
    

%}

%cupsym symCSJ
%cup
%class scannerCSJ
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
<YYINITIAL> "mientras"      { return new Symbol(symCSJ.MIENTRAS, yyline, yycolumn, yytext()); }
<YYINITIAL> "'True'"        { return new Symbol(symCSJ.TRUE, yyline, yycolumn, yytext()); }
<YYINITIAL> "'False'"       { return new Symbol(symCSJ.FALSE, yyline, yycolumn, yytext()); }
<YYINITIAL> "funcion"       { return new Symbol(symCSJ.FUNCION, yyline, yycolumn, yytext()); }
<YYINITIAL> "para"          { return new Symbol(symCSJ.PARA, yyline, yycolumn, yytext()); }
<YYINITIAL> "si"            { return new Symbol(symCSJ.SI, yyline, yycolumn, yytext()); }
<YYINITIAL> "sino"          { return new Symbol(symCSJ.SINO, yyline, yycolumn, yytext()); }
<YYINITIAL> "imprimir"      { return new Symbol(symCSJ.IMPRIMIR, yyline, yycolumn, yytext()); }
<YYINITIAL> "DimV"          { return new Symbol(symCSJ.DIMV, yyline, yycolumn, yytext()); }
<YYINITIAL> "Retornar"      { return new Symbol(symCSJ.RETORNAR, yyline, yycolumn, yytext()); }
<YYINITIAL> "Detener"       { return new Symbol(symCSJ.DETENER, yyline, yycolumn, yytext()); }
<YYINITIAL> "Selecciona"    { return new Symbol(symCSJ.SELECCIONAR, yyline, yycolumn, yytext()); }
<YYINITIAL> "Caso"          { return new Symbol(symCSJ.CASO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Defecto"       { return new Symbol(symCSJ.DEFECTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Documento"     { return new Symbol(symCSJ.DOCUMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Observador"    { return new Symbol(symCSJ.OBSERVADOR, yyline, yycolumn, yytext()); }
<YYINITIAL> "aTexto"        { return new Symbol(symCSJ.ATEXTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "SetElemento"   { return new Symbol(symCSJ.SETELEMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> "Mensaje"       { return new Symbol(symCSJ.MENSAJE, yyline, yycolumn, yytext()); }
<YYINITIAL> "Mensaje"       { return new Symbol(symCSJ.MENSAJE, yyline, yycolumn, yytext()); }
<YYINITIAL> "Obtener"       { return new Symbol(symCSJ.OBTENER, yyline, yycolumn, yytext()); }
<YYINITIAL> "Conteo"       { return new Symbol(symCSJ.CONTEO, yyline, yycolumn, yytext()); }

<YYINITIAL> {ID}            { return new Symbol(symCSJ.ID, yyline, yycolumn, yytext()); }
<YYINITIAL> {CADENA}        { return new Symbol(symCSJ.CADENA, yyline, yycolumn, yytext()); }
<YYINITIAL> {NUMERICO}      { return new Symbol(symCSJ.NUMERICO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DATE}          { return new Symbol(symCSJ.DATE, yyline, yycolumn, yytext()); }
<YYINITIAL> {DATE_T}        { return new Symbol(symCSJ.DATE_T, yyline, yycolumn, yytext()); }



<YYINITIAL> {MAS}           { return new Symbol(symCSJ.MAS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOS}	    { return new Symbol(symCSJ.MENOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MULT}          { return new Symbol(symCSJ.MULT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIV}	    { return new Symbol(symCSJ.DIV, yyline, yycolumn, yytext()); }
<YYINITIAL> {INCREMENTO}    { return new Symbol(symCSJ.INCREMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DECREMENTO}    { return new Symbol(symCSJ.DECREMENTO, yyline, yycolumn, yytext()); }

<YYINITIAL> {IGUAL}	    { return new Symbol(symCSJ.IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERENTE}	    { return new Symbol(symCSJ.DIFERENTE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORQUE}	    { return new Symbol(symCSJ.MAYORQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORQUE}	    { return new Symbol(symCSJ.MENORQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORIGUALQUE} { return new Symbol(symCSJ.MAYORIGUALQUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORIGUALQUE} { return new Symbol(symCSJ.MENORIGUALQUE, yyline, yycolumn, yytext()); }

<YYINITIAL> {AND}           { return new Symbol(symCSJ.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {OR}            { return new Symbol(symCSJ.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT}           { return new Symbol(symCSJ.NOT, yyline, yycolumn, yytext()); }

<YYINITIAL> {PAR_IZQ}	    { return new Symbol(symCSJ.PAR_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR_DER}	    { return new Symbol(symCSJ.PAR_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLA_DER}	    { return new Symbol(symCSJ.LLA_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLA_IZQ}	    { return new Symbol(symCSJ.LLA_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO}	    { return new Symbol(symCSJ.PUNTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {COMA}	    { return new Symbol(symCSJ.COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO_COMA}    { return new Symbol(symCSJ.PUNTO_COMA, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOS_PUNTOS}    { return new Symbol(symCSJ.DOS_PUNTOS, yyline, yycolumn, yytext()); }

//COMENTARIO
<YYINITIAL>{Comment}        { /*Comentarios Ignorados*/ }

<YYINITIAL> {SPACE}     { /*Espacios en blanco, ignorados*/ }
<YYINITIAL> {ENTER}     { /*Saltos de linea, ignorados*/}

<YYINITIAL>. {
        String errLex = "Error léxico, caracter irreconocible: '"+yytext()+"' en la línea: "+(yyline+1)+" y columna: "+yycolumn;
        System.err.println(errLex);
}