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
import java.util.ArrayList;

/**
 *
 * @author jerduar
 */
public class senLlamada extends Sentencia {

    public senLlamada(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Result respuesta;
        SimJS funcion;
        //SI ES UNA LLAMADA SIN PARAMETROS
        if (this.sentencia.getCod() == ConsCJS.LLAM) {
            funcion = t.getFuncion(this.sentencia.getHijo(0).getLexema(), 0);
        //SI ES UNA FUNCION CON PARAMETROS
        } else {
            funcion = t.getFuncion(this.sentencia.getHijo(0).getLexema(), this.sentencia.getHijo(1).getHijos().size());
        }

        if (funcion == null) {
            ManErr.InsertarError("", "Semantcio", 0, 0, "No se encontro la funcion " + this.sentencia.getHijo(0).getLexema() + " SenLlamada");
            respuesta = Result.EjecucionError();
            return respuesta;
        }
        
        TablaSymCSJ tabla_funcion = new TablaSymCSJ(1);
        tabla_funcion.setPadre(t.getGlobal());
        if(funcion.getParametros() != null){
            ArrayList<Nodo> lista_param = funcion.getParametros().getHijos();
            ArrayList<Nodo> parametros = this.sentencia.getHijo(1).getHijos();
            for(int i = 0; i < lista_param.size(); i++){
                
                Nodo param_fun =lista_param.get(i);
                Nodo valor = parametros.get(i);
                //System.out.println("param: " + param_fun.getLexema() + " valor: " + valor.getCod());
                
                Result valor_exp = new Expresion(valor,t).ResolverExpresion();
                if(Auxiliar.esError(valor_exp.getTipo())){
                    return Result.EjecucionError();
                }
                
                SimJS param = SimJS.getSimVar(param_fun);
                param.setValor(valor_exp);
                
                tabla_funcion.InsertarSimJS(param);
            }
            
        }
        respuesta = new SenCuerpo(funcion.getSentencias(), true).Ejecutar(tabla_funcion);
        tabla_funcion.setPadre(null);
        return respuesta;

    }

}
