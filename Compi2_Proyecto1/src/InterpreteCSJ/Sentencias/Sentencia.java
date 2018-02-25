/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;
import AST.Nodo;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerdu
 */
public abstract class Sentencia {
    
    protected Nodo sentencia;
    protected static TablaSymCSJ tabla;
    
    public Sentencia(Nodo sen){
        this.sentencia = sen;
    }
    
    public abstract Result Ejecutar(TablaSymCSJ t);
    
    public static final void SetContexto(TablaSymCSJ t){
        Sentencia.tabla = t;
    }
    
}
