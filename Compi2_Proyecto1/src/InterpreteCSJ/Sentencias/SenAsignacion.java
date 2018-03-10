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
import InterpreteCSJ.Recolector.SimJS;
import InterpreteCSJ.Recolector.TablaSymCSJ;
import java.util.ArrayList;

/**
 *
 * @author jerduar
 */
public class SenAsignacion extends Sentencia {

    public SenAsignacion(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        
        if(this.sentencia.getHijo(0).getCod() == ConsCJS.POS_ARRE){
            Nodo arreglo = this.sentencia.getHijo(0);
            
            Result valor = t.BuscarVariable(arreglo.getHijo(0).getLexema());
            if(Auxiliar.esError(valor.getTipo())){
                ManErr.InsertarError("", "Semantico", -1, -1, "No se pudo ubicar la posición del arreglo SenAsig");
                return Result.EjecucionError();
            }
            
            if(!valor.isEsArreglo()){
                ManErr.InsertarError("", "Semantico", -1, -1, "La variable debe ser una arreglo SenAsig");
                return Result.EjecucionError();
            }
            
            Result pos = new Expresion(arreglo.getHijo(1),t).ResolverExpresion();
            
            if(!Auxiliar.esTipo(pos.getTipo(), ConsJS.NUM)){
                ManErr.InsertarError("", "Semantico", -1, -1, "el valor debe ser numérico SenAsig");
                return Result.EjecucionError();
            }
            
            Double posicion = Double.parseDouble(pos.getValor());
            if((posicion < 0)){
                ManErr.InsertarError("", "Semantico", -1, -1, "el valor debe ser mayor a -1 SenAsig");
                return Result.EjecucionError();
            }
            
            Result nuevo_valor = new Expresion(this.sentencia.getHijo(1),t).ResolverExpresion();
            if(Auxiliar.esError(nuevo_valor.getTipo())){
                ManErr.InsertarError("", "Semantico", -1, -1, "No se pudo evaluar la posición del arreglo SenAsig");
                return Result.EjecucionError();
            }
            
            if(posicion > valor.getTam_arreglo()-1){
                ManErr.InsertarError("", "Semantico", -1, -1, "Fuera de rango SenAsig");
                return Result.EjecucionError();
            }
            Result var = valor.getSoluciones().get(posicion.intValue());
            
            var.setEsArreglo(nuevo_valor.isEsArreglo());
            var.setSoluciones(nuevo_valor.getSoluciones());
            var.setTam_arreglo(nuevo_valor.getTam_arreglo());
            var.setTipo(nuevo_valor.getTipo());
            var.setValor(nuevo_valor.getValor());
            return Result.EjecucionOK();
            
        }
        
        if (this.sentencia.getHijo(1).getCod() == ConsCJS.ARR) {
            SimJS var = t.getVar(this.sentencia.getHijo(0).getLexema());
            if (var == null) {
                System.out.println("No se encontró la variable " + this.sentencia.getHijo(0).getLexema());
                return Result.EjecucionError();
            }
            Result r = new Result();
            r.setEsArreglo(true);
            ArrayList<Result> lista = new ArrayList<>();
            for(Nodo e: this.sentencia.getHijo(1).getHijo(0).getHijos()){
                //System.out.println(ConsCJS.RetornStringCSJ(e.getCod()));
                Result res = new Expresion(e, t).ResolverExpresion();
                lista.add(res);
            }
            r.setSoluciones(lista);
            var.setValor(r);
            var.getValor().setEsArreglo(true);
            var.getValor().getSoluciones().size();
            var.getValor().setSoluciones(lista);
            return Result.EjecucionOK();
            
        }
        Expresion e = new Expresion(this.sentencia.getHijo(1), t);
        Result a = e.ResolverExpresion();

        if (Auxiliar.esError(a.getTipo())) {
            ManErr.InsertarError("", "Semantico", 0, 0, "No se puedo evaluar la expresion");
            return Result.EjecucionError();
        }

        SimJS var = t.getVar(this.sentencia.getHijo(0).getLexema());
        if (var == null) {
            ManErr.InsertarError("", "Semántico", this.sentencia.getHijo(0).getFila(), this.sentencia.getHijo(0).getColumna(), "No se econtró la variable " + this.sentencia.getHijo(0).getLexema());
            //System.out.println("No se encontró la variable " + this.sentencia.getHijo(0).getLexema());
            return Result.EjecucionOK();
        }

        var.setValor(a);
        System.out.println("Se pudo setear el valor bien :)");
        return Result.EjecucionOK();
    }

}
