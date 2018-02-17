package GeneradorCCSS;

import java_cup.runtime.Symbol;
import java.util.ArrayList;
//Inicio
%%
%{
//Código de Java
public String comentario = "";
//public ArrayList<Nodo_Error> Listado_Errores;  
%}


%cupsym symCCSS
%class scannerCCSS
%cup    
%public
%char
%column
%full
%line
%unicode
%ignorecase

//Definición de Estados
%state COMENTARIO_SIMPLE
%state COMENTARIO_MULTIPLE


digito = [0-9]
NUM = {digito}+("."{digito}+)?

Letra = [a-zA-Z] 
ID = ({Letra}|"_")+({Letra}|{NUM}|"_")*

CADENA = "\""[^\"]*"\""

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
%%
//Fin 
    
<YYINITIAL>"["                     {return new Symbol(symCCSS.COR_IZQ, yycolumn, yyline, yytext()); }
<YYINITIAL>"]"                     {return new Symbol(symCCSS.COR_DER, yycolumn, yyline, yytext());}
<YYINITIAL>"("                     {return new Symbol(symCCSS.PAR_IZQ, yycolumn, yyline, yytext());}
<YYINITIAL>")"                     {return new Symbol(symCCSS.PAR_DER, yycolumn, yyline, yytext());}
<YYINITIAL>";"                     {return new Symbol(symCCSS.PUNTO_COMA, yycolumn, yyline, yytext());}
<YYINITIAL>":="                    {return new Symbol(symCCSS.IGUAL, yycolumn, yyline, yytext());}
<YYINITIAL>","                     {return new Symbol(symCCSS.COMA, yycolumn, yyline, yytext());}
<YYINITIAL>"+"                     {return new Symbol(symCCSS.MAS, yycolumn, yyline, yytext());}
<YYINITIAL>"-"                     {return new Symbol(symCCSS.MENOS, yycolumn, yyline, yytext());}
<YYINITIAL>"*"                     {return new Symbol(symCCSS.POR, yycolumn, yyline, yytext());}
<YYINITIAL>"/"                     {return new Symbol(symCCSS.DIV, yycolumn, yyline, yytext());}

<YYINITIAL>"negrilla"          {return new Symbol(symCCSS.t_negrilla, yycolumn, yyline, yytext());}
<YYINITIAL>"cursiva"           {return new Symbol(symCCSS.t_cursiva, yycolumn, yyline, yytext());}
<YYINITIAL>"mayuscula"         {return new Symbol(symCCSS.t_mayuscula, yycolumn, yyline, yytext());}
<YYINITIAL>"minuscula"         {return new Symbol(symCCSS.t_minuscula, yycolumn, yyline, yytext());}
<YYINITIAL>"capital-t"         {return new Symbol(symCCSS.t_capital, yycolumn, yyline, yytext());}
<YYINITIAL>"true"              {return new Symbol(symCCSS.TRUE, yycolumn, yyline, yytext());}
<YYINITIAL>"false"             {return new Symbol(symCCSS.FALSE, yycolumn, yyline, yytext());}
<YYINITIAL>"alineado"          {return new Symbol(symCCSS.t_alineado, yycolumn, yyline, yytext());}
<YYINITIAL>"texto"             {return new Symbol(symCCSS.t_texto, yycolumn, yyline, yytext());}
<YYINITIAL>"formato"           {return new Symbol(symCCSS.t_formato, yycolumn, yyline, yytext());}
<YYINITIAL>"letra"             {return new Symbol(symCCSS.t_letra, yycolumn, yyline, yytext());}
<YYINITIAL>"tamtex"            {return new Symbol(symCCSS.t_tamTex, yycolumn, yyline, yytext());}
<YYINITIAL>"fondoelemento"     {return new Symbol(symCCSS.t_fondoElemento, yycolumn, yyline, yytext());}
<YYINITIAL>"autoredimension"   {return new Symbol(symCCSS.t_autoredimension, yycolumn, yyline, yytext());}
<YYINITIAL>"visible"           {return new Symbol(symCCSS.t_visible, yycolumn, yyline, yytext());}
<YYINITIAL>"borde"             {return new Symbol(symCCSS.t_borde, yycolumn, yyline, yytext());}
<YYINITIAL>"opaque"            {return new Symbol(symCCSS.t_opaque, yycolumn, yyline, yytext());}
<YYINITIAL>"colortext"         {return new Symbol(symCCSS.t_colorText, yycolumn, yyline, yytext());}
<YYINITIAL>"id"                {return new Symbol(symCCSS.t_id, yycolumn, yyline, yytext());}
<YYINITIAL>"grupo"             {return new Symbol(symCCSS.t_grupo, yycolumn, yyline, yytext());}
    
<YYINITIAL>{NUM}             {return new Symbol(symCCSS.NUM, yycolumn, yyline, yytext());}
<YYINITIAL>{ID}                 {return new Symbol(symCCSS.ID, yycolumn, yyline, yytext());}
<YYINITIAL>{CADENA}             {return new Symbol(symCCSS.CADENA, yycolumn, yyline, yytext());}
<YYINITIAL>{WhiteSpace}         {/*Se ignoran*/}
<YYINITIAL>{Comment}            {/*Se ignoran*/}
    
<YYINITIAL>.                    {System.out.println("Error: <<"+yytext()+">> ["+yyline+" , "+yycolumn+"]");
                                /*return new Symbol(symCCSS.error, yycolumn, yyline, yytext());*/
                                //Listado_Errores.add(new Nodo_Error(yytext(),yycolumn,yyline,"Error Lexico","El caracter no pertenece al lenguaje"));   
                                }
