/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.ConsJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.SimJS;
import InterpreteCSJ.Recolector.TablaSymCSJ;
import java.util.ArrayList;

/**
 *
 * @author jerdu
 */
public class SenAtexto extends Sentencia{

    public SenAtexto(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        Result respuesta = new Result();
        System.out.println(sentencia.getCod() + " " + ConsCJS.ATEXTO);
        if(sentencia.getCod() == ConsCJS.ATEXTO){
            respuesta = id_texto(sentencia.getHijo(0),t);
            return respuesta;
        }
        return respuesta;   
    }
    
    private Result id_texto(Nodo id, TablaSymCSJ t){
        Result r = new Result();
        
        SimJS var = t.BuscarVariable(id.getLexema());
        
        if(var == null){
            ManErr.InsertarError("", "Semantico", id.getFila(), id.getCod(), "No existe la variable " + id.getLexema());
            return r;
        }
        
        if(!var.isEsArreglo()){
            ManErr.InsertarError("", "Semantico", id.getFila(), id.getCod(), "La variable " + id.getLexema() + " no es un arreglo");
            return r;
        }
        
        if(var.getElemento() == null){
            r.setValor("");
            r.setTipo(ConsJS.CAD);
            return r;
        }
        
        r.setValor(ArrToStr(var.getElemento()));
        r.setTipo(ConsJS.CAD);
        
        return r;
    }
    
    private String ArrToStr(ArrayList<Result> arr){
        String str = "";
        for(Result r : arr){
            str += r.getValor();
        }
        
        return str;
    }
    
    
    
}
