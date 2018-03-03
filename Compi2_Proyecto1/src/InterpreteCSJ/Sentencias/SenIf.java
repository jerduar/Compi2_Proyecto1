/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Expresiones.Auxiliar;
import InterpreteCSJ.Expresiones.Expresion;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.ConsJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerdu
 */
public class SenIf extends Sentencia {

    public SenIf(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        Result exp = new Expresion(this.sentencia.getHijo(0),t).ResolverExpresion();
        
        if(Auxiliar.esError(exp.getTipo()) || exp.getValor() == null){
            ManErr.InsertarError("", "Semántico", 0, 0, "No se puedo evaluar la expresión correctamente");
            return Result.EjecucionError();
        }
        
        if(!Auxiliar.esTipo(exp.getTipo(), ConsJS.BOOL)){
            ManErr.InsertarError("", "Semántico", 0, 0, "No trae un valor booleano");
            return Result.EjecucionError();
        }
        
        if(Auxiliar.toBool(exp.getValor())){
           return new SenCuerpo(this.sentencia.getHijo(1),false).Ejecutar(t.crearTablaHijo());
        }else if(sentencia.getCod() == ConsCJS.IF_ELSE){
            return new SenCuerpo(this.sentencia.getHijo(2),false).Ejecutar(t.crearTablaHijo());
        }
        
        return Result.EjecucionOK();
    }
    
}
