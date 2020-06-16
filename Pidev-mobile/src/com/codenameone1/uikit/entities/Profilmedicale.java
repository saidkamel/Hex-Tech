/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

/**
 *
 * @author sa3do
 */
public class Profilmedicale {
    int id;
    int Taille;
    int Poids;
    String Maladie;
    String Etat;
   
    String nomP;
    String prenomP;
    String emailP;
    String nomE;
    String prenomE;

    @Override
    public String toString() {
        return "\nProfilmedicale \n" + " \nEnfant: nom=" + nomE + ", prenom=" + prenomE + "\nTaille=" + Taille + ", Poids=" + Poids + ", Maladie=" + Maladie + ", Etat=" + Etat + ", \nPediatre: nom=" + nomP + ", prenom=" + prenomP + ", email=" + emailP  ;
    }
    public String listeMail(){
        return "Email: "+emailP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaille() {
        return Taille;
    }

    public void setTaille(int Taille) {
        this.Taille = Taille;
    }

    public int getPoids() {
        return Poids;
    }

    public void setPoids(int Poids) {
        this.Poids = Poids;
    }

    public String getMaladie() {
        return Maladie;
    }

    public void setMaladie(String Maladie) {
        this.Maladie = Maladie;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

   

   
    
}
