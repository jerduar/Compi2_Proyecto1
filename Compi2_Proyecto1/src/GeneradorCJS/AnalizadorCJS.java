/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCJS;

import AST.Nodo;
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
public class AnalizadorCJS {

    public static void AnalizarCJS(String direccion) throws IOException, FileNotFoundException {

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

            scannerCJS scan = new scannerCJS(new BufferedReader(new StringReader(texto)));

            parserCJS parser = new parserCJS(scan);
            Nodo a = (Nodo) parser.parse().value;
            a.DibujarAST(1);
            if (a != null) {
                ManErr m = new ManErr();  
                TablaSymCSJ t = RecolectorCSJ.LlenarTabla(m,a.getHijo(0));           
                new SenCuerpo(a.getHijo(0), true).Ejecutar(t);
            } else {
                System.out.println("No hay nodos para recolectar :(");
            }

            System.out.println("Finaliza la generación de CJS...");
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}
