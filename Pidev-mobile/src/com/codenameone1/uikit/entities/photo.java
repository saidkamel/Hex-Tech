/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.entities;

/**
 *
 * @author mekki
 */
public class photo {
    int id;
    String img, img1, img2, img3;

    public photo(int id, String img, String img1, String img2, String img3) {
        this.id = id;
        this.img = img;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public photo() {
    }
    
    
    
    @Override
    public String toString() {
        return "Media{" + "id=" + id + ", img=" + img + ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3 + '}';
    }
    
}
