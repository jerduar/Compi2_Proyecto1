/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import InterpreteCSJ.Recolector.ConsJS;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author jerdu
 */
public class Auxiliar {

    public static boolean esTipo(Integer i, Integer cons) {
        return (Objects.equals(i, cons));
    }

    public static Double ToNum(String val) {
        return Double.parseDouble(val);
    }

    public static boolean esError(Integer tipo) {
        if (esTipo(tipo, ConsJS.ERROR)) {
            return true;
        }
        if (esTipo(tipo, ConsJS.VOID)) {
            return true;
        }
        return false;
    }

    public static boolean esDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            Date result = df.parse(date);
            return true;
        } catch (ParseException pe) {
            return false;
        }

    }

    public static boolean esDateT(String dateh) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
            df.setLenient(false);
            Date result = df.parse(dateh);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }
    
    public static Date toDateH(String dateh){
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
            df.setLenient(false);
            Date result = df.parse(dateh);
            return result;
        } catch (ParseException pe) {
            return null;
        }
    }
    
    public static Date toDate(String date){
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            Date result = df.parse(date);
            return result;
        } catch (ParseException pe) {
            return null;
        }
    }

    public static boolean esDateoDT(Integer valor) {
        return esTipo(valor, ConsJS.FECHA) || esTipo(valor, ConsJS.FECHA_HORA);
    }

    public static boolean toBool(String val) {
        return val.equals("1");
    }
    
    public static String BoolToStr(boolean val){
        return (val) ? "1" : "0";
    }

    public static Double BoolToNum(String bool) {
        if (bool.equals("1")) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

}
