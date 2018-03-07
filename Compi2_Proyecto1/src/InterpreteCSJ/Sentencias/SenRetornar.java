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
import InterpreteCSJ.Recolector.TablaSymCSJ;
import java.util.ArrayList;

/**
 *
 * @author jerdu
 */
public class SenRetornar extends Sentencia {

    public SenRetornar(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        Result respuesta;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(this.sentencia.getHijo(0).getCod() == ConsCJS.E
                || this.sentencia.getHijo(0).getCod() == ConsCJS.EA
                || this.sentencia.getHijo(0).getCod() == ConsCJS.ER
                || this.sentencia.getHijo(0).getCod() == ConsCJS.EL){
            respuesta = new Expresion(this.sentencia.getHijo(0),t).ResolverExpresion();
            //System.out.println(respuesta.getValor());
            respuesta.setEsReturn(true);
            return respuesta;
        }
        respuesta = toArray(sentencia.getHijo(0), t);
        respuesta.setEsReturn(true);
        return respuesta;
    
    }
    
    private Result toArray(Nodo hoja, TablaSymCSJ t){
        Result respuesta = new Result();
        
        ArrayList<Result> lista = new ArrayList<>();
        hoja.getHijos().stream().map((n) -> new Expresion(n,t).ResolverExpresion()).filter((res) -> (!Auxiliar.esError(res.getTipo()))).forEachOrdered((res) -> {
            lista.add(res);
        });
        
        respuesta.setEsArreglo(true);
        respuesta.setTipo(ConsJS.arreglo);
        respuesta.setSoluciones(lista);
        respuesta.setTam_arreglo(lista.size());
        return respuesta;
    }
    
}
