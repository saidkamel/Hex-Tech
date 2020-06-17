
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiy;

/**
 *
 * @author mondh
 */
public class participant {
    private int Id_p;
    private String Nom_p;
    private String Prenom_p;
    private String mdp_p;
   

    public participant(String Nom_p, String Prenom_p, String mdp_p) {
        this.Nom_p = Nom_p;
        this.Prenom_p = Prenom_p;
        this.mdp_p = mdp_p;
     
    }

    public participant() {
    }

    public int getId_p() {
        return Id_p;
    }

    public void setId_p(int Id_p) {
        this.Id_p = Id_p;
    }

    
    public String getNom_p() {
        return Nom_p;
    }

    public void setNom_p(String Nom_p) {
        this.Nom_p = Nom_p;
    }

    public String getPrenom_p() {
        return Prenom_p;
    }

    public void setPrenom_p(String Prenom_p) {
        this.Prenom_p = Prenom_p;
    }

    public String getMdp() {
        return mdp_p;
    }

      public void setMdp(String mdp_p) {
        this.mdp_p=mdp_p;
    }

    @Override
    public String toString() {
        return "\n participant{" + "Id_p=" + Id_p + ", Nom_p=" + Nom_p + ", Prenom_p=" + Prenom_p + ", mdp_p=" + mdp_p +'}';
    }
    

    
    
    
    
}
