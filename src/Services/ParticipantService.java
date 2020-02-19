/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entitiy.participant;
import Entitiy.evenement;
import Utils.Connexion;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mondh
 */
public class ParticipantService {
    Connection cn = Connexion.getInstance().getCnx();

 
                public void AjouterParticipant(participant p){
        
         
        String requete="INSERT INTO participant (Nom_p, Prenom_p, mdp_p) VALUES ('"+p.getNom_p()+"','"+p.getPrenom_p()+"','"+p.getMdp()+"')";
        
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("ajout avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        
    
    
    
  
public ObservableList<participant> getListParticipants(){
        ObservableList<participant> List =FXCollections.observableArrayList();
        String requete="select * from participant";
        try {
            Statement st = cn.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               participant p = new participant();
               p.setId_p(rs.getInt(1)); 
               p.setNom_p(rs.getString(2)); 
               p.setPrenom_p(rs.getString(3));
               p.setMdp(rs.getString(4));
               List.add(p);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return List;
        
    }
    public void modifierParticipant(int Id_p,String Nom_p,String Prenom_p,String mdp_p) {
     String sql = "UPDATE   participant SET `Nom_p`='"+Nom_p+ "',`Prenom_p`='"+Prenom_p+ "',`mdp_p`='"+mdp_p+ "' WHERE Id_p='"+Id_p+"' ";

        PreparedStatement st;
        try {
           
            st = cn.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
   
    
    public void SupprimerParticipant(participant p){
        String requete="Delete from participant where Nom_p=?";
        
        try {
         PreparedStatement st = cn.prepareStatement(requete);  
         st.setString(1,p.getNom_p());
         st.executeUpdate();
          System.out.println("Suppression!!");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  
       
       public List<participant> RechercheParticipant(int Id_p){
        List<participant> list = new ArrayList<>();
        String requete="SELECT * FROM participant WHERE 'Id_p'="+Id_p;
        try {
            Statement st = cn.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               participant p = new participant();
               p.setId_p(rs.getInt(1)); // ou p.setId(rs.getInt(1));
               p.setNom_p(rs.getString(2)); // ou p.setNom(rs.getString("nom));
               p.setPrenom_p(rs.getString(3));
               p.setMdp(rs.getString(4));
              
               list.add(p);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
           public void AffectParticipantInEvent(participant p,evenement e){
        
        EvenementService es = new EvenementService();
        String requete="INSERT INTO participant(Nom_p, Prenom_p, mdp_p) VALUES ('"+p.getNom_p()+"','"+p.getPrenom_p()+"','"+p.getMdp()+"')";  //"','"+e.getNbr_participant()+
        es.RetournerNombreParticipant();
       String requete2="UPDATE event SET nbr_participant = nbr_participant +1";
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            st.executeUpdate(requete2);
            System.out.println("ajout avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        
}
