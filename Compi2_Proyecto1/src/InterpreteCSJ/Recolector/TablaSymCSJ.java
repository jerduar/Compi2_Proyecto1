/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

import AST.Nodo;
import InterpreteCSJ.Expresiones.Result;
import java.util.Hashtable;

/**
 *
 * @author jerdu
 */
public class TablaSymCSJ {

    private TablaSymCSJ hijo, padre;
    private Hashtable<Integer, SimJS> tabla;
    Integer nivel;

    public TablaSymCSJ() {

    }

    public TablaSymCSJ(Integer nivel) {
        this.hijo = null;
        this.padre = null;
        this.tabla = new Hashtable<>();
        this.nivel = nivel;
    }
    
    public TablaSymCSJ getGlobal(){
        if(this.getPadre() == null){
            return this;
        }else{
            return this.getPadre().getGlobal();
        }
    }
    
    public SimJS getFuncion(String nombre, Integer no_param){
        TablaSymCSJ global = this.getGlobal();
        Integer key = GenerarKey(nombre, ConsJS.funcion, no_param);
        SimJS funcion = getFuncion(key, global);
        
        if(funcion == null){
            return null;
        }
        
        return funcion;
    }
    
    private SimJS getFuncion(Integer key, TablaSymCSJ tabla){
        
        SimJS fun = tabla.tabla.get(key);
        return fun;
        
    }

    public TablaSymCSJ crearTablaHijo() {
        if (this.hijo == null) {
            hijo = new TablaSymCSJ(this.nivel + 1);
            hijo.setPadre(this);
            return hijo;
        } else {
            return hijo;
        }
    }

    public Boolean InsertarSimJS(SimJS sim) {

        Integer key = GenerarKey(sim.getNombre(), sim.getRol(), sim.getNo_param());

        if (!ExisteSimJS(key)) {
            sim.setKey(key);
            this.tabla.put(key, sim);
            return true;
        }

        return false;
    }

    public void SetVar(SimJS var, Result valor) {
        //SimJS a = this.BuscarVariable(var.getNombre());}
        Integer key = GenerarKey(var.getNombre(), var.getRol(), var.getNo_param());
        SimJS a = SacarSimbolo(key);
        if (a != null) {
            a.setValor(valor);
        } else {
            System.out.println("No se encontro la variable para setear");
        }

    }

    public Boolean ExisteSimJS(Integer key) {
        if (tabla.containsKey(key)) {
            return true;
        } else if (this.getPadre() != null) {
            return this.getPadre().ExisteSimJS(key);
        } else {
            return false;
        }
    }

    private Integer GenerarKey(String llave, int rol, int num_param) {
        Integer key = 0;
        llave = llave.toLowerCase();
        for (int i = 0; i < llave.length(); i++) {
            key += (i + 1) * llave.charAt(i);
        }

        return key + rol + num_param;
    }

    public Result BuscarVariable(String nombre) {

        Integer key = this.GenerarKey(nombre, ConsJS.variable, 0);
        SimJS sim = SacarSimbolo(key);
        if (sim == null) {
            return new Result();
        }
        return sim.getValor();
    }

    public SimJS getVar(String nombre) {
        Integer key = this.GenerarKey(nombre, ConsJS.variable, 0);
        return SacarSimbolo(key);
    }

    public SimJS SacarSimbolo(Integer key) {
        SimJS var = this.tabla.get(key);
        if (var != null) {
            return var;
        } else {
            if (this.getPadre() != null) {
                return this.getPadre().SacarSimbolo(key);
            } else {
                return null;
            }
        }
    }

    public void LimpiarTabla() {
        this.padre.hijo = null;
    }

    /**
     * @return the padre
     */
    public TablaSymCSJ getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(TablaSymCSJ padre) {
        this.padre = padre;
    }
}
