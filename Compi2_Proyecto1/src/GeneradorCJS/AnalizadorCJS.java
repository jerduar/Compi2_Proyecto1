/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCJS;

import AST.Nodo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java_cup.runtime.Symbol;

/**
 *
 * @author jerdu
 */
public class AnalizadorCJS {
    
    public static void AnalizarCJS(String direccion) throws IOException, FileNotFoundException{
        
        FileReader f = new FileReader(direccion);
        BufferedReader b = new BufferedReader(f);
        String texto = "",cadena = "";
        
        while((cadena = b.readLine()) != null){
            texto += cadena + "\n";
        }
        
        if(texto.isEmpty()){
            System.err.println("No es posible evaluar una cadena en blanco.");
            return;
        }
        
        if(texto.isEmpty()){
            System.err.println("No es posible evaluar una cadena en blanco.");
            return;
        }
        try {
            
            
            scannerCJS scan = new scannerCJS(new BufferedReader( new StringReader(texto)));
            
            parserCJS parser = new parserCJS(scan);
            Nodo a = (Nodo)parser.parse().value;
            a.DibujarAST(1);
            
            System.out.println("Finaliza la generación de CSJ...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
