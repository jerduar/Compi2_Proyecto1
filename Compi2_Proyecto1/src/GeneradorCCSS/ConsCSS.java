/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCCSS;

/**
 *
 * @author jerdu
 */
public class ConsCSS {
    //non terminal Nodo INICIO, L_DEF, DEF, ATRIB, L_SEN, SEN, L_FORM, E, FORM, L_ATRIB;
    //NO TERMINALES
    
    public static final Integer INICIO = 0;
    public static final Integer L_DEF = 1;
    public static final Integer DEF = 2;
    public static final Integer ATRIB_ID = 3;
    public static final Integer L_SEN = 4;
    public static final Integer SEN = 5;
    public static final Integer L_FORM = 6;
    public static final Integer E = 7;
    public static final Integer FORM = 8;
    public static final Integer L_ATRIB = 9;
    
    //TERMINALES
    //terminal String formato, letra, tamTex, fondoElemento, autoredimension, visible, borde, opaque, colorText, id_token;
    //terminal String grupo, negrilla, cursiva, mayuscula, minuscula, capital, tamelemento;
    
    public static final Integer FORMATO = 10;
    public static final Integer LETRA = 11;
    public static final Integer TAMTEX = 12;
    public static final Integer FONDOELE = 13;
    public static final Integer AUTOREDIM = 14;
    public static final Integer VISIBLE = 15;
    public static final Integer BORDE = 16;
    public static final Integer OPAQUE = 17;
    public static final Integer COLOR_TEXT = 18;
    public static final Integer ID_TOKEN = 19;
    
    public static final Integer GRUPO = 20;
    public static final Integer NEGRILLA = 21;
    public static final Integer CURSIVA = 22;
    public static final Integer MAYUSCULA = 23;
    public static final Integer MINUSCULA = 24;
    public static final Integer CAPITAL = 25;
    public static final Integer TAMELE = 26;
    
    //terminal String COR_IZQ, COR_DER, PAR_IZQ, PAR_DER, PUNTO_COMA, IGUAL, MAS, MENOS, POR, DIV, alineado, texto;
    public static final Integer IGUAL = 27;
    public static final Integer MAS = 28;
    public static final Integer MENOS = 29;
    public static final Integer POR = 30;
    public static final Integer DIV = 31;
    
    //TIPOS DE DATO
    public static final Integer NUM = 32;
    public static final Integer ID = 33;
    public static final Integer CAD = 34;
    public static final Integer BOOL = 35;
    public static final Integer TEXTO = 36;
    
    public static final Integer ATRIB_G = 37;
    public static final Integer ALINEADO = 38;
    public static final Integer HORI = 39;
    public static final Integer VERTI = 40;
    public static final Integer JUSTIFICADO = 41;
    public static final Integer CENTRADO = 42;
    public static final Integer IZQ = 43;
    public static final Integer DER = 44;
    public static final Integer L_CAD = 45;
    
   
    private static String[] ROL = {"INICIO","L_DEF","DEF","ATRIB_ID","L_SEN","SEN","L_FORM","E","FORM","L_ATRIB",
                                    "formato","letra","tamtex","fondoele","autoredim","visible","borde","opaque","colortext","id_token",
                                    "grupo","negrilla","cursiva","mayus","minus","capital","tamele",":=","+","-",
                                    "*","/","NUM","ID","CAD","BOOL","TEXTO","ATRIB_G","ALINEADO","HORI","VERTI",
                                    "JUSTI","CENTR","IZQ","DER","L_CAD"};
    
    public static String RetornStringCSS(Integer i){
        return (i<0 || i>45) ? "" : ROL[i];
    }

}
