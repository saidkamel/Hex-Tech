/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author House
 */
public class Enfant {
    private int id;
    private String nom;
    private String prenom;
    private Date DateNaissance ;
    private String nomclasse;

    public Enfant(int id, String nom, String prenom, Date DateNaissance, String nomclasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaissance = DateNaissance;
        this.nomclasse = nomclasse;
    }

    public String getNomclasse() {
        return nomclasse;
    }

    public void setNomclasse(String nomclasse) {
        this.nomclasse = nomclasse;
    }

    public Enfant(int id, String nom, String prenom,Date DateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaissance =DateNaissance;
        
        
    }
      public Enfant() {
    }

    public Enfant(String nom, String prenom,Date DateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.DateNaissance =DateNaissance;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date DateNaissance) {
        this.DateNaissance = DateNaissance;
    }

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", DateNaissance=" + DateNaissance + '}';
    }

    

  

   
    
}
