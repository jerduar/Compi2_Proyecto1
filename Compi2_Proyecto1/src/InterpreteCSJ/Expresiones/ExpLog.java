/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import InterpreteCSJ.Recolector.ConsJS;

/**
 *
 * @author jerduar
 */
public class ExpLog {
    
    public static Result negacion(Result izq){
        if(izq.getValor().equals("1")){
            return CrearResult("0");
        }else{
            return CrearResult("1");
        }
    }
    
    public static Result and(Result izq, Result der){
        boolean op1 = Auxiliar.toBool(izq.getValor());
        boolean op2 = Auxiliar.toBool(der.getValor());
        
        return CrearResult(Auxiliar.BoolToStr(op1 && op2));
    }
    
    public static Result or(Result izq, Result der){
        boolean op1 = Auxiliar.toBool(izq.getValor());
        boolean op2 = Auxiliar.toBool(der.getValor());
        
        return CrearResult(Auxiliar.BoolToStr(op1 || op2));
    }
    
    private static Result CrearResult(String val){
        Result r = new Result();
        r.setValor(val);
        r.setTipo(ConsJS.BOOL);
        return r;
    }
    
}
