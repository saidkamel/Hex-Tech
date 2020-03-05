/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author mondh
 */

public class evenement {


    private int Id;
    private String Nom;
    private String Type;
    private Date Date_Debut;
    private Date Date_Fin;
    private String Description;
    private int nbr_participant;

    public evenement( String Nom, String Type, Date Date_Debut, Date Date_Fin, String Description, int nbr_participant) {
       
        this.Nom = Nom;
        this.Type = Type;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.Description = Description;
        this.nbr_participant = nbr_participant;
    }

    public evenement(String Nom) {
        this.Nom = Nom;
    }
    
    


    public evenement() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

  

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getNbr_participant() {
        return nbr_participant;
    }

    public void setNbr_participant(int nbr_participant) {
        this.nbr_participant = nbr_participant;
    }

    @Override
    public String toString() {
        return "\n evenement{" + "Id=" + Id + ", Nom=" + Nom + ", Type=" + Type + ", Date_Debut=" + Date_Debut + ", Date_Fin=" + Date_Fin + ", Description=" + Description + ", nbr_participant=" + nbr_participant + '}';
    }

   

 
    
}
