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
import InterpreteCSJ.Recolector.TablaSymCSJ;
import java.util.ArrayList;

/**
 *
 * @author jerduar
 * 
 */
public class SenSelecciona extends Sentencia {

    public SenSelecciona(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Result respuesta = Result.EjecucionOK();
        ArrayList<Nodo> Casos = this.sentencia.getHijo(1).getHijos();

        if (HayVariosDefect(Casos)) {
            ManErr.InsertarError("", "Semantcio", 0, 0, "Hay más de un defecto Selecciona");
            return Result.EjecucionError();
        }
        TablaSymCSJ tabla = t.crearTablaHijo();
        Nodo defecto = getDefecto(Casos);
        boolean seCumplioAlguno = false;
        int i = 0;
        for (; i < Casos.size(); i++) {
            if (Casos.get(i).getCod() == ConsCJS.DEF) {
                if (seCumplioAlguno) {
                    respuesta = EjecutarDefecto(defecto, tabla);
                    if (respuesta.esDetener()) {
                        respuesta = Result.EjecucionOK();
                        break;
                    }
                    if (respuesta.esRetorno()) {
                        if (respuesta.isEsReturn()) {
                            tabla.LimpiarTabla();
                            return respuesta;
                        }
                    }

                }
                continue;
            }

            Nodo er = new Nodo(ConsCJS.ER);

            Nodo igual = new Nodo(ConsCJS.IGUAL);
            Nodo e1 = this.sentencia.getHijo(0);
            Nodo e2 = Casos.get(i).getHijo(0);

            igual.addHijo(e1, e2);
            er.addHijo(igual);
            Result condicion = new Expresion(er, t).ResolverExpresion();
            if (!Auxiliar.esTipo(condicion.getTipo(), ConsJS.BOOL)) {
                ManErr.InsertarError("", "Semantcio", 0, 0, "Se esperaba un retorno booleano Selecciona");
                return Result.EjecucionError();
            }

            if (seCumplioAlguno || Auxiliar.toBool(condicion.getValor())) {
                seCumplioAlguno = true;
                respuesta = new SenCuerpo(Casos.get(i).getHijo(1), true).Ejecutar(tabla);

                if (respuesta.esDetener()) {

                    respuesta = Result.EjecucionOK();
                    break;
                }

                if (respuesta.isEsReturn()) {
                    tabla.LimpiarTabla();
                    return respuesta;
                }
            }
        }

        if (!seCumplioAlguno && defecto != null) {
            respuesta = EjecutarDefecto(defecto, t);
        }

        tabla.LimpiarTabla();
        return respuesta;
    }

    private boolean HayVariosDefect(ArrayList<Nodo> lista) {

        int cont = 0;
        for (Nodo c : lista) {
            if (c.getCod() == ConsCJS.DEF) {
                cont = cont + 1;
            }
            if (cont > 1) {
                return true;
            }
        }

        return false;
    }

    private Result EjecutarDefecto(Nodo defecto, TablaSymCSJ t) {
        return new SenCuerpo(defecto.getHijo(0), true).Ejecutar(t.crearTablaHijo());
    }

    private Nodo getDefecto(ArrayList<Nodo> lista) {
        for (Nodo n : lista) {
            if (n.getCod() == ConsCJS.DEF) {
                //lista.remove(n);
                return n;
            }
        }
        return null;
    }

}
