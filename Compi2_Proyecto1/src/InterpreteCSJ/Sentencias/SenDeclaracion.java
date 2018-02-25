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
import InterpreteCSJ.Recolector.SimJS;
import InterpreteCSJ.Recolector.TablaSymCSJ;
import java.util.Objects;

/**
 *
 * @author jerdu
 */
public class SenDeclaracion extends Sentencia {

    public SenDeclaracion(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        Result a = new Result();
        if (this.sentencia.getCod() == ConsCJS.LID) {
            DecVariables(t);
        } else if (this.sentencia.getCod() == ConsCJS.LISTA_AR) {

            DecArreglos(this.sentencia, t);
        }
        return a;
    }

    //Declaración variables
    private void DecVariables(TablaSymCSJ t) {

        SimJS variable;
        for (Nodo var : sentencia.getHijos()) {
            variable = SimJS.getSimVar(var);
            if (!t.InsertarSimJS(variable)) {
                ManErr.InsertarError("", "Semantico", var.getFila(), var.getColumna(), "Ya existe una función " + var.getLexema());
                
            } else {

            }
        }
    }

    private void DecArreglos(Nodo Lista, TablaSymCSJ t) {
        Lista.getHijos().forEach((arreglo) -> {
            String id = arreglo.getHijo(0).getLexema();
            Result tamanio = new Expresion(arreglo.getHijo(1), t).ResolverExpresion();
            //System.out.println("tamanio : " + tamanio.getTipo() + " " + tamanio.getValor());
            if (!Objects.equals(tamanio.getTipo(), ConsJS.NUM)) {
                //ERROR
                System.out.println("Debe ser una expresión Numérica " + tamanio.getValor());

            } else {
                try {
                    Integer tam = Integer.parseInt(tamanio.getValor());
                    if (tam < 1) {
                        //ERROR
                        System.out.println("Debe ser un número mayor que 0");
                    }
                    SimJS ar = SimJS.getSimArreglo(id, null, tam);
                    if (!t.InsertarSimJS(ar)) {
                        System.out.println("Arreglo ya existente " + id);
                    }
                } catch (NumberFormatException excepcion) {
                    //ERROR
                    System.out.println("No es una entero positivo");
                }

            }
        });
    }

}
