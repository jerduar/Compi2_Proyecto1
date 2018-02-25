/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Recolector.ConsJS;
import java.util.Objects;

/**
 *
 * @author jerdu
 */
public class ExpArit {
    public static Result Incremento(Result num){
        Result respuesta = new Result();
        double resultado;
        
        resultado = Double.parseDouble(num.getValor())+1;
        respuesta.setTipo(ConsJS.NUM);
        respuesta.setValor(resultado+"");
        return respuesta;
    }
    
    public static Result Decremento(Result num){
        Result respuesta = new Result();
        double resultado;
        
        resultado = Double.parseDouble(num.getValor())-1;
        respuesta.setTipo(ConsJS.NUM);
        respuesta.setValor(resultado+"");
        return respuesta;
    }
    
    public static Result Suma(Result izq, Result der){
        Result respuesta = new Result();
                        

        //CADENA + CUALQUIER TIPO || CUALQUIER TIPO + CADENA (MENOS FECHA Y FECHA_T)
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD) || Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)){
            String cad = izq.getValor() + der.getValor();
            respuesta.setTipo(ConsJS.CAD);
            respuesta.setValor(cad);
            return respuesta;
        }
        
        if(Objects.equals(izq.getTipo(), ConsJS.NUM)){
            //NUM + NUM
            Double op1 = Auxiliar.ToNum(izq.getValor());
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                Double valor = op1 + Auxiliar.ToNum(der.getValor());
                respuesta.setTipo(ConsJS.NUM);
                respuesta.setValor(valor+"");
                return respuesta;
            }
            //NUM + BOOL
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                Double valor = op1 + Auxiliar.BoolToNum(der.getValor());
                respuesta.setTipo(ConsJS.NUM);
                respuesta.setValor(valor + "");
                return respuesta;
            }
        }else if(Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)){
            boolean op1 = Auxiliar.toBool(izq.getValor());
            //BOOL + BOOL
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                boolean res = op1 || Auxiliar.toBool(der.getValor());
                respuesta.setTipo(ConsJS.BOOL);
                respuesta.setValor(res+"");
                return respuesta;
            }
            
        }
        
        if(Auxiliar.esTipo(der.getTipo(),ConsJS.BOOL)){
            Double op1 = Auxiliar.BoolToNum(der.getValor());
            //BOOL + NUM
            if(Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)){
                op1 = op1 + Auxiliar.ToNum(izq.getValor());
                respuesta.setTipo(ConsJS.NUM);
                respuesta.setValor(op1+"");
                return respuesta;
            }
        }
        
        return respuesta;
    }
    
    public static Result Resta(Result izq, Result der){
        Result respuesta = new Result();
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)){
            Double op1 = Auxiliar.BoolToNum(izq.getValor());
            //BOOL - NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                op1 = op1 - Auxiliar.ToNum(der.getValor());
                respuesta.setTipo(der.getTipo());
                respuesta.setValor(op1+"");
                return respuesta;
            }
        }else if(Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)){
            Double op1 = Auxiliar.BoolToNum(izq.getValor());
            //NUM - NUMM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                op1 = op1 - Auxiliar.ToNum(der.getValor());
                respuesta.setValor(op1+"");
                respuesta.setTipo(izq.getTipo());
                return respuesta;
            }
            //NUM - BOOL
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                op1 = op1 - Auxiliar.BoolToNum(der.getValor());
                respuesta.setValor(op1+"");
                respuesta.setTipo(izq.getTipo());
                return respuesta;
            }
        }
        
        return respuesta;
        
    }
    
    public static Result Multiplicacion(Result izq, Result der){
        Result respuesta = new Result();
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)){
            //BOOL *(AND) BOOL
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                boolean r = Auxiliar.toBool(izq.getValor()) && Auxiliar.toBool(der.getValor());
                respuesta.setValor(r+"");
                respuesta.setTipo(izq.getTipo());
                return respuesta;
            }
            //BOOL * NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                Double r = Auxiliar.BoolToNum(izq.getValor()) * Auxiliar.ToNum(der.getValor());
                respuesta.setValor(r+"");
                respuesta.setTipo(der.getTipo());
                return respuesta;
            }
        }else if(Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)){
            //NUM * BOOL
            Double op1 = Auxiliar.ToNum(izq.getValor());
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                op1 *= Auxiliar.BoolToNum(der.getValor());
                respuesta.setTipo(izq.getTipo());
                respuesta.setValor(op1+"");
                return respuesta;
            }
            //NUM * NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                op1 *= Auxiliar.ToNum(der.getValor());
                respuesta.setValor(op1+"");
                respuesta.setTipo(izq.getTipo());
                return respuesta;
            }
        }
        
        return respuesta;
    }
    
    public static Result Division(Result izq, Result der){
        Result respuesta = new Result();
        
        return respuesta;
    }
}
