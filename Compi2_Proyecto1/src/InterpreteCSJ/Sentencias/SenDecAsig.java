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
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.SimJS;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerduar
 */
public class SenDecAsig extends Sentencia {

    public SenDecAsig(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Result valor = new Expresion(this.sentencia.getHijo(1), t).ResolverExpresion();
        if (Auxiliar.esError(valor.getTipo())) {
            ManErr.InsertarError("", "Semántico", 0, 0, "No se puedo evaluar la expresion DecAsig");
            return Result.EjecucionError();
        }

        SimJS variable = SimJS.getSimVar(this.sentencia.getHijo(0));
        variable.setValor(valor);
        if (!t.InsertarSimJS(variable)) {
            ManErr.InsertarError("", "Semantico", this.sentencia.getHijo(0).getFila(), this.sentencia.getHijo(0).getColumna(), "Ya existe una función " + this.sentencia.getHijo(0).getLexema());
            return Result.EjecucionError();
        }else{
            //System.out.println("Se declaró y asgino bien");
        }

        return Result.EjecucionOK();
    }

    

}
