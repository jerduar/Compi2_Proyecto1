/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_201442819;

import GeneradorCCSS.AnalizadorCCSS;
import GeneradorCJS.AnalizadorCJS;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jerdu
 */
public class Proyecto1_201442819 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AnalizadorCJS.AnalizarCJS("src\\GeneradorCJS\\prueba_js.cjs");
            AnalizadorCCSS.AnalizarCCSS("src\\GeneradorCCSS\\prueba_css.ccss");
        } catch (IOException ex) {
            Logger.getLogger(Proyecto1_201442819.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}