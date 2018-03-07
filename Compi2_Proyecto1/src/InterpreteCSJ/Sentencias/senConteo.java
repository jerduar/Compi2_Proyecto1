/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Expresiones.Expresion;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.ConsJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerdu
 */
public class senConteo extends Sentencia {

    public senConteo(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Result respuesta = Result.EjecucionOK();
        
        Nodo e = new Nodo(ConsCJS.E);
        e.addHijo(this.sentencia.getHijo(0));
        Result array = new Expresion(e,t).ResolverExpresion();
        if(!array.isEsArreglo()){
            ManErr.InsertarError("", "Semantico", 0, 0, "Debe ser un arreglo senConteo");
            return Result.EjecucionError();
        }
        respuesta.setValor(array.getTam_arreglo().toString());
        respuesta.setTipo(ConsJS.NUM);
        return respuesta;
    }
    
}
