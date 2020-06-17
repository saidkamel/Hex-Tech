/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

/**
 *
 * @author alaka
 */
public class Activite {
    private float id ;
    private String nom ;
    private String description;
    private String type;

    public Activite() {
    }

    public Activite(String nom, String description, String type) {
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    public Activite(float id, String nom, String description, String type) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "uwu: Activite{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type=" + type + '}';
    }
    
    
}
