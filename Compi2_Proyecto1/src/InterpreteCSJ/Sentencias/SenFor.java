/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Sentencias;

import AST.Nodo;
import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Expresiones.Auxiliar;
import InterpreteCSJ.Expresiones.Expresion;
import InterpreteCSJ.Expresiones.Result;
import InterpreteCSJ.Recolector.ConsJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.TablaSymCSJ;

/**
 *
 * @author jerdu
 */
public class SenFor extends Sentencia{

    public SenFor(Nodo sen) {
        super(sen);
    }

    @Override
    public Result Ejecutar(TablaSymCSJ t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Result respuesta = Result.EjecucionOK();
        
        Nodo DecAsig = new Nodo(ConsCJS.ASIG);
        DecAsig.addHijo(this.sentencia.getHijo(0),this.sentencia.getHijo(1));
        TablaSymCSJ tabla = t.crearTablaHijo();
        new SenDecAsig(DecAsig).Ejecutar(t);
        
        
        while(true){
            Result condicion = new Expresion(this.sentencia.getHijo(2),t).ResolverExpresion();
            
            if(!Auxiliar.esTipo(condicion.getTipo(), ConsJS.BOOL)){
                ManErr.InsertarError("", "Semantico", 0, 0, "La expresión no devuelve booleano SenFor");
                return Result.EjecucionError();
            }
            
            boolean esVerdad = Auxiliar.toBool(condicion.getValor());
            
            if(!esVerdad){
                break;
            }
            
            respuesta = new SenCuerpo(this.sentencia.getHijo(4),false).Ejecutar(tabla);
            
            if(respuesta.isEsReturn()){
                break;
            }
            
            Nodo ex = new Nodo(ConsCJS.EA);
            Nodo op;
            if(this.sentencia.getHijo(3).getLexema().equals("++")){
                op = new Nodo(ConsCJS.INCREMENTO);
                ex.addHijo(op);
            }else{
                op = new Nodo(ConsCJS.DECREMENTO);
                ex.addHijo(op);
            }
            Nodo e = new Nodo(ConsCJS.E);
            e.addHijo(this.sentencia.getHijo(0));
            op.addHijo(e);
            
            
            Result a = new Expresion(ex,tabla).ResolverExpresion();
            t.SetVar(t.getVar(this.sentencia.getHijo(0).getLexema()), a);
            tabla.LimpiarTabla();

        }
        
        tabla.LimpiarTabla();
        return respuesta;
    }
    
}
