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
public class ClasseActivite {
        private int IdActivite;
        private int IdClasse;

    public ClasseActivite() {
    }

    public ClasseActivite(int IdClasse , int IdActivite) {
        this.IdActivite = IdActivite;
        this.IdClasse = IdClasse;
    }

    @Override
    public String toString() {
        return "\nClasseActivite{" + "IdActivite=" + IdActivite + ", IdClasse=" + IdClasse + '}';
    }

    public int getIdActivite() {
        return IdActivite;
    }

    public void setIdActivite(int IdActivite) {
        this.IdActivite = IdActivite;
    }

    public int getIdClasse() {
        return IdClasse;
    }

    public void setIdClasse(int IdClasse) {
        this.IdClasse = IdClasse;
    }
    
}
