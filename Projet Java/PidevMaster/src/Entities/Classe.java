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
public class Classe {
    private int IdClasse;
    private String NomClasse;

    public Classe(String NomClasse) {
        this.NomClasse = NomClasse;
    }

    public Classe() {
       
   }

    @Override
    public String toString() {
        return "\nClasse{" + "IdClasse=" + IdClasse + ", NomClasse=" + NomClasse + '}';
    }

    public int getIdClasse() {
        return IdClasse;
    }

    public void setIdClasse(int IdClasse) {
        this.IdClasse = IdClasse;
    }

    public String getNomClasse() {
        return NomClasse;
    }

    public void setNomClasse(String NomClasse) {
        this.NomClasse = NomClasse;
    }
    
    
}
