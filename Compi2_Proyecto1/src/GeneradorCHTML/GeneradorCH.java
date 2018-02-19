/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCHTML;

/**
 *
 * @author jerdu
 */
public class GeneradorCH {
    public static void main(String[] args) {
        GenerarCH();
    }
    
    public static void GenerarCH(){
        try {
            String ruta = "src/GeneradorCHTML/";
            String opcFlex[] = { ruta + "LexicoCHTML.flex", "-d", ruta };
            jflex.Main.generate(opcFlex);
            String opcCUP[] = { "-destdir", ruta, "-parser", "parserCH","-symbols","symCH",  ruta + "SintacticoCHTML.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
