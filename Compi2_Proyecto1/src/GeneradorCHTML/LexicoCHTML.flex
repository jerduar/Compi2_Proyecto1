package GeneradorCHTML;
import java_cup.runtime.*;
import java.util.ArrayList;

%%
%{
public String buffer_cadena = "";
public String comentario = "";
 
%}

%public
%class LexicoCH
%cupsym symCH
%cup    
%char
%column
%full
%line
%unicode
%ignorecase

N = [0-9]
NUM = {N}+("." {N}+)?
Letra = [a-zA-Z]
ID = {Letra}+({Letra}|{NUM}|"_")*
otro_simb = ["%"|"&"|"{"|"-"|"!"|"#"|"$"|"/"|"("|")"|"="|"?"|"¿"|"¡"|"°"|"¬"|"}"|"^"|"_"|"+"|"*"|":"|"."|","|";"]+  
ini_cad = [>](([ ]|\t|\r|\n|\f)*)({otro_simb}|{NUM}|{Letra})
CAD = "\""[^\"]*"\""



MENOR           = "<"
MAYOR           = ">"
PUNTO_COMA      = ";"
IGUAL           = "="

%state COMENTARIO
%state MULTI
%state CADENA_MEDIO

%%

<YYINITIAL>{
 

    "//"                        {yybegin(COMENTARIO);}
    "<//-"                      {yybegin(MULTI);}
    {ini_cad}                   {buffer_cadena = yytext();yybegin(CADENA_MEDIO);}
    
    {MENOR}                     {return new Symbol(symCH.MENOR, yycolumn, yyline, yytext()); }
    {MAYOR}                     {return new Symbol(symCH.MAYOR, yycolumn, yyline, yytext());}
    {PUNTO_COMA}                {return new Symbol(symCH.PUNTO_COMA, yycolumn, yyline, yytext());}
    {IGUAL}                     {return new Symbol(symCH.IGUAL, yycolumn, yyline, yytext());}

    "CHTML"                     {return new Symbol(symCH.chtml, yycolumn, yyline, yytext());}
    "FIN-CHTML"                 {return new Symbol(symCH.finchtml, yycolumn, yyline, yytext());}
    "ENCABEZADO"                {return new Symbol(symCH.encabezado, yycolumn, yyline, yytext());}
    "TITULO"                    {return new Symbol(symCH.titulo, yycolumn, yyline, yytext());}
    "FIN-TITULO"                {return new Symbol(symCH.fintitulo, yycolumn, yyline, yytext());}
    "CUERPO"                    {return new Symbol(symCH.cuerpo, yycolumn, yyline, yytext());}
    "FIN-CUERPO"                {return new Symbol(symCH.fincuerpo, yycolumn, yyline, yytext());}
    "PANEL"                     {return new Symbol(symCH.panel, yycolumn, yyline, yytext());}
    "FIN-PANEL"                 {return new Symbol(symCH.finpanel, yycolumn, yyline, yytext());}
    "TEXTO"                     {return new Symbol(symCH.texto_t, yycolumn, yyline, yytext());}
    "FIN-TEXTO"                 {return new Symbol(symCH.fintexto, yycolumn, yyline, yytext());}
    "CAJA_TEXTO"                {return new Symbol(symCH.caja_texto, yycolumn, yyline, yytext());}
    "FIN-ENCABEZADO"            {return new Symbol(symCH.finencabezado, yycolumn, yyline, yytext());}
    "CJS"                       {return new Symbol(symCH.cjs, yycolumn, yyline, yytext());}
    "FIN-CJS"                   {return new Symbol(symCH.fincjs, yycolumn, yyline, yytext());}
    "CCSS"                      {return new Symbol(symCH.ccss, yycolumn, yyline, yytext());}
    "FIN-CCSS"                  {return new Symbol(symCH.finccss, yycolumn, yyline, yytext());}
    "FIN-CAJA_TEXTO"            {return new Symbol(symCH.fincaja_texto, yycolumn, yyline, yytext());}
    "IMAGEN"                    {return new Symbol(symCH.imagen, yycolumn, yyline, yytext());}
    "FIN-IMAGEN"                {return new Symbol(symCH.finimagen, yycolumn, yyline, yytext());}
    "BOTON"                     {return new Symbol(symCH.boton, yycolumn, yyline, yytext());}
    "FIN-BOTON"                 {return new Symbol(symCH.finboton, yycolumn, yyline, yytext());}
    "ENLACE"                    {return new Symbol(symCH.enlace, yycolumn, yyline, yytext());}
    "FIN-ENLACE"                {return new Symbol(symCH.finenlace, yycolumn, yyline, yytext());}
    "TABLA"                     {return new Symbol(symCH.tabla, yycolumn, yyline, yytext());}
    "FIN-TABLA"                 {return new Symbol(symCH.fintabla, yycolumn, yyline, yytext());}
    "FIL_T"                     {return new Symbol(symCH.fil_t, yycolumn, yyline, yytext());}
    "FIN-FIL_T"                 {return new Symbol(symCH.finfil_t, yycolumn, yyline, yytext());}
    "TEXTO_A"                   {return new Symbol(symCH.texto_a, yycolumn, yyline, yytext());}
    "FIN-TEXTO_A"               {return new Symbol(symCH.fintexto_a, yycolumn, yyline, yytext());}
    "CAJA"                      {return new Symbol(symCH.caja, yycolumn, yyline, yytext());}
    "FIN-CAJA"                  {return new Symbol(symCH.fincaja, yycolumn, yyline, yytext());}
    "OPCION"                    {return new Symbol(symCH.opcion, yycolumn, yyline, yytext());}
    "FIN-OPCION"                {return new Symbol(symCH.finopcion, yycolumn, yyline, yytext());}
    "SPINNER"                   {return new Symbol(symCH.spinner, yycolumn, yyline, yytext());}
    "FIN-SPINNER"               {return new Symbol(symCH.finspinner, yycolumn, yyline, yytext());}
    "SALTO-FIN"                 {return new Symbol(symCH.salto, yycolumn, yyline, yytext());}
    "click"                     {return new Symbol(symCH.click, yycolumn, yyline, yytext());}
    "CB"                        {return new Symbol(symCH.cb, yycolumn, yyline, yytext());}
    "FIN-CB"                    {return new Symbol(symCH.fincb, yycolumn, yyline, yytext());}
    "CT"                        {return new Symbol(symCH.ct, yycolumn, yyline, yytext());}
    "FIN-CT"                    {return new Symbol(symCH.finct, yycolumn, yyline, yytext());}
    "ruta"                      {return new Symbol(symCH.ruta, yycolumn, yyline, yytext());}
    "id"                        {return new Symbol(symCH.id, yycolumn, yyline, yytext());}
    "grupo"                     {return new Symbol(symCH.grupo, yycolumn, yyline, yytext());}
    "alto"                      {return new Symbol(symCH.alto, yycolumn, yyline, yytext());}
    "fondo"                     {return new Symbol(symCH.fondo, yycolumn, yyline, yytext());}
    "valor"                     {return new Symbol(symCH.valor, yycolumn, yyline, yytext());}
    "ancho"                     {return new Symbol(symCH.ancho, yycolumn, yyline, yytext());}
    "alineado"                  {return new Symbol(symCH.alineado, yycolumn, yyline, yytext());}
    
    
    {NUM}                       {return new Symbol(symCH.NUM, yycolumn, yyline, yytext());}
    {ID}                        {return new Symbol(symCH.ID, yycolumn, yyline, yytext());}
    {CAD}                       {return new Symbol(symCH.CAD, yycolumn, yyline, yytext());}
    
}


<MULTI>
{
    "-//>"                      {
                                comentario ="";
                                yybegin(YYINITIAL);
                                }
    .                           {comentario+= yytext();}
    [ \t\r\n\f]                 {/*Se ignora*/}
}


<COMENTARIO>
{
    [^"\n"]                     {comentario += yytext();}
    "\n"                        {comentario = "";yybegin(YYINITIAL);}
}

<CADENA_MEDIO>{
    "<"                         {String cadena = buffer_cadena; 
                                buffer_cadena = ""; 
                                yybegin(YYINITIAL);
                                return new Symbol(symCH.CAD2, yycolumn, yyline, cadena);
                                }
    .                           {buffer_cadena += yytext();}
    [ \t\r\n\f]                 {/*Se ignora*/}
}
    [ \t\r\n\f]                 {/*Se ignora*/}