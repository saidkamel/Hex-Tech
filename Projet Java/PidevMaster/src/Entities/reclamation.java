/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author asus
 */
public class reclamation {
    
    int cinParent;
    String nomParent;
    String prenomParent;
    int note ;
    String type;
    String description;
    Date date;
    String etat ;

    public reclamation(int cinParent, String nomParent, String prenomParent, int note, String type, String description, Date date, String etat) {
        this.cinParent = cinParent;
        this.nomParent = nomParent;
        this.prenomParent = prenomParent;
        this.note = note;
        this.type = type;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }
    
    

    public reclamation(int cinParent, String nomParent, String prenomParent, int note, String type, String description,Date date) {
        this.cinParent = cinParent;
        this.nomParent = nomParent;
        this.prenomParent = prenomParent;
        this.note = note;
        this.type = type;
        this.description = description;
        this.date=date;
    }

    public reclamation(int cinParent, String nomParent, String prenomParent, int note, String type, String description) {
        this.cinParent = cinParent;
        this.nomParent = nomParent;
        this.prenomParent = prenomParent;
        this.note = note;
        this.type = type;
        this.description = description;
    }

    public reclamation() {
    }

    

    public int getCinParent() {
        return cinParent;
    }

    public String getNomParent() {
        return nomParent;
    }

    public String getPrenomParent() {
        return prenomParent;
    }
 
    

    public int getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setCinParent(int cinParent) {
        this.cinParent = cinParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }

   

    public void setNote(int note) {
        this.note = note;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "reclamation{" + "cinParent=" + cinParent + ", nomParent=" + nomParent + ", prenomParent=" + prenomParent + ", note=" + note + ", type=" + type + ", description=" + description + ", date=" + date + ", etat=" + etat + '}';
    }

    

    public reclamation(int cinParent, int note, String type, String description, Date date) {
        this.cinParent = cinParent;
        this.note = note;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public reclamation(int cinParent, int note, String type, String description) {
        this.cinParent = cinParent;
        this.note = note;
        this.type = type;
        this.description = description;
    }

    public reclamation(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    
    
    
    
    
    
}
