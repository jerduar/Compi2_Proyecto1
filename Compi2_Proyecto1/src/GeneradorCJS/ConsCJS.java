/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCJS;

/**
 *
 * @author jerdu
 */
public class ConsCJS {
    //INICIAL, A, L1, DEC, FUN, IF, LLAMADA, ASIG, L_SEN, SEN, SWITCH, FOR, LID, MESSAGE, SOUT, LE, E, LCASO, CAS, N, RET, WHILE, OP, SET, OBS, ARREGLO;
    //NO TERMINALES
    public static final Integer INICIO = 0;
    public static final Integer L1 = 1;
    public static final Integer DEC = 2;
    public static final Integer FUN = 3;
    public static final Integer IF = 4;
    public static final Integer LLAM = 5;
    public static final Integer ASIG = 6;
    public static final Integer L_SEN = 7;
    public static final Integer SEN = 8;
    public static final Integer SWT = 9;
    public static final Integer FOR = 10;
    public static final Integer LID = 11;
    public static final Integer MSG = 12;
    public static final Integer SOUT = 13;
    public static final Integer LE = 14;
    public static final Integer E = 15;
    public static final Integer LCASO = 16;
    public static final Integer CASO = 17;
    public static final Integer N = 18;
    public static final Integer RET = 19;
    public static final Integer WHILE = 20;
    public static final Integer OP = 21;
    public static final Integer SET = 22;
    public static final Integer OBS = 23;
    public static final Integer ARR = 24;
    
    //CONSTANTES PARA LOS TIPOS DE DATOS
    public static final Integer numero = 25;
    public static final Integer cadena = 26;
    public static final Integer booleano = 27;
    public static final Integer fecha = 28;
    public static final Integer fecha_hora = 29;
    public static final Integer arreglo = 30;
    public static final Integer ID = 31;
    
    //terminal String MAS, MENOS, MULT, DIV, INCREMENTO, DECREMENTO, IGUAL, DIFERENTE, MAYORQUE, MENORQUE, MAYORIGUALQUE, 
    //MENORIGUALQUE, AND, OR, NOT, PAR_IZQ, PAR_DER, LLA_DER, LLA_IZQ, PUNTO, PUNTO_COMA, COMA, DOS_PUNTOS;
    //terminal String MIENTRAS, PARA, SI, SINO, IMPRIMIR, DIMV, RETORNAR, DETENER, SELECCIONAR, CASO, DEFECTO, DOCUMENTO, OBSERVADOR, ATEXTO, SETELEMENTO, MENSAJE, FUNCION, OBTENER, CONTEO;
    public static final Integer MAS = 32;
    public static final Integer MENOS = 33;
    public static final Integer MULT = 34;
    public static final Integer DIV = 35;
    public static final Integer INCREMENTO = 36;
    public static final Integer DECREMENTO = 37;
    public static final Integer IGUAL = 38;
    public static final Integer DIFERENTE = 39;
    public static final Integer MAYORQUE = 40;
    public static final Integer MENORQUE = 41;
    public static final Integer MAYORIGUALQUE = 42;
    public static final Integer MENORIGUALQUE = 43;
    public static final Integer AND = 44;
    public static final Integer OR = 45;
    public static final Integer NOT = 46;
    
    //CASOS ESPECIALES
    public static final Integer IF_ELSE = 47;
    public static final Integer POS_ARRE = 48;
    public static final Integer LLAM_P = 49;
    public static final Integer DOC_OBT_SET = 50;
    public static final Integer DOC_OBT_OBS = 51;
    public static final Integer DOC_OBT = 52;
    public static final Integer CONTEO = 53;
    public static final Integer ATEXTO = 54;
    public static final Integer ID_CONT = 55;
    public static final Integer ID_SET = 56;
    public static final Integer ID_OBS = 57;
    public static final Integer DEF = 58;
    public static final Integer DET = 59;
    public static final Integer ID_ID = 59;
    public static final Integer FUN_ID = 60;
    public static final Integer FUN_ID2 = 61;
    public static final Integer FUN_SET = 62;
    public static final Integer FUN_OBS = 63;
    public static final Integer FUN_OBS2 = 64;
    public static final Integer FUN_SET2 = 65;
    
    private static String[] ROL = {"INICIO","L1","DEC","FUN","IF","LLAM","ASIG","L_SEN","SEN","SWITCH",
                                    "FOR","LID","MSG","SOUT","LE","E","LCASO","CASO","N","RET",
                                    "WHILE","OP","SET","OBS","ARR","NUM","CAD","BOOL","FECHA","FECHA_T",
                                    "ARRAY","ID","+","-","*","/","++","--","==","!=",
                                    ">","<",">=","<=","&&","||","!","IF_ELSE","POS_ARRE","LLAM_P","DOS",
                                    "DOO","DO","CONTEO","ATEX","IDC","IDS","IDO","DEF","DETENER","ID_ID",
                                    "FUN_ID2","FUN_SET","FUN_OBS","FUN_SET2","FUN_OBS2"};
    
    public static String RetornStringCSJ(Integer i){
        return (i<0 || i>65) ? "" : ROL[i];
    }
    
    
    
}
