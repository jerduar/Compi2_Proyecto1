/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

import java.util.ArrayList;

/**
 *
 * @author jerdu
 */
public class ManErr {

    public static ArrayList<Error> tabla = new ArrayList<>();
    private String archivo;

    public ManErr() {
        this.tabla = new ArrayList<>();
    }
    
    public void setErrores(){
        this.tabla = new ArrayList<>();
    }

    public void ResetearTabla() {
        this.tabla.clear();
    }

    public static void InsertarError(String archivo, String tipo, int fila, int columna, String descripcion) {
        if (tabla != null) {
            Error nuevo = new Error(archivo, tipo, fila, columna, descripcion);
            nuevo.Imprimir();
            ManErr.tabla.add(nuevo);
        }

    }

}
