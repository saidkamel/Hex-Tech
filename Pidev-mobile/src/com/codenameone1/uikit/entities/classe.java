/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

import java.util.ArrayList;

/**
 *
 * @author alaka
 */
public class classe {
   
    private int id ;
    private String NomClasse ;
    private String Activite ;
    private ArrayList<Activite> ListActivite1 = new ArrayList<>();

    public void addList(Activite a ){
    ListActivite1.add(a);
    }

    public ArrayList<Activite> getListActivite1() {
        return ListActivite1;
    }
    
    

    public classe() {
    }

  
    public classe(int id, String NomClasse) {
        this.id = id;
        this.NomClasse = NomClasse;
    }
    
    public classe(int id) {
        this.id = id;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomClasse() {
        return NomClasse;
    }

    public void setNomClasse(String NomClasse) {
        this.NomClasse = NomClasse;
    }

    public String getActivite() {
        return Activite;
    }

    public void setActivite(String NomActivite) {
        this.Activite = NomActivite;
    }

    @Override
    public String toString() {
        return "classe{\n" + "id=" + id + ", \nNomClasse=" + NomClasse + ", \nActivite=" + Activite + '}';
    }

    
    
}
