/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterpreteCSJ.Recolector;

/**
 *
 * @author jerdu
 */
public class Error {

    private final String archivo;
    private final String tipo;
    private final String descripcion;
    private final int fila;
    private final int columna;
    
    public Error(String archivo, String tipo, int linea, int columna, String descripcion){
        this.archivo = archivo;
        this.tipo = tipo;
        this.fila = linea;
        this.columna = columna;
        this.descripcion = descripcion;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }
    
    public void Imprimir(){
        System.out.println("Archivo: " + this.archivo + " tipo: " + this.tipo + " Línea: " + this.fila + " Columna: "+ this.getColumna() +" Descripcion: " + this.descripcion);
    }
    
}
