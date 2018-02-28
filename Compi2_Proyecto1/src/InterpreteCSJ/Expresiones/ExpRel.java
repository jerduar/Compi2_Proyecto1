/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Expresiones;

import InterpreteCSJ.Recolector.ConsJS;
import java.util.Date;
import java.util.Objects;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;

/**
 *
 * @author jerdu
 */
public class ExpRel {

    public static Result Igual_Igual(Result izq, Result der) {
        Result respuesta = new Result();
        boolean r;

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) {

            //NUM == NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = (der.getValor() == null ? izq.getValor() == null : der.getValor().equals(izq.getValor()));
                return CrearResult(r);
            }
            //NUM == CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = Auxiliar.ToNum(izq.getValor()) == der.getValor().length();
                return CrearResult(r);
            }
            //NUM == BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Objects.equals(Auxiliar.ToNum(izq.getValor()), Auxiliar.BoolToNum(der.getValor()));
                return CrearResult(r);
            }
        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)) {
            //CAD == NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = izq.getValor().length() == Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD == CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = (izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor()));
                return CrearResult(r);
            }
            //CAD == DATE/DATETIME
            if (Auxiliar.esDateoDT(der.getTipo())) {
                r = (izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor()));
                return CrearResult(r);
            }
        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)) {
            //BOOL == NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Objects.equals(Auxiliar.BoolToNum(izq.getValor()), Auxiliar.ToNum(der.getValor()));
                return CrearResult(r);
            }
            //BOOL == BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Objects.equals(Auxiliar.BoolToNum(izq.getValor()), Auxiliar.BoolToNum(der.getValor()));
                return CrearResult(r);
            }
        }

        //YA SOLO QUEDA EL CASO EN QUE SEAN CADENA O DATETIME
        return CrearResult((izq.getValor() == null ? der.getValor() == null : izq.getValor().equals(der.getValor())));
    }

    public static Result menorque(Result izq, Result der) {
        Result respuesta = new Result();
        boolean r;

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) {

            //NUM < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.ToNum(izq.getValor()) < Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //NUM < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = Auxiliar.ToNum(izq.getValor()) < der.getValor().length();
                return CrearResult(r);
            }
            //NUM < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.ToNum(izq.getValor()) < Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)) {

            //CAD < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = izq.getValor().length() < Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = izq.getValor().length() < der.getValor().length();
                return CrearResult(r);
            }
            //CAD < DATE/DATETIME
            if (Auxiliar.esDateoDT(der.getTipo())) {
                Date op1, op2;
                op2 = Auxiliar.toDate(der.getValor());

                op1 = Auxiliar.toDateH(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) < 0);
                }
                op1 = Auxiliar.toDate(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) < 0);
                }
                return CrearResult(izq.getValor().length() < der.getValor().length());
            }

        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)) {
            //BOOL < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.BoolToNum(izq.getValor()) < Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //BOOL < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.BoolToNum(izq.getValor()) < Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esDateoDT(izq.getTipo())) {
            Date op1 = Auxiliar.toDateH(izq.getValor());
            if (op1 == null) {
                op1 = Auxiliar.toDate(izq.getValor());
            }
            //System.out.println(op1);

            // DATE/DATETIME < CAD || DATE/DATETIME < DATE/DATEDATIME
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA_HORA)) {
                Date op2;
                op2 = Auxiliar.toDateH(der.getValor());
                if (op2 != null) {
                    System.out.println("AQUI WEY");
                    System.out.println(op2);
                    return CrearResult(CompDate(op1, op2) < 0);
                }
                op2 = Auxiliar.toDate(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) < 0);
                }

                return CrearResult(izq.getValor().length() < der.getValor().length());
            }
        }

        return respuesta;
    }

    public static Result mayorque(Result izq, Result der) {
        Result respuesta = new Result();
        boolean r;

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) {

            //NUM < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.ToNum(izq.getValor()) > Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //NUM < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = Auxiliar.ToNum(izq.getValor()) > der.getValor().length();
                return CrearResult(r);
            }
            //NUM < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.ToNum(izq.getValor()) > Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)) {

            //CAD < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = izq.getValor().length() > Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = izq.getValor().length() > der.getValor().length();
                return CrearResult(r);
            }
            //CAD < DATE/DATETIME
            if (Auxiliar.esDateoDT(der.getTipo())) {
                Date op1, op2;
                op2 = Auxiliar.toDate(der.getValor());

                op1 = Auxiliar.toDateH(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) > 0);
                }
                op1 = Auxiliar.toDate(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) > 0);
                }
                return CrearResult(izq.getValor().length() > der.getValor().length());
            }

        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)) {
            //BOOL < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.BoolToNum(izq.getValor()) > Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //BOOL < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.BoolToNum(izq.getValor()) > Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esDateoDT(izq.getTipo())) {
            Date op1 = Auxiliar.toDateH(izq.getValor());
            if (op1 == null) {
                op1 = Auxiliar.toDate(izq.getValor());
            }

            // DATE/DATETIME > CAD || DATE/DATETIME > DATE/DATEDATIME
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA_HORA)) {
                Date op2;

                op2 = Auxiliar.toDateH(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) > 0);
                }
                op2 = Auxiliar.toDate(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) > 0);
                }

                return CrearResult(izq.getValor().length() > der.getValor().length());
            }
        }

        return respuesta;
    }
    
    public static Result menorigualque(Result izq, Result der){
        Result respuesta = new Result();
        boolean r;

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) {

            //NUM < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.ToNum(izq.getValor()) <= Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //NUM < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = Auxiliar.ToNum(izq.getValor()) <= der.getValor().length();
                return CrearResult(r);
            }
            //NUM < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.ToNum(izq.getValor()) <= Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)) {

            //CAD < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = izq.getValor().length() <= Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = izq.getValor().length() <= der.getValor().length();
                return CrearResult(r);
            }
            //CAD < DATE/DATETIME
            if (Auxiliar.esDateoDT(der.getTipo())) {
                Date op1, op2;
                op2 = Auxiliar.toDate(der.getValor());

                op1 = Auxiliar.toDateH(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) <= 0);
                }
                op1 = Auxiliar.toDate(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) <= 0);
                }
                return CrearResult(izq.getValor().length() <= der.getValor().length());
            }

        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)) {
            //BOOL < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.BoolToNum(izq.getValor()) <= Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //BOOL < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.BoolToNum(izq.getValor()) <= Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esDateoDT(izq.getTipo())) {
            Date op1 = Auxiliar.toDateH(izq.getValor());
            if (op1 == null) {
                op1 = Auxiliar.toDate(izq.getValor());
            }

            // DATE/DATETIME > CAD || DATE/DATETIME > DATE/DATEDATIME
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA_HORA)) {
                Date op2;

                op2 = Auxiliar.toDateH(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) <= 0);
                }
                op2 = Auxiliar.toDate(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) <= 0);
                }

                return CrearResult(izq.getValor().length() <= der.getValor().length());
            }
        }

        return respuesta;
    }
    
    public static Result mayorigualque(Result izq, Result der){
        Result respuesta = new Result();
        boolean r;

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.NUM)) {

            //NUM < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.ToNum(izq.getValor()) >= Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //NUM < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = Auxiliar.ToNum(izq.getValor()) >= der.getValor().length();
                return CrearResult(r);
            }
            //NUM < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.ToNum(izq.getValor()) >= Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.CAD)) {

            //CAD < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = izq.getValor().length() >= Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //CAD < CAD
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD)) {
                r = izq.getValor().length() >= der.getValor().length();
                return CrearResult(r);
            }
            //CAD < DATE/DATETIME
            if (Auxiliar.esDateoDT(der.getTipo())) {
                Date op1, op2;
                op2 = Auxiliar.toDate(der.getValor());

                op1 = Auxiliar.toDateH(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) >= 0);
                }
                op1 = Auxiliar.toDate(izq.getValor());
                if (op1 != null) {
                    System.out.println(op1);
                    return CrearResult(CompDate(op1, op2) >= 0);
                }
                return CrearResult(izq.getValor().length() >= der.getValor().length());
            }

        }

        if (Auxiliar.esTipo(izq.getTipo(), ConsJS.BOOL)) {
            //BOOL < NUM
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.NUM)) {
                r = Auxiliar.BoolToNum(izq.getValor()) >= Auxiliar.ToNum(der.getValor());
                return CrearResult(r);
            }
            //BOOL < BOOL
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.BOOL)) {
                r = Auxiliar.BoolToNum(izq.getValor()) >= Auxiliar.BoolToNum(der.getValor());
                return CrearResult(r);
            }
        }

        if (Auxiliar.esDateoDT(izq.getTipo())) {
            Date op1 = Auxiliar.toDateH(izq.getValor());
            if (op1 == null) {
                op1 = Auxiliar.toDate(izq.getValor());
            }

            // DATE/DATETIME > CAD || DATE/DATETIME > DATE/DATEDATIME
            if (Auxiliar.esTipo(der.getTipo(), ConsJS.CAD) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA) || Auxiliar.esTipo(der.getTipo(), ConsJS.FECHA_HORA)) {
                Date op2;

                op2 = Auxiliar.toDateH(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) >= 0);
                }
                op2 = Auxiliar.toDate(der.getValor());
                if (op2 != null) {
                    return CrearResult(CompDate(op1, op2) >= 0);
                }

                return CrearResult(izq.getValor().length() >= der.getValor().length());
            }
        }

        return respuesta;
    }

    private static Result CrearResult(boolean valor) {
        return new Result(ConsJS.BOOL, valor + "");
    }

    private static int CompDate(Date op1, Date op2) {
        
        DateTimeComparator n = DateTimeComparator.getInstance();
        System.out.println(n.compare(op1, op2));
        return n.compare(op1, op2);
    }

}
