/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import GeneradorCJS.ConsCJS;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author jerduar
 */
public class Nodo {

    private String id;
    private String lexema;
    private ArrayList<Nodo> hijos;
    private static int contador;
    private static String cadena_dot;
    private Integer cod;

    public Nodo(String id, String lexema) {
        this.id = id;
        this.lexema = lexema;
        this.hijos = new ArrayList<>();
    }
    
    public Nodo(Integer cod, String valor){
        this.cod = cod;
        this.lexema = valor;
        this.hijos = new ArrayList<>();
    }
    
    public Nodo(Integer cod){
        this.cod = cod;
        this.lexema = "";
        this.hijos = new ArrayList<>();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * @param lexema the lexema to set
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     *
     * @param id
     * @param lexema
     */
    public void addHijo(Integer id, String lexema) {
        getHijos().add(new Nodo(id, lexema));
    }

    /**
     *
     * @param hijo Agregaun hijo recibiendo como parametro un nodo
     */
    public void addHijo(Nodo ... hijo) {
        for(Nodo h: hijo){
            hijos.add(h);
        }
    }

    public void DibujarAST() {
        cadena_dot = "";
        contador = 0;
        Visitar(this);
        CrearDot();
    }

    private String Visitar(Nodo e) {
        String id_padre = "Nodo" + contador;
        contador++;
        cadena_dot += id_padre + "[label=\"" + ConsCJS.RetornStringCSJ(e.cod) + " " + e.lexema + "\"];\n";
        for (Nodo hijo : e.getHijos()) {
            if (hijo != null) {
                String cad = id_padre + "->" + Visitar(hijo) + ";\n";
                cadena_dot += cad;
            }

        }

        return id_padre;
    }

    private void CrearDot() {
        try {
            File archivo_dot = new File("arbol.txt");
            FileWriter escribir = new FileWriter(archivo_dot, false);
            escribir.write("digraph Grafo{\n " + cadena_dot + "\n}\n");
            escribir.close();
            CrearImg("C:\\Users\\jerdu\\Documents\\NetBeansProjects\\Compi2_Proyecto1\\Compi2_Proyecto1\\arbol.txt","C:\\Users\\jerdu\\Documents\\NetBeansProjects\\Compi2_Proyecto1\\Compi2_Proyecto1\\grafo.png");
        } catch (Exception e) {
            System.err.println("Error al crear el DOT");
        } finally {

        }
    }

    private void CrearImg(String direccionDot, String direccionImg) {
        try {
            /*ProcessBuilder img;
            img = new ProcessBuilder("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe", "-Tpng", "-o", direccionImg, direccionDot);
            img.redirectErrorStream(true);
            img.start();*/
            String[]  cmd = {"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe","-Tpng",direccionDot,"-o",direccionImg};
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (Exception e) {
            System.out.println(e.toString());;
        }

    }

    /**
     * @return the hijos
     */
    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

}
