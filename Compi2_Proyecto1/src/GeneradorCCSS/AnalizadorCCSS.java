/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCCSS;

import AST.Nodo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author jerdu
 */
public class AnalizadorCCSS {
    
    public static void AnalizarCCSS(String direccion) throws FileNotFoundException, IOException{
        
        FileReader f = new FileReader(direccion);
        BufferedReader b = new BufferedReader(f);
        String texto = "",cadena = "";
        
        while((cadena = b.readLine()) != null){
            texto += cadena;
        }
        
        if(texto.isEmpty()){
            System.err.println("No es posible evaluar una cadena en blanco.");
            return;
        }
        try {
            
            System.out.println("Inicia la generación de CCSS...");
            scannerCCSS scan = new scannerCCSS(new BufferedReader( new StringReader(texto)));
            parserCCSS parser = new parserCCSS(scan);
            Nodo a = (Nodo)parser.parse().value;
            a.DibujarAST(2);
            
            System.out.println("Finaliza la generación de CCSS...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
