/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author mekki
 */
public class addParent {
    private int id;
    private String nom;
    private String prenom;

    
    private String cin;
    private String email;
    private String phone;
public addParent() {
    }
    public addParent(int id,String nom, String prenom, String cin, String email, String phone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.phone = phone;
    }
    public addParent(String nom, String prenom, String cin, String email, String phone) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.phone = phone;
    }
    

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    

    

    public int getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

   
    

    @Override
    public String toString() {
        return "Parent{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", phone=" + phone +  '}';
    }
    
}

