/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerdu
 */
public class SenCuerpo extends Sentencia {

    private final boolean esCuerpoGlobal;

    public SenCuerpo(Nodo sen, boolean esGlobal) {
        super(sen);
        this.esCuerpoGlobal = esGlobal;
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        Result r = Result.EjecucionOK();
        for (Nodo sent : this.sentencia.getHijos()) {
            if (sent.getCod() == ConsCJS.FUN && this.esCuerpoGlobal) {
                //ESTOY ANALIZANDO LAS SENTENCIAS EXTERNAS Y ENCUENTRO FUNCIONES LAS IGNORO
                //System.out.println("Encontre una funcion la voy a ignorar ;)");
                
            }else if(sent.getCod() == ConsCJS.DEC){
                r = new SenDeclaracion(sent.getHijo(0)).Ejecutar(t);
            }else if(sent.getCod() == ConsCJS.ASIG){
                r = new SenAsignacion(sent).Ejecutar(t);
            }else if(sent.getCod() == ConsCJS.MSG){
                r = new SenMsg(sent.getHijo(0)).Ejecutar(t);
            }else if(sent.getCod() == ConsCJS.IF || sent.getCod() == ConsCJS.IF_ELSE){
                r = new SenIf(sent).Ejecutar(t);
            }else if(sent.getCod() == ConsCJS.ATEXTO){
                //r = new SenAtexto(sent).Ejecutar(t);
            }
        }
        
        return r;
    }

}
