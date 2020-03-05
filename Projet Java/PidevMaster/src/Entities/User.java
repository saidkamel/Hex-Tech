/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author sa3do
 */
public class User {
    private String nom;
    private String prenom;
    private int cin;
    private String mail ;

    public User(String nom, String prenom, int cin, String mail, String pass, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.mail = mail;
        this.pass = pass;
        this.role = role;
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
      private String pass ;
    private String role ;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public User(String mail, String pass, String role) {
        this.mail = mail;
        this.pass = pass;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "mail=" + mail + ", pass=" + pass + ", role=" + role + '}';
    }
  
    
    
    
}
