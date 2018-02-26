/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import InterpreteCSJ.Recolector.ConsJS;
import java.util.Objects;

/**
 *
 * @author jerdu
 */
public class ExpRel {
    public static Result Igual_Igual(Result izq, Result der){
        Result respuesta = new Result();
        boolean r;
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)){
            
            //NUM == NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                r = (der.getValor() == null ? izq.getValor() == null : der.getValor().equals(izq.getValor()));
                return CrearResult(r);
            }
            //NUM == CAD
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)){
                r = Auxiliar.ToNum(izq.getValor()) == der.getValor().length();
                return CrearResult(r);
            }
            //NUM == BOOL
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                r = Objects.equals(Auxiliar.ToNum(izq.getValor()), Auxiliar.BoolToNum(der.getValor()));
                return CrearResult(r);
            }
        }
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)){
            //CAD == NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                r = izq.getValor().length() == Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD == CAD
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)){
                r = (izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor()));
                return CrearResult(r);
            }
            //CAD == DATE/DATETIME
            if(Auxiliar.esDateoDT(der.getTipo())){
                r = (izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor()));
                return CrearResult(r);
            }
        }
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)){
            //BOOL == NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                r = Objects.equals(Auxiliar.BoolToNum(izq.getValor()), Auxiliar.ToNum(der.getValor()));
                return CrearResult(r);
            }
            //BOOL == BOOL
            if(Auxiliar.esTipo(der.getTipo(),ConsJS.BOOL)){
                r = Objects.equals(Auxiliar.BoolToNum(izq.getValor()), Auxiliar.BoolToNum(der.getValor()));
                return CrearResult(r);
            }
        }
        
        //YA SOLO QUEDA EL CASO EN QUE SEAN CADENA O DATETIME
        
        
        return CrearResult((izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor())));
    }
    
    public static Result menorque(Result izq, Result der){
        Result respuesta = new Result();
        boolean r;
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)){
            
            //NUM < NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                r = Auxiliar.ToNum(izq.getValor()) < Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //NUM < CAD
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)){
                r = Auxiliar.ToNum(izq.getValor()) < der.getValor().length();
                return CrearResult(r);
            }
            //NUM < BOOL
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)){
                r = Auxiliar.ToNum(izq.getValor()) < Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)){
            //CAD < NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                r = izq.getValor().length() < Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD < CAD
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)){
                r = izq.getValor().length() < der.getValor().length();
                return CrearResult(r);
            }
            //CAD == DATE/DATETIME
            if(Auxiliar.esDateoDT(der.getTipo())){
                r = (izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor()));
                return CrearResult(r);
            }
        }
        
        if(Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)){
            //BOOL == NUM
            if(Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)){
                r = Objects.equals(Auxiliar.BoolToNum(izq.getValor()), Auxiliar.ToNum(der.getValor()));
                return CrearResult(r);
            }
            //BOOL == BOOL
            if(Auxiliar.esTipo(der.getTipo(),ConsJS.BOOL)){
                r = Objects.equals(Auxiliar.BoolToNum(izq.getValor()), Auxiliar.BoolToNum(der.getValor()));
                return CrearResult(r);
            }
        }
        
        //YA SOLO QUEDA EL CASO EN QUE SEAN CADENA O DATETIME
        return respuesta;
    }
    
    private static Result CrearResult(boolean valor){
        return new Result(ConsJS.BOOL, valor+"");
    }
    
    
    
    
}
