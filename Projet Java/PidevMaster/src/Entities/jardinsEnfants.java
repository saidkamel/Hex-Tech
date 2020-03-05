/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author asus
 */
public class jardinsEnfants {
    int id ;
    String nom;
    int capacite;
    int num;
    String mail;
    String activite;
    String localisation ;

    public jardinsEnfants(int id, String nom, int capacite, int num, String mail, String activite, String localisation) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.num = num;
        this.mail = mail;
        this.activite = activite;
        this.localisation = localisation;
    }

    public jardinsEnfants() {
        /*throw new UnsupportedOperationException("Not supported yet.");*/ //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getNum() {
        return num;
    }

    public String getMail() {
        return mail;
    }

    public String getActivite() {
        return activite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "jardinsEnfants{" + "id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", num=" + num + ", mail=" + mail + ", activite=" + activite + ", localisation=" + localisation + '}';
    }
    
    
}
