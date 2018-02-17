/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCCSS;

/**
 *
 * @author jerdu
 */
public class GeneradorCCSS {
    public static void main(String[] args) {
        generarCompiladorCCSS();
    }
    
    private static void generarCompiladorCCSS(){
        try {
            String ruta = "src/GeneradorCCSS/";
            String opcFlex[] = { ruta + "LexicoCCSS.flex", "-d", ruta };
            jflex.Main.generate(opcFlex);
            String opcCUP[] = { "-destdir", ruta, "-parser", "parserCCSS","-symbols","symCCSS",  ruta + "SintacticoCCSS.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
