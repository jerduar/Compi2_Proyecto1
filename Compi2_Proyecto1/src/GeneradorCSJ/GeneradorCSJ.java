/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorCSJ;

/**
 *
 * @author jerdu
 */
public class GeneradorCSJ {
    
    //CLASE PRINCIPAL PARA GENERAR LAS CLASES DE ANALIZADOR
    public static void main(String args[]){
        generarCompiladorCSJ();
    }
    
    private static void generarCompiladorCSJ(){
        try {
            String ruta = "src/GeneradorCSJ/";
            String opcFlex[] = { ruta + "LexicoCSJ.flex", "-d", ruta };
            jflex.Main.generate(opcFlex);
            String opcCUP[] = { "-destdir", ruta, "-parser", "parserCSJ","-symbols","symCSJ",  ruta + "SintacticoCSJ.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
           
}
