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
public class Activite {
    private int IdActivite;
    private String NomActivite;
    private String Description;
    private String Type;

    public Activite(String NomActivite, String Description, String Type) {
        this.NomActivite = NomActivite;
        this.Description = Description;
        this.Type = Type;
    }

    public Activite() {
       
    }

    public int getIdActivite() {
        return IdActivite;
    }

    public void setIdActivite(int IdActivite) {
        this.IdActivite = IdActivite;
    }

    public String getNomActivite() {
        return NomActivite;
    }

    public void setNomActivite(String NomActivite) {
        this.NomActivite = NomActivite;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "\nActivite{" + "IdActivite=" + IdActivite + ", NomActivite=" + NomActivite + ", Description=" + Description + ", Type=" + Type + '}';
    }
    
    
    
}
