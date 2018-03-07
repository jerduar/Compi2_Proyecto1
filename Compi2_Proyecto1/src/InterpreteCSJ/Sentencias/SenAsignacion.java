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
 * @author jerdu
 */
public class SenAsignacion extends Sentencia {

    public SenAsignacion(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
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
