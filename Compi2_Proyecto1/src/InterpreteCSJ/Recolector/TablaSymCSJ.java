/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

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
    
    public Boolean InsertarSimJS(SimJS sim){
        
        Integer key = GenerarKey(sim.getNombre(),sim.getRol(),sim.getNo_param());
        
        if(!ExisteSimJS(key)){
            this.tabla.put(key, sim);
            return true;
        }
        
        return false;
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
        for(int i = 0; i < llave.length(); i++){
            key += (i+1) * llave.charAt(i);
        }
        
        return key + rol + num_param;
    }
    
    public void MostrarTabla(){
        
    }
}
