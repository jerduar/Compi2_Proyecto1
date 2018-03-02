/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCHTML;

import AST.Nodo;
import GeneradorCJS.parserCJS;
import GeneradorCJS.scannerCJS;
import InterpreteCSJ.Recolector.ManErr;
import InterpreteCSJ.Recolector.RecolectorCSJ;
import InterpreteCSJ.Recolector.TablaSymCSJ;
import InterpreteCSJ.Sentencias.SenCuerpo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author jerdu
 */
public class AnalizadorCHTML {
    public static void AnalizarCH(String direccion) throws IOException, FileNotFoundException {

        FileReader f = new FileReader(direccion);
        BufferedReader b = new BufferedReader(f);
        String texto = "", cadena = "";

        while ((cadena = b.readLine()) != null) {
            texto += cadena + "\n";
        }

        if (texto.isEmpty()) {
            System.err.println("No es posible evaluar una cadena en blanco.");
            return;
        }
        try {

            scannerCH scan = new scannerCH(new BufferedReader(new StringReader(texto)));

            parserCH parser = new parserCH(scan);
            Nodo a = (Nodo) parser.parse().value;
            a.DibujarAST(3);
            /*if (a != null) {
                ManErr m = new ManErr();  
                TablaSymCSJ t = RecolectorCSJ.LlenarTabla(m,a.getHijo(0));           
                new SenCuerpo(a.getHijo(0), true).Ejecutar(t);
            } else {
                System.out.println("No hay nodos para recolectar :(");
            }*/

            System.out.println("Finaliza la generación de CHTML...");
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}
