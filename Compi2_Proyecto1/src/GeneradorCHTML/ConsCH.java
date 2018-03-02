/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCHTML;

/**
 *
 * @author jerdu
 */
public class ConsCH {
    public static final Integer CHTML = 0;
    public static final Integer PROPIEDADES = 1;
    public static final Integer CLICK = 2;
    public static final Integer ID = 3;
    public static final Integer GRUPO = 4;
    public static final Integer ALTO = 5;
    public static final Integer ALINEADO = 6;
    public static final Integer RUTA = 7;
    public static final Integer VALOR = 8;
    public static final Integer FONDO = 9;
    public static final Integer CONTENIDO = 10;
    public static final Integer TITULO = 11;
    public static final Integer ENCABEZADO = 12;
    public static final Integer CUERPO = 13;
    public static final Integer PANEL = 14;
    public static final Integer CCSS = 15;
    public static final Integer CJS = 16;
    public static final Integer TEXTO = 17;
    public static final Integer TEXTO_A = 18;
    public static final Integer CAJA_TEXTO = 19;
    public static final Integer IMAGEN = 20;
    public static final Integer BOTON = 21;
    public static final Integer ENLACE = 22;
    public static final Integer SPINNER = 23;
    public static final Integer CAJA = 24;
    public static final Integer TABLA = 25;
    public static final Integer SALTO_FIN = 26;
    public static final Integer OPCIONES = 27;
    public static final Integer OPCION = 28;
    public static final Integer FIL_T = 29;
    public static final Integer CB = 30;
    public static final Integer CT = 31;
    public static final Integer VACIO = 32;
    
    public static final Integer TXT = 33;
    public static final Integer CAD = 34;
    public static final Integer ANCHO = 35;
    
    public static String ROL[] = {"CHTML","PROPIED","CLICK","ID","GRUPO","ALTO","ALINEADO","RUTA","VALOR","FONDO",
                                    "CONTEN","TITULO","ENCABE","CUERPO","PANEL","CCSS","CJS","TEXTO","TEXTO_A","CAJA_TEXTO",
                                    "IMG","BOTON","ENLAC","SPIN","CAJA","TABLA","BR","OPCS","OPC","FIL_T",
                                    "CB","CT","VACIO","TXT","CAD","ANCHO"};
    
    public static String RetornStringCH(Integer i){
        return (i<0 || i>35) ? "" : ROL[i];
    }
}
