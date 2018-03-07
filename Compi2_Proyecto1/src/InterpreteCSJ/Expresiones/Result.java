/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import InterpreteCSJ.Recolector.ConsJS;
import java.util.ArrayList;

/**
 *
 * @author jerdu
 */
public class Result {
    
    private static final int OK = 20;
    private static final int FAIL = 30;
    private static final int BREAK = 40;
    private static final int CONTINUE = 50;
    private static final int RETURN = 60;
    
    private Integer tipo;
    private String valor;
    private Integer status;
    private ArrayList<Result> soluciones;
    private Integer dimensiones;
    private boolean esArreglo;
    private Integer tam_arreglo;
    private boolean esReturn;
    
    public Result() {
        this.tipo = ConsJS.ERROR;
        this.valor = null;
        this.status = OK;
        this.soluciones = null;
        this.dimensiones = 0;
        this.esArreglo = false;
    }
    
    public Result(Integer tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.status = OK;
        this.soluciones = null;
        this.dimensiones = 0;
        this.esArreglo = false;
    }
    
    private Result(Integer tipo, String valor, Integer status) {
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
        this.soluciones = null;
        this.dimensiones = 0;
        this.esArreglo = false;
    }
    
    public static Result EjecucionOK(){
        return new Result(ConsJS.VOID, "", OK);
    }
    
    public static Result EjecucionError(){
        return new Result(ConsJS.ERROR, "", FAIL);
    }
    
    public static Result Detener(){
        return new Result(ConsJS.VOID,"",BREAK);
    }
    
    public boolean esDetener(){
        return this.status == BREAK;
    }
    
    public boolean esRetorno(){
        return this.esReturn;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the soluciones
     */
    public ArrayList<Result> getSoluciones() {
        return soluciones;
    }

    /**
     * @param soluciones the soluciones to set
     */
    public void setSoluciones(ArrayList<Result> soluciones) {
        this.soluciones = soluciones;
    }

    /**
     * @return the dimensiones
     */
    public Integer getDimensiones() {
        return dimensiones;
    }

    /**
     * @param dimensiones the dimensiones to set
     */
    public void setDimensiones(Integer dimensiones) {
        this.dimensiones = dimensiones;
    }

    /**
     * @return the esArreglo
     */
    public boolean isEsArreglo() {
        return esArreglo;
    }

    /**
     * @param esArreglo the esArreglo to set
     */
    public void setEsArreglo(boolean esArreglo) {
        this.esArreglo = esArreglo;
    }

    /**
     * @return the tam_arreglo
     */
    public Integer getTam_arreglo() {
        return tam_arreglo;
    }

    /**
     * @param tam_arreglo the tam_arreglo to set
     */
    public void setTam_arreglo(Integer tam_arreglo) {
        this.tam_arreglo = tam_arreglo;
    }

    /**
     * @return the esReturn
     */
    public boolean isEsReturn() {
        return esReturn;
    }

    /**
     * @param esReturn the esReturn to set
     */
    public void setEsReturn(boolean esReturn) {
        this.esReturn = esReturn;
    }
    
    
}
