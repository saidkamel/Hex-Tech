/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author alaka
 */
public class Employe {
    int id ;
    String nom;
    String prenom;
    int cin;
    String titre;
    int salaire;
    String email;
    int absence;

    public Employe() {
    }

    public Employe(String nom, String prenom, int cin, String titre, int salaire, String email, int absence) {
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.salaire = salaire;
        this.email=email;
        this.absence=absence;
        this.cin=cin;
        

    }

    

    public Employe(int id, String nom, String prenom, String titre, int salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.salaire = salaire;

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

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin +", titre=" + titre + ", salaire=" + salaire + ", email=" + email + ", absence=" + absence + '}';
    }

    
    public String afficherSalaire(){
    return "votre salaire est:"+salaire;
    
    }
  public String afficherAbsence(){
    return "Le nombre de votre absences est = "+absence;
    
    }
  public String afficherAccount(){
    return "votre login est "+email+" votre mot de passe est "+cin;
    
    }

}
