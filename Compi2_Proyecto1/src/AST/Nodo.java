/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import GeneradorCCSS.ConsCSS;
import GeneradorCJS.ConsCJS;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author jerduar
 */
public class Nodo {
    
    private String lexema;
    private ArrayList<Nodo> hijos;
    private static Integer contador;
    private static String cadena_dot;
    private Integer cod;
    
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

    public void DibujarAST(int arbol) {
        cadena_dot = "";
        contador = 0;
        switch(arbol){
            case 1:
                VisitarCJS(this);
                break;
            case 2:
                VisitarCSS(this);
                break;
            case 3:
                //VisitarCHTML(this);
                break;
            default:
                System.out.println("No se envio paramétro valido para el dibujo del arbol");
                break;
        }
        CrearDot(arbol);
    }

    private String VisitarCJS(Nodo e) {
        String id_padre = "Nodo" + contador;
        contador++;
        cadena_dot += id_padre + "[label=\"" + ConsCJS.RetornStringCSJ(e.cod) + " " + e.lexema + "\"];\n";
        for (Nodo hijo : e.getHijos()) {
            if (hijo != null) {
                String cad = id_padre + "->" + VisitarCJS(hijo) + ";\n";
                cadena_dot += cad;
            }

        }

        return id_padre;
    }
    
    private String VisitarCSS(Nodo e){
        String id_padre = "Nodo" + contador;
        contador++;
        cadena_dot += id_padre + "[label=\"" + ConsCSS.RetornStringCSS(e.cod) + " " + e.lexema + "\"];\n";
        for (Nodo hijo : e.getHijos()) {
            if (hijo != null) {
                String cad = id_padre + "->" + VisitarCSS(hijo) + ";\n";
                cadena_dot += cad;
            }

        }

        return id_padre;
    }

    private void CrearDot(int i) {
        try {
            File archivo_dot = new File("arbol" + i + ".txt");
            FileWriter escribir = new FileWriter(archivo_dot, false);
            escribir.write("digraph Grafo{\n " + cadena_dot + "\n}\n");
            escribir.close();
            CrearImg("C:\\Users\\jerdu\\Documents\\NetBeansProjects\\Compi2_Proyecto1\\Compi2_Proyecto1\\arbol" + i + ".txt",
                    "C:\\Users\\jerdu\\Documents\\NetBeansProjects\\Compi2_Proyecto1\\Compi2_Proyecto1\\grafo" + i + ".png");
        } catch (Exception e) {
            System.err.println("Error al crear el DOT");
        } finally {

        }
    }

    private void CrearImg(String direccionDot, String direccionImg) {
        try {
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
