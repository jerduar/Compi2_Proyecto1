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

            } else if (sent.getCod() == ConsCJS.DEC) {
                r = new SenDeclaracion(sent.getHijo(0)).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.ASIG) {
                r = new SenAsignacion(sent).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.MSG) {
                r = new SenMsg(sent.getHijo(0)).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.IF || sent.getCod() == ConsCJS.IF_ELSE) {
                r = new SenIf(sent).Ejecutar(t.crearTablaHijo());
            } else if (sent.getCod() == ConsCJS.ATEXTO) {
                r = new SenAtexto(sent).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.DEC_ASIG) {
                r = new SenDecAsig(sent.getHijo(0)).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.WHILE) {
                r = new SenMientras(sent).Ejecutar(t.crearTablaHijo());
            } else if (sent.getCod() == ConsCJS.FOR) {
                r = new SenFor(sent).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.DET) {
                System.out.println(ConsCJS.DET);
                //if(this.sentencia.getCod() == ConsCJS.SWT){
                r = Result.Detener();
                //System.out.println(r.getTipo());
                return r;
                //}else{
                /*r = Result.EjecucionError();
                    System.out.println("Error no es lugar para un break");
                    ManErr.InsertarError("", "Semantico", 0, 0, "No es lugar para un break sentencia");
                }*/
            } else if (sent.getCod() == ConsCJS.SWT) {
                r = new SenSelecciona(sent).Ejecutar(t);
            } else if (sent.getCod() == ConsCJS.RET) {
                r = new SenRetornar(sent).Ejecutar(t);
                
            } else if (sent.getCod() == ConsCJS.LLAM || sent.getCod() == ConsCJS.LLAM_P) {
                r = new senLlamada(sent).Ejecutar(t);
            }else if(sent.getCod() == ConsCJS.ID_CONT){
                r = new senConteo(sent).Ejecutar(t);
            }else if(sent.getCod() == ConsCJS.SOUT){
                r = new senSout(sent.getHijo(0)).Ejecutar(t);
            }
            
            if(r.esRetorno()){
                return r;
            }
        }

        return r;
    }

}
