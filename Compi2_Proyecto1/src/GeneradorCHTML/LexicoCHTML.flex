package GeneradorCHTML;

import java_cup.runtime.Symbol;
//Inicio
%%
%{

public String comentario = "";
public String texto = "";
  
%}

/*-------------------CONFIGURACIONES------------------*/
%cupsym symCH
%class scannerCH
%cup    
%public
%char
%column
%full
%line
%unicode
%ignorecase

/*--------------------ESTADOS--------------------------*/
%state COMENTARIO
%state TEXTO

/*------------------EXPRESIONES REGULARES--------------*/

digito = [0-9]
numero = {digito}+("."{digito}+)?

letra = [a-zA-Z]
identificador = ({letra}|"_")+({letra}|{numero}|"_")*

cadena = "\""[^\"]*"\""
inicioDeTexto = [>](([ ]|\t|\r|\n|\f)*)({simbolos}|{numero}|{letra})
simbolos = ["%"|"&"|"{"|"-"|"!"|"#"|"$"|"/"|"("|")"|"="|"?"|"¿"|"¡"|"°"|"¬"|"}"|"^"|"_"|"+"|"*"|":"|"."|","|";"]+  

limiteDeLinea = \r|\n|\r\n
espacioEnBlanco = {limiteDeLinea} | [ \t\f]

%%
//Fin

<YYINITIAL>{
    /*------------------------------------------------------------Estados-------------------------------------------------------------*/
    "<//-"                  {yybegin(COMENTARIO);}
    {inicioDeTexto}         {texto = yytext();
                             yybegin(TEXTO);}

    /*-----------------------------------------------------Signos de Agrupación-------------------------------------------------------*/
    "<"                     {return new Symbol(symCH.menor, yycolumn, yyline, yytext()); }
    ">"                     {return new Symbol(symCH.mayor, yycolumn, yyline, yytext());}
    "("                     {return new Symbol(symCH.pqa, yycolumn, yyline, yytext());}
    ")"                     {return new Symbol(symCH.pqc, yycolumn, yyline, yytext());}

    /*------------------------------------------------------Signos de Puntuación------------------------------------------------------*/
    ";"                     {return new Symbol(symCH.pyc, yycolumn, yyline, yytext());}
    "="                     {return new Symbol(symCH.igual, yycolumn, yyline, yytext());}
    ","                     {return new Symbol(symCH.coma, yycolumn, yyline, yytext());}
  
    /*--------------------------------------------------------Palabras Reservadas-----------------------------------------------------*/
    "chtml"                 {return new Symbol(symCH.t_chtml, yycolumn, yyline, yytext());}
    "fin-chtml"             {return new Symbol(symCH.t_fin_chtml, yycolumn, yyline, yytext());}
    "encabezado"            {return new Symbol(symCH.t_encabezado, yycolumn, yyline, yytext());}
   "fin-encabezado"         {return new Symbol(symCH.t_fin_encabezado, yycolumn, yyline, yytext());}
    "cjs"                   {return new Symbol(symCH.t_cjs, yycolumn, yyline, yytext());}
    "fin-cjs"               {return new Symbol(symCH.t_fin_cjs, yycolumn, yyline, yytext());}
    "titulo"                {return new Symbol(symCH.t_titulo, yycolumn, yyline, yytext());}
    "fin-titulo"            {return new Symbol(symCH.t_fin_titulo, yycolumn, yyline, yytext());}
    "cuerpo"                {return new Symbol(symCH.t_cuerpo, yycolumn, yyline, yytext());}
    "fin-cuerpo"            {return new Symbol(symCH.t_fin_cuerpo, yycolumn, yyline, yytext());}
    "panel"                 {return new Symbol(symCH.t_panel, yycolumn, yyline, yytext());}
    "fin-panel"             {return new Symbol(symCH.t_fin_panel, yycolumn, yyline, yytext());}
    "texto"                 {return new Symbol(symCH.t_texto, yycolumn, yyline, yytext());}
    "fin-texto"             {return new Symbol(symCH.t_fin_texto, yycolumn, yyline, yytext());}
    "caja_texto"            {return new Symbol(symCH.t_caja_texto, yycolumn, yyline, yytext());}
    "fin-caja_texto"        {return new Symbol(symCH.t_fin_caja_texto, yycolumn, yyline, yytext());}
    "imagen"                {return new Symbol(symCH.t_imagen, yycolumn, yyline, yytext());}
    "fin-imagen"            {return new Symbol(symCH.t_fin_imagen, yycolumn, yyline, yytext());}
    "boton"                 {return new Symbol(symCH.t_boton, yycolumn, yyline, yytext());}
    "fin-boton"             {return new Symbol(symCH.t_fin_boton, yycolumn, yyline, yytext());}
    "enlace"                {return new Symbol(symCH.t_enlace, yycolumn, yyline, yytext());}
    "fin-enlace"            {return new Symbol(symCH.t_fin_enlace, yycolumn, yyline, yytext());}
    "tabla"                 {return new Symbol(symCH.t_tabla, yycolumn, yyline, yytext());}
    "fin-tabla"             {return new Symbol(symCH.t_fin_tabla, yycolumn, yyline, yytext());}
    "fil_t"                 {return new Symbol(symCH.t_fil_t, yycolumn, yyline, yytext());}
    "fin-fil_t"             {return new Symbol(symCH.t_fin_fil_t, yycolumn, yyline, yytext());}
    "cb"                    {return new Symbol(symCH.t_cb, yycolumn, yyline, yytext());}
    "fin-cb"                {return new Symbol(symCH.t_fin_cb, yycolumn, yyline, yytext());}
    "ct"                    {return new Symbol(symCH.t_ct, yycolumn, yyline, yytext());}
    "fin-ct"                {return new Symbol(symCH.t_fin_ct, yycolumn, yyline, yytext());}
    "texto_a"               {return new Symbol(symCH.t_texto_a, yycolumn, yyline, yytext());}
    "fin-texto_a"           {return new Symbol(symCH.t_fin_texto_a, yycolumn, yyline, yytext());}
    "caja"                  {return new Symbol(symCH.t_caja, yycolumn, yyline, yytext());}
    "fin-caja"              {return new Symbol(symCH.t_fin_caja, yycolumn, yyline, yytext());}
    "opcion"                {return new Symbol(symCH.t_opcion, yycolumn, yyline, yytext());}
    "fin-opcion"            {return new Symbol(symCH.t_fin_opcion, yycolumn, yyline, yytext());}
    "spinner"               {return new Symbol(symCH.t_spinner, yycolumn, yyline, yytext());}
    "fin-spinner"           {return new Symbol(symCH.t_fin_spinner, yycolumn, yyline, yytext());}
    "salto-fin"             {return new Symbol(symCH.t_salto_fin, yycolumn, yyline, yytext());}
    
    /*-------------------------------------------------Palabras Reservadas de Propiedades----------------------------------------------*/
    "click"                 {return new Symbol(symCH.t_click, yycolumn, yyline, yytext());}
    "ruta"                  {return new Symbol(symCH.t_ruta, yycolumn, yyline, yytext());}
    "id"                    {return new Symbol(symCH.t_id, yycolumn, yyline, yytext());}
    "grupo"                 {return new Symbol(symCH.t_grupo, yycolumn, yyline, yytext());}
    "alto"                  {return new Symbol(symCH.t_alto, yycolumn, yyline, yytext());}
    "ancho"                 {return new Symbol(symCH.t_ancho, yycolumn, yyline, yytext());}
    "alineado"              {return new Symbol(symCH.t_alineado, yycolumn, yyline, yytext());}
    "fondo"                 {return new Symbol(symCH.t_fondo, yycolumn, yyline, yytext());}
    "ccss"                  {return new Symbol(symCH.t_ccss, yycolumn, yyline, yytext());}
    "fin-ccss"              {return new Symbol(symCH.t_fin_ccss, yycolumn, yyline, yytext());}
    "valor"                 {return new Symbol(symCH.t_valor, yycolumn, yyline, yytext());}



    /*-------------------------------------------------------Expresiones Regulares----------------------------------------------------*/
    {numero}                {return new Symbol(symCH.numero, yycolumn, yyline, yytext());}
    {identificador}         {return new Symbol(symCH.identificador, yycolumn, yyline, yytext());}
    {cadena}                {String lexema = yytext().substring(1,yytext().length()-1); lexema = lexema.replace("\n", ""); lexema = lexema.replace("\"", "\\\""); return new Symbol(symCH.cadena, yycolumn, yyline, lexema);}
    
    {espacioEnBlanco}            {/*ignorados*/}
    
    
    /*-------------------------------------------------------Error----------------------------------------------------*/
    .                       {System.err.println("Error: <<"+yytext()+">> ["+yyline+" , "+yycolumn+"]");
                             return new Symbol(symCH.error, yycolumn, yyline, yytext());
                                
                            }
}

<COMENTARIO>
{
    "-//>"                  {
                             comentario ="";
                             yybegin(YYINITIAL);
                            }
    .                       { 
                             comentario+= yytext();
                            }
    [ \t\r\n\f]             {/*se ignora*/}
}

<TEXTO>
{
    "<"                     {
                             String lexema = texto + yytext();
                             texto = "";
                             yybegin(YYINITIAL);
                             lexema = lexema.substring(1,lexema.length()-1);
                             lexema = lexema.replace("\n", ""); lexema = lexema.replace("\"", "\\\"");
                             return new Symbol(symCH.texto, yycolumn, yyline, lexema);
                            }
    .                       { 
                                texto += yytext();
                            }
    [ \t\r\n\f]             {/*Se ignora*/}
}