/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;



/**
 *
 * @author bhk
 */
public class Prenom {
    private int id;
    private String Nom,Prenom,DateNaissance;
    
    
    public Prenom(int id, String Nom, String Prenom,String DateNaissance) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.DateNaissance = DateNaissance;
    }

 
    public Prenom(String Nom, String Prenom,String DateNaissance) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.DateNaissance = DateNaissance;
    }
  
    public Prenom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(String DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

  

    @Override
    public String toString() {
        return  ""+Prenom+"";
    }




}

    