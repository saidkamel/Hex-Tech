/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.reclamation;
import Entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.controleSaisie;
import Utils.DataBase;
import Entities.jardinsEnfants;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class reclamationService {
         Connection cn =DataBase.getInstance().getConnection();
//java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());



public void ajouterReclamation (reclamation r,  User u ) {
        Date a=new Date();
            java.sql.Date d=new java.sql.Date(r.getDate().getTime());
            controleSaisie CS = new controleSaisie();
            

                        if ((CS.testnomprenom(u.getNom())==true) ||(CS.testnomprenom(u.getPrenom())==true)) {
                            String[] result =r.getDescription().split("\\s");
                            String newdesc = "";
                    for (int x=0; x<result.length; x++)
                    {
                    result[x]=CS.filterBadWords(result[x]);
                    newdesc+=result[x];
                    }
            
         String sql = "INSERT INTO `reclamation` ( `cin`, `nom`,`prenom`,`note`,`motif`, "
                 + "`description`, `date`)VALUES ('"+u.getCin()+ "','" + u.getNom()+ "','" +
                 u.getPrenom()+"','"+r.getNote()+ "','" + r.getType()+"','" + newdesc+"','" +d+"');";

  try {
            Statement st = cn.createStatement();
            
            
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       } else
           System.out.println("nom ou prenom du parent non valide");

}

public List<reclamation> getlistReclamation(){
     List<reclamation> list = new  ArrayList<>();
     String sql = "select * from reclamation ";
     Statement st;
        try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             reclamation r = new reclamation();
             r.setCinParent(rs.getInt(1));
             r.setNomParent(rs.getString(2));
             r.setPrenomParent(rs.getString(3));
             r.setNote(rs.getInt(4));
             r.setType(rs.getString(5));
             r.setDescription(rs.getString(6));
             r.setDate(rs.getDate(7));
             list.add(r);
         }
        } catch (SQLException ex) {
            Logger.getLogger(reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
   
     return list;
 }

 public void modifierReclamation(int idd,int note,  String type, String desc) {
            
     /*reclamation r = new reclamation();
     
     Date a=new Date();
            java.sql.Date d=new java.sql.Date(r.getDate().getTime());
    */
        String sql;
       
        
               sql = "UPDATE   `reclamation` SET `note`='"+note+ "',`motif`='"+type+ "',`description`='"+desc+ "' WHERE cin='"+idd+"' ;";

        
try {
            Statement stl = cn.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            Logger.getLogger(reclamation.class.getName()).log(Level.SEVERE, null, ex);
            
        }}
        
 public void SupprimerReclamation(int idd) {

    String sql = "DELETE FROM `reclamation` where cin ='"+idd+"';";
  
    try {
            Statement stl = cn.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            Logger.getLogger(reclamation.class.getName()).log(Level.SEVERE, null, ex);
            
        }        }
    

 public List<reclamation> rechercheReclamationCin(int cinn) {
            
     List<reclamation> list = new  ArrayList<>();
        

            String sql = "SELECT * FROM reclamation  WHERE cin='"+cinn+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             reclamation r = new reclamation();
             r.setCinParent(rs.getInt(1));
             r.setNomParent(rs.getString(2));
             r.setPrenomParent(rs.getString(3));
             r.setNote(rs.getInt(4));
             r.setType(rs.getString(5));
             r.setDescription(rs.getString(6));
             r.setDate(rs.getDate(7));
             list.add(r);

         }
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     }

        
 public List<reclamation> triReclamation(){
     List<reclamation> list = new  ArrayList<>();
     String sql = "select * from reclamation order by motif ";
     Statement st;
        try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             reclamation r = new reclamation();
             r.setCinParent(rs.getInt(1));
             r.setNomParent(rs.getString(2));
             r.setPrenomParent(rs.getString(3));
             r.setNote(rs.getInt(4));
             r.setType(rs.getString(5));
             r.setDescription(rs.getString(6));
             r.setDate(rs.getDate(7));
             list.add(r);
         }
        } catch (SQLException ex) {
            Logger.getLogger(reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
   
     return list;
 }
      public ObservableList<reclamation> displayall() {
        ObservableList<reclamation> listeReclamation=FXCollections.observableArrayList();
        String req= "select * from reclamation";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=cn.prepareStatement(req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
             reclamation reclamation = null;
             /*  Utilisateur cl =new UtilisateurService().rechercheUtilisateurParUsername(resultSet.getString(4));
             System.out.println(cl);*/
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
             reclamation = new reclamation(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7));
             listeReclamation.add(reclamation);
                
            }
        } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
        }
        return listeReclamation;
    }
      
      
       public void traiteReclamation ( int id){
String sql= "UPDATE `reclamation` SET `etat` = 'traite' WHERE (id_salle ='"+id+"');";
try {
            Statement stl = cn.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
           
            
 
                   }}


}
