/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

import java.util.Date;

/**
 *
 * @author bhk
 */
public class EmploiDuTemps {
    private int id;
    private String Image,Classe;
    
    
    public EmploiDuTemps(int id, String Image, String Classe) {
        this.id = id;
        this.Image = Image;
        this.Classe = Classe;
     
    }

    public EmploiDuTemps(String Image, String Classe) {
        this.Image = Image;
        this.Classe = Classe;
    }
  
    public EmploiDuTemps() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    @Override
    public String toString() {
        return "EmploiDuTemps{" + "id=" + id + ", Image=" + Image + ", Classe=" + Classe + '}';
    }

   



}

    