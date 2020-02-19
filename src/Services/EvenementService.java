/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entitiy.evenement;
import Utils.Connexion;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXAboutDialog;
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
public class EvenementService {
    Connection cn = Connexion.getInstance().getCnx();

 
                public void AjouterEvenement(evenement p){
         if(p.getDate_Debut().after(p.getDate_Fin())){
            System.out.println("Date incorrecte!!!!!");
       }
        else{
        String requete="INSERT INTO event (Nom ,Type,Date_Debut,Date_Fin,Description,nbr_participant) VALUES ('"+p.getNom()+"','"+p.getType()+"','"+p.getDate_Debut()+"','"+p.getDate_Fin()+"','"+p.getDescription()+"','"+p.getNbr_participant()+"')";
        
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("ajout avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        
    }
    
    
    
    public void ajouterEvenement(evenement p){
        String requete="INSERT INTO event (Nom ,Type,Date_Debut,Date_Fin,Description) VALUES (?,?,?,?)";
        try {
            
             //Date dd =new Date(02,11,2020); 
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getType());
           pst.setDate(3,p.getDate_Debut());
            pst.setDate(3,p.getDate_Fin());
            pst.setString(4, p.getDescription());
            int rowsInserted=pst.executeUpdate();
            if(rowsInserted>0)
            System.out.println("Ajout!");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public List<evenement> getListEvenement(){
        List<evenement> list = new ArrayList<>();
        String requete="select * from event";
        try {
            Statement st = cn.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               evenement p = new evenement();
               p.setId(rs.getInt(1)); // ou p.setId(rs.getInt(1));
               p.setNom(rs.getString(2)); // ou p.setNom(rs.getString("nom));
               p.setType(rs.getString(3));
               p.setDate_Debut(rs.getDate(4));
               p.setDate_Fin(rs.getDate(5));
               p.setDescription(rs.getString(6));
               p.setNbr_participant(rs.getInt(7));
               list.add(p);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ObservableList<evenement> getListEvenements(){
        ObservableList<evenement> list =FXCollections.observableArrayList();
        String requete="select * from event";
        try {
            Statement st = cn.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               evenement p = new evenement();
               p.setId(rs.getInt(1)); // ou p.setId(rs.getInt(1));
               p.setNom(rs.getString(2)); // ou p.setNom(rs.getString("nom));
               p.setType(rs.getString(3));
               p.setDate_Debut(rs.getDate(4));
               p.setDate_Fin(rs.getDate(5));
               p.setDescription(rs.getString(6));
               p.setNbr_participant(rs.getInt(7));
               list.add(p);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     
    public void modifierEvenement(int Id,String Nom, String Type, Date Date_Debut,Date Date_Fin,String Description,int nbr_participant) {
     String sql = "UPDATE   event SET `Nom`='"+Nom+ "',`Type`='"+Type+ "',`Date_Debut`='"+Date_Debut+ "',`Date_Fin`='"+Date_Fin+ "',`description`='"+Description+ "',`nbr_participant`='"+nbr_participant+ "' WHERE Id='"+Id+"' ;";

        PreparedStatement st;
        try {
           
            st = cn.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
   
    
    public void SupprimerEvenement(evenement p){
        String requete="Delete from event where Nom=?";
        
        try {
         PreparedStatement st = cn.prepareStatement(requete);  
         st.setString(1,p.getNom());
         st.executeUpdate();
          System.out.println("Suppression!!");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
       
       
       public List<evenement> rechercherEvenement(String Nom,String Event1) {
        List<evenement> list = new ArrayList<>();
        String requete;
      
            requete = "select * from event where "+Nom+" like '%"+Event1+"%'";
     
        try {
            Statement st = cn.createStatement();       
           ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {

            evenement p = new evenement();
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
            p.setType(rs.getString(3));
            p.setDate_Debut(rs.getDate(4));
            p.setDate_Fin(rs.getDate(5));
            p.setDescription(rs.getString(4));
            p.setNbr_participant(rs.getInt(7));
            list.add(p);
        }
    }
    catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list ;
}

       public List<evenement> trieEvenement(String s) 
    {
        String requete = "SELECT * FROM event order by "+s;
        PreparedStatement pst;
            List<evenement> list = new ArrayList<>();

        try {
            pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            evenement e = new evenement();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setType(rs.getString(3));
            e.setDate_Debut(rs.getDate(4));
            e.setDate_Fin(rs.getDate(5));
            e.setDescription(rs.getString(6));
             e.setNbr_participant(rs.getInt(1));
            list.add(e);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
          
        
    }
       public int RetournerNombreParticipant(){
          
           String requete="SELECT count(*) from event";
         //  int nbr = Integer.parseInt(requete);
           int nbr = Integer.valueOf(requete);
           evenement evnt = new evenement();
              try {
         PreparedStatement pst = cn.prepareStatement(requete); 
         
         ResultSet rs = pst.executeQuery();

         evnt.setNbr_participant(rs.getInt(7));

         pst.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return nbr;
        }
       
       public ObservableList<String> AffecterParticipants() throws SQLException{
        ObservableList<String> list =FXCollections.observableArrayList();
        String requete="select Nom from event";
       
            PreparedStatement pst = cn.prepareStatement(requete);
           ResultSet rs =  pst.executeQuery(requete);
           //String s="";
           while(rs.next()){
                     
               list.add(rs.getString(1));
                           
           }
      return list;}
       
       public void ParticipantsNumber(String s) throws SQLException{
           ArrayList<Integer> list = new ArrayList<>();
           String sql = "select nbr_participant from event where Nom='"+s+"'";
           PreparedStatement pst = cn.prepareStatement(sql);
           ResultSet rs =  pst.executeQuery(sql);
           int nbr;
           while(rs.next()){
               nbr=rs.getInt(1)+1;
               
                       
               String sql1="update event set nbr_participant='"+nbr+"' where Nom='"+s+"'";
               PreparedStatement pst1 = cn.prepareStatement(sql1);
               pst1.executeUpdate();
           System.out.println(nbr);
           }

           
       
       
       
}
}
       
       

