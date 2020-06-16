/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

import java.util.Date;

/**
 *
 * @author mondh
 */
public class Evenement {
    private int id,nbrParticipants,nbrPlaces;
    private String nom,type,description;
    private Date DateDebut,DateFin;

    public Evenement(int id, int nbrParticipants, int nbrPlaces, String nom, String type, String description, Date DateDebut, Date DateFin) {
        this.id = id;
        this.nbrParticipants = nbrParticipants;
        this.nbrPlaces = nbrPlaces;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nbrParticipants=" + nbrParticipants + ", nbrPlaces=" + nbrPlaces + ", nom=" + nom + ", type=" + type + ", description=" + description + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + '}';
    }
    
    
    
    
}
