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
public class commentaire {
    String nom ; 
    String prenom ;
    int num ; 
    String sujet ; 
    String commentaire ;

    public commentaire(String nom, String prenom, int num, String sujet, String commentaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.sujet = sujet;
        this.commentaire = commentaire;
    }

    public commentaire() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNum() {
        return num;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "commentaire{" + "nom=" + nom + ", prenom=" + prenom + ", num=" + num + ", sujet=" + sujet + ", commentaire=" + commentaire + '}';
    }
    
    
    
}
