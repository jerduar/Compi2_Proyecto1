/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCSJ;

import java.io.BufferedReader;
import java.io.StringReader;
import java_cup.runtime.Symbol;

/**
 *
 * @author jerdu
 */
public class AnalizadorCSJ {
    
    public static void Analizar(String texto){
        
        if(texto.isEmpty()){
            System.err.println("No es posible evaluar una cadena en blanco.");
            return;
        }
        try {
            
            System.out.println("Inicia la generación de CSJ...");
            scanner scan = new scanner(new BufferedReader( new StringReader(texto)));
            //Symbol s = (Symbol)scan.next_token();
            //PRUEBA DE ANALISIS LEXICA
            /*while(s.sym != 0){
                System.out.println("lexema: " + s.value + " token: " + s.sym + " columna: " + scan.yylength());
                s = (Symbol)scan.next_token();
            }*/
            parser parser = new parser(scan);
            parser.parse();
            
            System.out.println("Finaliza la generación de CSJ...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
