/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mekki
 */
public class Subscription 
{
    int id;
    String nomEnfant, nomC;
    

    public Subscription(int id, String nomEnfant, String nomC) {
        this.id = id;
        this.nomEnfant = nomEnfant;
        this.nomC = nomC;
    }

    public Subscription(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }
    

    public Subscription(String nomEnfant, String nomC) {
        this.nomEnfant = nomEnfant;
        this.nomC = nomC;
    }

    
    public Subscription() {
     
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }
    
    
    
    

}