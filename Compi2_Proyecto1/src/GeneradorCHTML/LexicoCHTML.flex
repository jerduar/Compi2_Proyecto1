package GeneradorCHTML;
import java_cup.runtime.*;
import java.util.ArrayList;

%%
%{
public String colector_cadena = "";
public String comentario = "";  
%}

%public
%class scannerCH
%cupsym symCH
%cup    
%char
%column
%full
%line
%unicode

digito = [0-9] //E.R. que reconoce el intervalo de dígitos del 0 al 9.
numero = {digito}+("." {digito}+)?//E.R. que reconoce tanto decimales como enteros.

Letra = [a-zA-Z] //E.R. que reconoce el intervalo de letras de la a hasta z minúsculas y mayúsculas.
identificador = {Letra}+({Letra}|{numero}|"_")*//E.R. para reconocer identificadores
inicio = "--"
otro_simb = ["%"|"&"|"{"|"-"|"!"|"#"|"$"|"/"|"("|")"|"="|"?"|"¿"|"¡"|"°"|"¬"|"}"|"^"|"_"|"+"|"*"]  

cadena_dos = "\""[^\"]*"\""




%state COMENTARIO
%state MULTI
%state STRING_CENTRO
%%

<YYINITIAL>{
 

    "//"                    {yybegin(COMENTARIO);}
    "<//-"                  {yybegin(MULTI);}
    {inicio}                {yybegin(STRING_CENTRO);}
    
  
    "<"                     {return new Symbol(symCH.menor, yycolumn, yyline, yytext()); }
    ">"                     {return new Symbol(symCH.mayor, yycolumn, yyline, yytext());}
    "("                     {return new Symbol(symCH.pr_parA, yycolumn, yyline, yytext());}
    ")"                     {return new Symbol(symCH.pr_parC, yycolumn, yyline, yytext());}
    
    
    ","                     {return new Symbol(symCH.coma, yycolumn, yyline, yytext());}
    ";"                     {return new Symbol(symCH.p_coma, yycolumn, yyline, yytext());}
    "="                     {return new Symbol(symCH.igual, yycolumn, yyline, yytext());}


    
    "TITULO"                {return new Symbol(symCH.pr_titulo, yycolumn, yyline, yytext());}
    "FIN-TITULO"            {return new Symbol(symCH.pr_fintitulo, yycolumn, yyline, yytext());}
    "CHTML"                 {return new Symbol(symCH.pr_chtml, yycolumn, yyline, yytext());}
    "FIN-CHTML"             {return new Symbol(symCH.pr_finchtml, yycolumn, yyline, yytext());}
    "ENCABEZADO"            {return new Symbol(symCH.pr_encabezado, yycolumn, yyline, yytext());}
    "FIN-ENCABEZADO"        {return new Symbol(symCH.pr_finencabezado, yycolumn, yyline, yytext());}
    "CJS"                   {return new Symbol(symCH.pr_cjs, yycolumn, yyline, yytext());}
    "FIN-CJS"               {return new Symbol(symCH.pr_fincjs, yycolumn, yyline, yytext());}
    "CCSS"                  {return new Symbol(symCH.pr_ccss, yycolumn, yyline, yytext());}
    "FIN-CCSS"              {return new Symbol(symCH.pr_finccss, yycolumn, yyline, yytext());}
    "CUERPO"                {return new Symbol(symCH.pr_cuerpo, yycolumn, yyline, yytext());}
    "FIN-CUERPO"            {return new Symbol(symCH.pr_fincuerpo, yycolumn, yyline, yytext());}
    "PANEL"                 {return new Symbol(symCH.pr_panel, yycolumn, yyline, yytext());}
    "FIN-PANEL"             {return new Symbol(symCH.pr_finpanel, yycolumn, yyline, yytext());}
    "TEXTO"                 {return new Symbol(symCH.pr_texto, yycolumn, yyline, yytext());}
    "FIN-TEXTO"             {return new Symbol(symCH.pr_fintexto, yycolumn, yyline, yytext());}

    "CAJA_TEXTO"            {return new Symbol(symCH.pr_caja_texto, yycolumn, yyline, yytext());}
    "FIN-CAJA_TEXTO"        {return new Symbol(symCH.pr_fincaja_texto, yycolumn, yyline, yytext());}
    "IMAGEN"                {return new Symbol(symCH.pr_imagen, yycolumn, yyline, yytext());}
    "FIN-IMAGEN"            {return new Symbol(symCH.pr_finimagen, yycolumn, yyline, yytext());}
    "BOTON"                 {return new Symbol(symCH.pr_boton, yycolumn, yyline, yytext());}
    "FIN-BOTON"             {return new Symbol(symCH.pr_finboton, yycolumn, yyline, yytext());}
    "ENLACE"                {return new Symbol(symCH.pr_enlace, yycolumn, yyline, yytext());}
    "FIN-ENLACE"            {return new Symbol(symCH.pr_finenlace, yycolumn, yyline, yytext());}
    "TABLA"                 {return new Symbol(symCH.pr_tabla, yycolumn, yyline, yytext());}
    "FIN-TABLA"             {return new Symbol(symCH.pr_fintabla, yycolumn, yyline, yytext());}
    "FIL_T"                 {return new Symbol(symCH.pr_fil_t, yycolumn, yyline, yytext());}
    "FIN-FIL_T"             {return new Symbol(symCH.pr_finfil_t, yycolumn, yyline, yytext());}
    "CB"                    {return new Symbol(symCH.pr_cb, yycolumn, yyline, yytext());}
    "FIN-CB"                {return new Symbol(symCH.pr_fincb, yycolumn, yyline, yytext());}
    "CT"                    {return new Symbol(symCH.pr_ct, yycolumn, yyline, yytext());}
    "FIN-CT"                {return new Symbol(symCH.pr_finct, yycolumn, yyline, yytext());}
    "TEXTO_A"               {return new Symbol(symCH.pr_texto_a, yycolumn, yyline, yytext());}
    "FIN-TEXTO_A"           {return new Symbol(symCH.pr_fintexto_a, yycolumn, yyline, yytext());}
    "CAJA"                  {return new Symbol(symCH.pr_caja, yycolumn, yyline, yytext());}
    "FIN-CAJA"              {return new Symbol(symCH.pr_fincaja, yycolumn, yyline, yytext());}
    "OPCION"                {return new Symbol(symCH.pr_opcion, yycolumn, yyline, yytext());}
    "FIN-OPCION"            {return new Symbol(symCH.pr_finopcion, yycolumn, yyline, yytext());}
    "SPINNER"               {return new Symbol(symCH.pr_spinner, yycolumn, yyline, yytext());}
    "FIN-SPINNER"           {return new Symbol(symCH.pr_finspinner, yycolumn, yyline, yytext());}
    "SALTO-FIN"             {return new Symbol(symCH.pr_salto, yycolumn, yyline, yytext());}
    "click"                 {return new Symbol(symCH.pr_click, yycolumn, yyline, yytext());}
    "ruta"                  {return new Symbol(symCH.pr_ruta, yycolumn, yyline, yytext());}

    "id"                    {return new Symbol(symCH.pr_id, yycolumn, yyline, yytext());}
    "grupo"                 {return new Symbol(symCH.pr_grupo, yycolumn, yyline, yytext());}
    "alto"                  {return new Symbol(symCH.pr_alto, yycolumn, yyline, yytext());}
    "ancho"                 {return new Symbol(symCH.pr_ancho, yycolumn, yyline, yytext());}
    "alineado"              {return new Symbol(symCH.pr_alineado, yycolumn, yyline, yytext());}
    "ccss"                  {return new Symbol(symCH.pr_atri_ccss, yycolumn, yyline, yytext());}
    "valor"                 {return new Symbol(symCH.pr_valor, yycolumn, yyline, yytext());}
    
    {numero}                {return new Symbol(symCH.numero, yycolumn, yyline, yytext());}
    {identificador}         {return new Symbol(symCH.identificador, yycolumn, yyline, yytext());}
    {cadena_dos}            {return new Symbol(symCH.cadena, yycolumn, yyline, yytext());}
    {otro_simb}             {return new Symbol(symCH.otro_simb, yycolumn, yyline, yytext());}
    
}


<MULTI>
{
    "-//>"                  {System.out.println("Comentario multi: "+comentario);
                            comentario ="";
                            yybegin(YYINITIAL);
                            }
    .                       { comentario+= yytext();}
    [ \t\r\n\f]             {/*Se ignoran*/}
}


<COMENTARIO>
{
    [^"\n"]                 {comentario += yytext();}
    "\n"                    {
                            System.out.println("Comentario simple: "+comentario);
                            comentario = "";
                            yybegin(YYINITIAL);
                            }
}

<STRING_CENTRO>{
    [^"-/"]                 {colector_cadena += yytext();}
    "-/"                    {
                            String cadena_temporal = colector_cadena; 
                            colector_cadena = ""; 
                            yybegin(YYINITIAL); 
                            System.out.println("Comentario dentro etiquetas: "+cadena_temporal);
                            return new Symbol(symCH.cadena2, yycolumn, yyline, cadena_temporal);
           
     }

}

[ \t\r\n\f]                 {/*Se ignoran*/}

        .                   {System.out.println("simbolo: <<"+yytext()+">> ["+yyline+" , "+yycolumn+"]");
                            return new Symbol(symCH.ptodo, yycolumn, yyline, yytext());
                             //Listado_Errores.add(new Nodo_Error(yytext(),yycolumn,yyline,"Error Lexico","El caracter no pertenece al lenguaje"));   
                            }