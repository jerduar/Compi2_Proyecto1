/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

import InterpreteCSJ.Expresiones.Result;
import java.util.Hashtable;

/**
 *
 * @author jerdu
 */
public class TablaSymCSJ {
    
    
    private TablaSymCSJ hijo, padre;
    private Hashtable<Integer,SimJS> tabla;
    Integer nivel;
    
    public TablaSymCSJ(){
        
    }
    
    public TablaSymCSJ(Integer nivel){        
        this.hijo = null;
        this.padre = null;
        this.tabla = new Hashtable<>();
        this.nivel = nivel;
    }
    
    public TablaSymCSJ crearTablaHijo(){
        if(this.hijo == null){
            hijo = new TablaSymCSJ(this.nivel+1);
            hijo.padre = this;
            return hijo;
        }else{
            return hijo;
        }
    }
    
    public Boolean InsertarSimJS(SimJS sim){
        
        Integer key = GenerarKey(sim.getNombre(),sim.getRol(),sim.getNo_param());
        
        if(!ExisteSimJS(key)){
            sim.setKey(key);
            this.tabla.put(key, sim);
            return true;
        }
        
        return false;
    }
    
    public void SetVar(SimJS var, Result valor){
        SimJS a = this.BuscarVariable(var.getNombre());
        if(a != null){
           a.setValor(valor); 
        }else{
            System.out.println("No se encontro la variable para setear");
        }
        
    }
    
    public Boolean ExisteSimJS(Integer key){
        if(tabla.containsKey(key)){
            return true;
        }else if(this.padre != null){
            return this.padre.ExisteSimJS(key);
        }else{
            return false;
        }
    }
    
    private Integer GenerarKey(String llave, int rol, int num_param){
        Integer key = 0;
        llave = llave.toLowerCase();
        for(int i = 0; i < llave.length(); i++){
            key += (i+1) * llave.charAt(i);
        }
        
        return key + rol + num_param;
    }
    
    public SimJS BuscarVariable(String nombre){
        Integer key = this.GenerarKey(nombre, ConsJS.variable, 0);
        
        return SacarSimbolo(key);
    }
    
    
    public SimJS SacarSimbolo(Integer key){
        SimJS var = this.tabla.get(key);
        if(var != null){
            return var;
        }else{
            if(this.padre != null){
                return this.padre.SacarSimbolo(key);
            }else{
                return null;
            }
        }
    }
}
