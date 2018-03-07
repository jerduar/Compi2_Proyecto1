/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

import AST.Nodo;
import InterpreteCSJ.Expresiones.Result;
import java.util.ArrayList;

/**
 *
 * @author jerduar
 */
public class SimJS {

    /**
     * @param key the key to set
     */
    public void setKey(Integer key) {
        this.key = key;
    }

    /**
     * @return the no_param
     */
    public Integer getNo_param() {
        return no_param;
    }
    private Integer rol;
    private String nombre;
    private Integer key;
    
    //ATRIBUTOS DE UNA VARIABLE
    private Integer tipo_var;
    private Result valor;
    //private boolean esArreglo;
    
    //ATRIBUTOS DE UNA FUNCION
    private Nodo sentencias;
    private Nodo parametros;
    private Integer no_param;
    
    //ATRIBUTOS DE UN ARREGLO
    //private ArrayList<Result> ArregloResult;
    //private Integer no_elementos;
    
    
    
    private SimJS(){
        
    }
    
    /**
     *
     * @param variable
     * @return simJS de tipo variable
     */
    public static SimJS getSimVar(Nodo variable){
        SimJS sim = new SimJS();
        sim.setNombre(variable.getLexema());
        sim.setRol(ConsJS.variable);
        sim.setNo_param(0);
        //sim.setEsArreglo(false);
        return sim;
        
        
    }
    
    /**
     *
     * @param funcion
     * @return SimJS de tipo Funcion
     */
    public static SimJS getSimFuncion(Nodo funcion){
        SimJS sim = new SimJS();
        sim.setNombre(funcion.getHijo(0).getLexema());
        sim.setNo_param((Integer) funcion.getHijo(1).getHijos().size());
        sim.setParametros(funcion.getHijo(1));
        sim.setSentencias(funcion.getHijo(2));
        sim.setRol(ConsJS.funcion);
        //sim.setEsArreglo(false);
        return sim;
    }
    
    /**
     *
     * @param nombre
     * @param ele
     * @param no_ele
     * @return SimJS de tipo Arreglo
     */
    public static SimJS getSimArreglo(String nombre, ArrayList<Result> ele, Integer no_ele){
        Result valor = new Result();
        valor.setEsArreglo(true);
        valor.setTam_arreglo(no_ele);
        valor.setTipo(ConsJS.arreglo);
        
        SimJS sim = new SimJS();
        sim.setValor(valor);
        //sim.setElemento(ele);
        //sim.setNo_elementos(no_ele);
        sim.setNombre(nombre);
        sim.setRol(ConsJS.variable);
        sim.setNo_param(0);
        //sim.setEsArreglo(true);
        return sim;
    }

    /**
     * @return the rol
     */
    public Integer getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Integer rol) {
        this.rol = rol;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipo_var
     */
    public Integer getTipo_var() {
        return tipo_var;
    }

    /**
     * @param tipo_var the tipo_var to set
     */
    public void setTipo_var(Integer tipo_var) {
        this.tipo_var = tipo_var;
    }

    /**
     * @return the valor
     */
    public Result getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Result valor) {
        this.valor = valor;
    }

    /**
     * @return the sentencias
     */
    public Nodo getSentencias() {
        return sentencias;
    }

    /**
     * @param sentencias the sentencias to set
     */
    public void setSentencias(Nodo sentencias) {
        this.sentencias = sentencias;
    }

    /**
     * @return the parametros
     */
    public Nodo getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(Nodo parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the elemento
     
    public ArrayList<Result> getElemento() {
        return this.ArregloResult;
    }

    /**
     * @param elemento the elemento to set
     
    public void setElemento(ArrayList <Result> nuevo) {
        this.ArregloResult = nuevo;
    }

    /**
     * @return the no_elementos
     
    public Integer getNo_elementos() {
        return no_elementos;
    }

    /**
     * @param no_elementos the no_elementos to set
     
    public void setNo_elementos(Integer no_elementos) {
        this.no_elementos = no_elementos;
    }

    /**
     * @param no_param the no_param to set
     */
    public void setNo_param(Integer no_param) {
        this.no_param = no_param;
    }

    /**
     * @return the esArreglo
     */
    /*public boolean isEsArreglo() {
        return esArreglo;
    }

    /**
     * @param esArreglo the esArreglo to set
     */
    /*public void setEsArreglo(boolean esArreglo) {
        this.esArreglo = esArreglo;
    }

    /**
     * @return the key
     */
    public Integer getKey() {
        return key;
    }
}
