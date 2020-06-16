/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

/**
 *
 * @author mondh
 */
public class Participant {
    private int id;
    private String nom,prenom;
    private String Email;
    private Evenement event;
    private String evenement;

    public Participant(int id, String nom, String prenom, String Email, String evenement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.evenement = evenement;
    }
    

  


    public Participant( String nom, String prenom, String Email) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
    }

    public Participant() {
    }

    public Participant(int id, String nom, String prenom, String Email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
    }

    public Participant(int id) {
        this.id=id;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", evenement=" + evenement + '}';
    }

   

  
    
    
    
}
