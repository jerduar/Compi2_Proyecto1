/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import AST.Nodo;
import GeneradorCJS.ConsCJS;
import InterpreteCSJ.Recolector.ConsJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.TablaSymCSJ;
import java.util.Objects;

/**
 *
 * @author jerdu
 */
public class Expresion {

    private Nodo raiz;
    private TablaSymCSJ ctx;
    //private ManejadorErrores ma;

    /**
     * Constructores
     */
    public Expresion() {
        this.raiz = null;
        this.ctx = null;
        //this.ma = ManejadorErrores.getInstance("Lienzo_prueba.lz");
    }

    public Expresion(Nodo expresion, TablaSymCSJ ctx) {
        this.raiz = expresion;
        this.ctx = ctx;
        //this.ma = ManejadorErrores.getInstance("Lienzo_prueba.lz");
    }

    public Result ResolverExpresion(Nodo raiz) {
        Result r = new Result();
        //E
        if (raiz.getCod() == ConsCJS.E) {
            return ResolverExpresion(raiz.getHijo(0));
            //Expresion Aritmetica
        } else if (raiz.getCod() == ConsCJS.EA) {
            return ResolverAritmetica(raiz.getHijo(0));
        } else if (raiz.getCod() == ConsCJS.ER) {
            return ResolverRelacional(raiz.getHijo(0));
        }
        r = ResolverHoja(raiz);
        //System.out.println("tipo " + r.getTipo() + " valor " + r.getValor());
        return r;
    }

    public Result ResolverExpresion() {
        return ResolverExpresion(this.raiz);
    }

    private Result ResolverAritmetica(Nodo op) {
        Result r = new Result();
        Result izq, der;

        izq = ResolverExpresion(op.getHijo(0));
        if (Auxiliar.esError(izq.getTipo()) || izq.getValor() == null) {
            ManErr.InsertarError("", "Semantico", 0, 0, "Hay un problema con el operador izquierdo, no se puede operar");
            return r;
        }
        if (op.getCod() == ConsCJS.MENOS) {

            if (op.getTam() == 1) {
                if (!Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) {
                    ManErr.InsertarError("", "Semantico", 0, 0, "No se puede si no es número");
                    return r;
                }
                return ExpArit.menosE(izq);
            }
        }

        if (Objects.equals(izq.getStatus(), ConsJS.ERROR) || izq.getValor() == null) {
            //ERROR
            System.out.println("HUBO UN ERROR");
            return r;
        }

        //SI ES UN INCREMENTO
        if (op.getCod() == ConsCJS.INCREMENTO) {
            if (!(Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) || Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)) {
                //ERROR
                System.out.println("No se puede incrementar en datos que no sean numeros");
                return r;
            }
            return ExpArit.Incremento(izq);
        }

        //DECREMENTO
        if (op.getCod() == ConsCJS.DECREMENTO) {
            if (!(Objects.equals(izq.getTipo(), ConsJS.NUM) || Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL))) {
                //ERROR
                System.out.println("No se puede decrementar datos que no sean números");
            }
            return ExpArit.Decremento(izq);

        }

        if (op.getTam() <= 1) {
            return r;
        }

        der = ResolverExpresion(op.getHijo(1));
        if (Auxiliar.esError(der.getTipo()) || der.getValor() == null) {
            ManErr.InsertarError("", "Semantico", 0, 0, "Hay un problema con el operador derecho, no se puede operar");
            return r;
        }

        if (Auxiliar.esDateoDT(izq.getTipo()) || Auxiliar.esDateoDT(der.getTipo())) {
            ManErr.InsertarError("", "Semantico", 0, 0, "No se pueden operar fechas");
            return r;
        }

        //SIGNO MAS
        if (op.getCod() == ConsCJS.MAS) {
            return ExpArit.Suma(izq, der);
        }
        //SIGNO MENOS
        if (op.getCod() == ConsCJS.MENOS) {
            if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL) && Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                ManErr.InsertarError("", "Semantico", 0, 0, "No se pueden restar booleanos");
                return r;
            }

            return ExpArit.Resta(izq, der);
        }
        //SIGNO MULT
        if (op.getCod() == ConsCJS.MULT) {
            if (Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD) || Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                ManErr.InsertarError("", "Semantico", 0, 0, "No se pueden multiplicar cadenas");
                return r;
            }

            return ExpArit.Multiplicacion(izq, der);
        }
        //SIGNO DIV
        if (op.getCod() == ConsCJS.DIV) {
            if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL) && Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                ManErr.InsertarError("", "Sementico", 0, 0, "No se pueden dividir entre booleanos");
                return r;
            }

            return ExpArit.Division(izq, der);
        }

        if (op.getCod() == ConsCJS.POT) {
            if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL) && Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                ManErr.InsertarError("", "Sementico", 0, 0, "No se pueden potenciar entre booleanos");
                return r;
            }

            return ExpArit.potencia(izq, der);
        }

        if (op.getCod() == ConsCJS.MOD) {
            return ExpArit.modulo(izq, der);
        }

        return r;
    }

    private Result ResolverRelacional(Nodo op) {
        Result respuesta = new Result();

        Result izq = ResolverExpresion(op.getHijo(0));
        if (Auxiliar.esError(izq.getTipo()) || izq.getValor() == null) {
            ManErr.InsertarError("", "Semantico", 0, 0, "Hay un problema con el operador izquierdo, no se puede operar");
            return respuesta;
        }
        Result der = ResolverExpresion(op.getHijo(1));
        if (Auxiliar.esError(der.getTipo()) || der.getValor() == null) {
            ManErr.InsertarError("", "Semantico", 0, 0, "Hay un problema con el operador derecho, no se puede operar");
            return respuesta;
        }

        if ((Auxiliar.esDateoDT(izq.getTipo()) && (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)))
                || (Auxiliar.esDateoDT(der.getTipo()) && Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM))) {
            ManErr.InsertarError("", "Semantico", 0, 0, "No se puede comparar date/datetime con numericos");
            return respuesta;
        }

        if ((Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL) && !(Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL) || Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)))
                || ((Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL) && !(Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL) || Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM))))) {
            ManErr.InsertarError("", "Semantico", 0, 0, "Solo se pueden comparar bool con bool o numericos");
            return respuesta;
        }
        
        if (op.getCod() == ConsCJS.IGUAL) {
            
            return ExpRel.Igual_Igual(izq, der);
        }

        return respuesta;
    }

    private Result ResolverHoja(Nodo hoja) {
        Result resultado = new Result();
        if (hoja.getCod() == ConsJS.CAD
                || hoja.getCod() == ConsJS.NUM
                || hoja.getCod() == ConsJS.FECHA
                || hoja.getCod() == ConsJS.FECHA_HORA
                || hoja.getCod() == ConsJS.BOOL) {

            resultado.setTipo(hoja.getCod());
            //System.out.println("es hoja " + hoja.getLexema() + " " + hoja.getCod() + " " + ConsJS.NUM);
            resultado.setValor(hoja.getLexema());
            //System.out.println("es resultado " + resultado.getValor() + " " + resultado.getTipo() + " " + ConsJS.NUM);
            return resultado;
        }

        if (hoja.getCod() == ConsJS.ID) {

        }

        return resultado;
    }

}
