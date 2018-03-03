/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import InterpreteCSJ.Expresiones.Auxiliar;
import InterpreteCSJ.Expresiones.Expresion;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerduar
 */
public class SenMsg extends Sentencia{

    public SenMsg(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        Expresion e = new Expresion(this.sentencia,t);
        Result respuesta = e.ResolverExpresion();
        
        if(Auxiliar.esError(respuesta.getTipo())){
            System.out.println("Es un error :(");
            return Result.EjecucionOK();
        }else{
            System.out.println(respuesta.getValor());
            return Result.EjecucionError();
        }
    }
    
}
