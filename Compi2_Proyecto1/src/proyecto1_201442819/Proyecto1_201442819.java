/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_201442819;

import GeneradorCHTML.AnalizadorCHTML;
import GeneradorCJS.AnalizadorCJS;
import Interfaz.Navegador;
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
            //Navegador nav = Navegador.getNavegador();
            //nav.setVisible(true);
            AnalizadorCJS.AnalizarCJS("src\\GeneradorCJS\\prueba_js.cjs");
            //AnalizadorCCSS.AnalizarCCSS("src\\GeneradorCCSS\\prueba_css.ccss");
            //AnalizadorCHTML.AnalizarCH("src\\GeneradorCHTML\\prueba_ch.chtml");
            
        } catch (IOException ex) {
            Logger.getLogger(Proyecto1_201442819.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}