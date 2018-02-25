/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;
import AST.Nodo;
import GeneradorCJS.ConsCJS;

/**
 *
 * @author jerdu
 */
public class RecolectorCSJ {
    
    /*public static TablaSymCSJ getTablaSimbolos(Nodo){
        
    }**/
    
    //NECESITO RECOLECTAR TODOS LOS ELEMENTOS DE CJS EN UNA SOLA TABLA DE SÍMBOLOS
    
    
    
    public static TablaSymCSJ LlenarTabla(ManErr m,Nodo ... lista){
        TablaSymCSJ tabla = new TablaSymCSJ(0);
        for(Nodo n: lista){
            Recolectar(n,tabla,m);
        }
        return tabla;
    }
    
    private static void Recolectar(Nodo h, TablaSymCSJ t, ManErr m){
        for(Nodo hijo: h.getHijos()){
            
            if(hijo.getCod() == ConsCJS.FUN){
                SimJS fun = SimJS.getSimFuncion(hijo);
                if(!t.InsertarSimJS(fun)){
                    ManErr.InsertarError("", "Semantico", hijo.getHijo(0).getFila(), hijo.getHijo(0).getColumna(), "Ya existe una función " + hijo.getHijo(0).getLexema());
                }
            }
            else{
                //System.out.println("No se encontro ninguna coincidencia");
            }
        }
    }
}
