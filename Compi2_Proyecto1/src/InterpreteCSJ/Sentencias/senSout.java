/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import InterpreteCSJ.Expresiones.Auxiliar;
import InterpreteCSJ.Expresiones.Expresion;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.TablaSymCSJ;
import javax.swing.JOptionPane;

/**
 *
 * @author jerdu
 */
public class senSout extends Sentencia{

    public senSout(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Expresion e = new Expresion(this.sentencia,t);
        Result respuesta = e.ResolverExpresion();
        
        if(Auxiliar.esError(respuesta.getTipo())){
            ManErr.InsertarError("", "Semántico", this.sentencia.getFila(), this.sentencia.getColumna(), "No se puedo encontrar evaluar la expresion");
            //System.out.println("Es un error :(");
            return Result.EjecucionError();
        }else{
            System.out.println(respuesta.getValor());
            //JOptionPane.showMessageDialog(null, respuesta.getValor(), "INFORMACIÓN CJS", JOptionPane.INFORMATION_MESSAGE);
            return Result.EjecucionError();
        }
    }
    
}
