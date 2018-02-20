/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

import AST.Nodo;

/**
 *
 * @author jerduar
 */
public class SimJS {
    private Integer rol;
    private String nombre;
    
    //ATRIBUTOS DE UNA VARIABLE
    private Integer tipo_var;
    private Nodo valor;
    
    //ATRIBUTOS DE UNA FUNCION
    private Nodo sentencias;
    private Nodo parametros;
    
    //ATRIBUTOS DE UN ARREGLO
    private Nodo elemento;
    private Integer no_elementos;
    
    private SimJS(){
        
    }
    
    /**
     *
     * @param nombre
     * @return simJS de tipo variable
     */
    public static SimJS getSimVar(String nombre){
        SimJS sim = new SimJS();
        sim.setNombre(nombre);
        sim.setRol(ConsJS.variable);
        return sim;
    }
    
    /**
     *
     * @param nombre
     * @param param
     * @param sen
     * @return SimJS de tipo Funcion
     */
    public static SimJS getSimFuncion(String nombre, Nodo param, Nodo sen){
        SimJS sim = new SimJS();
        sim.setNombre(nombre);
        sim.setParametros(param);
        sim.setSentencias(sen);
        sim.setRol(ConsJS.funcion);
        return sim;
    }
    
    /**
     *
     * @param nombre
     * @param ele
     * @param no_ele
     * @return SimJS de tipo Arreglo
     */
    public static SimJS getSimArreglo(String nombre, Nodo ele, Integer no_ele){
        SimJS sim = new SimJS();
        sim.setElemento(ele);
        sim.setNo_elementos(no_ele);
        sim.setRol(ConsJS.arreglo);
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
    public Nodo getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Nodo valor) {
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
     */
    public Nodo getElemento() {
        return elemento;
    }

    /**
     * @param elemento the elemento to set
     */
    public void setElemento(Nodo elemento) {
        this.elemento = elemento;
    }

    /**
     * @return the no_elementos
     */
    public Integer getNo_elementos() {
        return no_elementos;
    }

    /**
     * @param no_elementos the no_elementos to set
     */
    public void setNo_elementos(Integer no_elementos) {
        this.no_elementos = no_elementos;
    }
}
