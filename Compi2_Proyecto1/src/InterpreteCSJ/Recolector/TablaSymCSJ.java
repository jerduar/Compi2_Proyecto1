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
    
    public TablaSymCSJ(Integer nivel){        
        this.hijo = null;
        this.padre = null;
        this.tabla = new Hashtable<>();
        this.nivel = nivel;
    }
    
    public Boolean InsertarSimJS(String llave, SimJS sim){
        Integer key = GenerarKey(llave,sim.getRol());
        
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
            return (this.padre.nivel == 0)? false : this.padre.ExisteSimJS(key);
        }else{
            return false;
        }
    }
    
    private Integer GenerarKey(String llave, int rol){
        Integer key = 0;
        for(int i = 0; i < llave.length(); i++){
            key += i * llave.charAt(i);
        }
        
        return key + rol;
    }
}
