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

digito = [0-9]
NUM = {digito}+("." {digito}+)?

Letra = [a-zA-Z]
ID = {Letra}+({Letra}|{NUM}|"_")*
ini_cad = [>](([ ]|\t|\r|\n|\f)*)({otro_simb}|{NUM}|{Letra})
cadena_dos = "\""[^\"]*"\""


COR_IZQ         = "["
COR_DER         = "]"
PAR_IZQ         = "("
PAR_DER         = ")"
MENOR           = "<"
MAYOR           = ">"
COMA            = "'"
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
    
    {MENOR}                     {return new Symbol(symCH.menor, yycolumn, yyline, yytext()); }
    {MAYOR}                     {return new Symbol(symCH.mayor, yycolumn, yyline, yytext());}
    {PAR_IZQ}                   {return new Symbol(symCH.pr_parA, yycolumn, yyline, yytext());}
    {PAR_DER}                   {return new Symbol(symCH.pr_parC, yycolumn, yyline, yytext());}    
    {COMA}                      {return new Symbol(symCH.coma, yycolumn, yyline, yytext());}
    {PUNTO_COMA}                {return new Symbol(symCH.p_coma, yycolumn, yyline, yytext());}
    {IGUAL}                     {return new Symbol(symCH.igual, yycolumn, yyline, yytext());}

    "CHTML"                     {return new Symbol(symCH.pr_chtml, yycolumn, yyline, yytext());}
    "FIN-CHTML"                 {return new Symbol(symCH.pr_finchtml, yycolumn, yyline, yytext());}
    "ENCABEZADO"                {return new Symbol(symCH.pr_encabezado, yycolumn, yyline, yytext());}
    "TITULO"                    {return new Symbol(symCH.pr_titulo, yycolumn, yyline, yytext());}
    "FIN-TITULO"                {return new Symbol(symCH.pr_fintitulo, yycolumn, yyline, yytext());}
    "CUERPO"                    {return new Symbol(symCH.pr_cuerpo, yycolumn, yyline, yytext());}
    "FIN-CUERPO"                {return new Symbol(symCH.pr_fincuerpo, yycolumn, yyline, yytext());}
    "PANEL"                     {return new Symbol(symCH.pr_panel, yycolumn, yyline, yytext());}
    "FIN-PANEL"                 {return new Symbol(symCH.pr_finpanel, yycolumn, yyline, yytext());}
    "TEXTO"                     {return new Symbol(symCH.pr_texto, yycolumn, yyline, yytext());}
    "FIN-TEXTO"                 {return new Symbol(symCH.pr_fintexto, yycolumn, yyline, yytext());}
    "CAJA_TEXTO"                {return new Symbol(symCH.pr_caja_texto, yycolumn, yyline, yytext());}
    "FIN-ENCABEZADO"            {return new Symbol(symCH.pr_finencabezado, yycolumn, yyline, yytext());}
    "CJS"                       {return new Symbol(symCH.pr_cjs, yycolumn, yyline, yytext());}
    "FIN-CJS"                   {return new Symbol(symCH.pr_fincjs, yycolumn, yyline, yytext());}
    "CCSS"                      {return new Symbol(symCH.pr_ccss, yycolumn, yyline, yytext());}
    "FIN-CCSS"                  {return new Symbol(symCH.pr_finccss, yycolumn, yyline, yytext());}
    "FIN-CAJA_TEXTO"            {return new Symbol(symCH.pr_fincaja_texto, yycolumn, yyline, yytext());}
    "IMAGEN"                    {return new Symbol(symCH.pr_imagen, yycolumn, yyline, yytext());}
    "FIN-IMAGEN"                {return new Symbol(symCH.pr_finimagen, yycolumn, yyline, yytext());}
    "BOTON"                     {return new Symbol(symCH.pr_boton, yycolumn, yyline, yytext());}
    "FIN-BOTON"                 {return new Symbol(symCH.pr_finboton, yycolumn, yyline, yytext());}
    "ENLACE"                    {return new Symbol(symCH.pr_enlace, yycolumn, yyline, yytext());}
    "FIN-ENLACE"                {return new Symbol(symCH.pr_finenlace, yycolumn, yyline, yytext());}
    "TABLA"                     {return new Symbol(symCH.pr_tabla, yycolumn, yyline, yytext());}
    "FIN-TABLA"                 {return new Symbol(symCH.pr_fintabla, yycolumn, yyline, yytext());}
    "FIL_T"                     {return new Symbol(symCH.pr_fil_t, yycolumn, yyline, yytext());}
    "FIN-FIL_T"                 {return new Symbol(symCH.pr_finfil_t, yycolumn, yyline, yytext());}
    "TEXTO_A"                   {return new Symbol(symCH.pr_texto_a, yycolumn, yyline, yytext());}
    "FIN-TEXTO_A"               {return new Symbol(symCH.pr_fintexto_a, yycolumn, yyline, yytext());}
    "CAJA"                      {return new Symbol(symCH.pr_caja, yycolumn, yyline, yytext());}
    "FIN-CAJA"                  {return new Symbol(symCH.pr_fincaja, yycolumn, yyline, yytext());}
    "OPCION"                    {return new Symbol(symCH.pr_opcion, yycolumn, yyline, yytext());}
    "FIN-OPCION"                {return new Symbol(symCH.pr_finopcion, yycolumn, yyline, yytext());}
    "SPINNER"                   {return new Symbol(symCH.pr_spinner, yycolumn, yyline, yytext());}
    "FIN-SPINNER"               {return new Symbol(symCH.pr_finspinner, yycolumn, yyline, yytext());}
    "SALTO-FIN"                 {return new Symbol(symCH.pr_salto, yycolumn, yyline, yytext());}
    "click"                     {return new Symbol(symCH.pr_click, yycolumn, yyline, yytext());}
    "CB"                        {return new Symbol(symCH.pr_cb, yycolumn, yyline, yytext());}
    "FIN-CB"                    {return new Symbol(symCH.pr_fincb, yycolumn, yyline, yytext());}
    "CT"                        {return new Symbol(symCH.pr_ct, yycolumn, yyline, yytext());}
    "FIN-CT"                    {return new Symbol(symCH.pr_finct, yycolumn, yyline, yytext());}
    "ruta"                      {return new Symbol(symCH.pr_ruta, yycolumn, yyline, yytext());}

    "id"                        {return new Symbol(symCH.pr_id, yycolumn, yyline, yytext());}
    "grupo"                     {return new Symbol(symCH.pr_grupo, yycolumn, yyline, yytext());}
    "alto"                      {return new Symbol(symCH.pr_alto, yycolumn, yyline, yytext());}
    "ancho"                     {return new Symbol(symCH.pr_ancho, yycolumn, yyline, yytext());}
    "alineado"                  {return new Symbol(symCH.pr_alineado, yycolumn, yyline, yytext());}
    "fondo"                     {return new Symbol(symCH.pr_fondo, yycolumn, yyline, yytext());}
    "ccss"                      {return new Symbol(symCH.pr_atri_ccss, yycolumn, yyline, yytext());}
    "valor"                     {return new Symbol(symCH.pr_valor, yycolumn, yyline, yytext());}
    
    {NUM}                       {return new Symbol(symCH.numero, yycolumn, yyline, yytext());}
    {ID}                        {return new Symbol(symCH.ID, yycolumn, yyline, yytext());}
    {cadena_dos}                {return new Symbol(symCH.cadena, yycolumn, yyline, yytext());}
    
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
                                return new Symbol(symCH.cadena2, yycolumn, yyline, cadena);
                                }
    .                           {buffer_cadena += yytext();}
    [ \t\r\n\f]                 {/*Se ignora*/}
}
    [ \t\r\n\f]                 {/*Se ignora*/}