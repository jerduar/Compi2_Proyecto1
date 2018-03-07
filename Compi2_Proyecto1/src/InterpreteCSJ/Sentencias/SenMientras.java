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
import InterpreteCSJ.Recolector.ConsJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerdu
 */
public class SenMientras extends Sentencia {

    public SenMientras(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {

        Result respuesta = Result.EjecucionOK();
        while (true) {
            Result condicion = new Expresion(this.sentencia.getHijo(0), t).ResolverExpresion();

            if (!Auxiliar.esTipo(condicion.getTipo(), ConsJS.BOOL)) {
                ManErr.InsertarError("", "Semantico", 0, 0, "La expresion no devuelve booleano SenMientras");
                return Result.EjecucionError();
            }
            
            boolean esVerdad = Auxiliar.toBool(condicion.getValor());
            
            if(!esVerdad){
                break;
            }
            
            respuesta = new SenCuerpo(this.sentencia.getHijo(1),true).Ejecutar(t.crearTablaHijo());
            
            if(respuesta.isEsReturn()){
                break;
            }
            
            t.LimpiarTabla();
        }
        t.LimpiarTabla();
        return respuesta;
    }

}
