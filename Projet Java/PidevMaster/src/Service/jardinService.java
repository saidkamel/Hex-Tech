/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.jardinsEnfants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.controleSaisie;
import Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import pidev.controleSaisie;

/**
 *
 * @author asus
 */
public class jardinService {
 private Connection con;
    private Statement ste;
   public static jardinService getInstance() {
       return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     Connection cn =DataBase.getInstance().getConnection();
    
     

 
 public void ajouterJardins(jardinsEnfants j )
    {
        controleSaisie CS = new controleSaisie();
        if (CS.testnomprenom(j.getNom())==true) {
            if(CS.mailformat(j.getMail())==true){
               
                    
        
   String sql =  " INSERT INTO `jardins`(`id`, `nom`,`capacite`,`num`,`mail`,`activite`,`localisation`) VALUES"
           + " ('"+j.getId()+"','"+j.getNom()+"','"+j.getCapacite()+"','"+j.getNum()+"','"+j.getMail()+"','"+j.getActivite()+"','"+j.getLocalisation()+"')";  
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            System.out.println("ajout");
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        
        }}else 
                System.out.println("format mail non valide ");
        }
        else 
            System.out.println("erreur nom");
           
}
 public void ajouterJar (jardinsEnfants j ){
     String sql = "insert into personne (id,nom,capacite,num,mail,activite,localisation) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, j.getId());
            pst.setString(2, j.getNom());
            pst.setInt(3, j.getCapacite());
            pst.setInt(4, j.getNum());
            pst.setString(5, j.getMail());
            pst.setString(6, j.getActivite());
            pst.setString(7, j.getLocalisation());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
 public List<jardinsEnfants> getlistjardins(){
     List<jardinsEnfants> list = new  ArrayList<>();
     String sql = "select * from jardins ";
     Statement st;
        try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             jardinsEnfants j = new jardinsEnfants();
             j.setId(rs.getInt(1));
             j.setNom(rs.getString(2));
             j.setCapacite(rs.getInt(3));
             j.setNum(rs.getInt(4));
             j.setMail(rs.getString(5));
             j.setActivite(rs.getString(6));
             j.setLocalisation(rs.getString(7));
             list.add(j);
         }
       } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
   
     return list;
 }
  /*public void updatejardins(int i) {
        
       String req= "UPDATE `carte_fidelite` SET `nb_point`=`nb_point` + 50 where `id_user` = '"+username+"';";
        System.out.println(req);
           PreparedStatement preparedStatement = null;
           try {
           
           
          preparedStatement = conn.prepareStatement(req);
          
             
            preparedStatement.executeUpdate();
       } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
       }*/
 
    public void Supprimerjardins(int idd) {

    String sql = "DELETE FROM `jardins` where id ='"+idd+"';";
   //String sql = "INSERT INTO fos_user(username) VALUES ('"+c.getUsername()+"');";
  
    try {
            Statement stl = cn.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
            
        }        }
    
    
    public void modifierJardins(int idd, String nom, int capacite, int num, String mail, String activite, String localisation) {

    
        String sql;
       
        
               sql = "UPDATE   `jardins` SET `nom`='"+nom+ "',`capacite`='"+capacite+ "',`num`='"+num+ "',`mail`='"+mail+ "',`activite`='"+activite+ "',`localisation`='"+localisation+ "' WHERE id='"+idd+"' ;";

        
try {
            Statement stl = cn.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
            
        }}
        
         public jardinsEnfants recherchejardinsnom(String nom) {
            
             jardinsEnfants j = new jardinsEnfants();
        

            String sql = "SELECT * FROM jardins  WHERE nom='"+nom+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
            // jardinsEnfants j = new jardinsEnfants();
             j.setId(rs.getInt(1));
             j.setNom(rs.getString(2));
             j.setCapacite(rs.getInt(3));
             j.setNum(rs.getInt(4));
             j.setMail(rs.getString(5));
             j.setActivite(rs.getString(6));
             j.setLocalisation(rs.getString(7));
         }
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;     }
         
         
         
         /****************************************************/
         
         
         
         
          public jardinsEnfants recherchejardinsid(int id) {
            
             jardinsEnfants j = new jardinsEnfants();
        

            String sql = "SELECT * FROM jardins  WHERE id='"+id+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
            // jardinsEnfants j = new jardinsEnfants();
             j.setId(rs.getInt(1));
             j.setNom(rs.getString(2));
             j.setCapacite(rs.getInt(3));
             j.setNum(rs.getInt(4));
             j.setMail(rs.getString(5));
             j.setActivite(rs.getString(6));
             j.setLocalisation(rs.getString(7));
         }
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;     }
          
          
          
          /**********************************************/
          
          
           public List<jardinsEnfants> recherchejardinsactivite(String activite) {
            
     List<jardinsEnfants> list = new  ArrayList<>();
        

            String sql = "SELECT * FROM jardins  WHERE activite='"+activite+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             jardinsEnfants j = new jardinsEnfants();
             j.setId(rs.getInt(1));
             j.setNom(rs.getString(2));
             j.setCapacite(rs.getInt(3));
             j.setNum(rs.getInt(4));
             j.setMail(rs.getString(5));
             j.setActivite(rs.getString(6));
             j.setLocalisation(rs.getString(7));
             list.add(j);

         }
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     }

    
    /*******************************/
           
           
           
           
           
           public List<jardinsEnfants> recherchejardinsnum(int num) {
            
     List<jardinsEnfants> list = new  ArrayList<>();
        

            String sql = "SELECT * FROM jardins  WHERE num='"+num+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             jardinsEnfants j = new jardinsEnfants();
             j.setId(rs.getInt(1));
             j.setNom(rs.getString(2));
             j.setCapacite(rs.getInt(3));
             j.setNum(rs.getInt(4));
             j.setMail(rs.getString(5));
             j.setActivite(rs.getString(6));
             j.setLocalisation(rs.getString(7));
             list.add(j);

         }
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     }
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           public List<jardinsEnfants> recherchejardinsmail(String mail) {
            
     List<jardinsEnfants> list = new  ArrayList<>();
        

            String sql = "SELECT * FROM jardins  WHERE mail='"+mail+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             jardinsEnfants j = new jardinsEnfants();
             j.setId(rs.getInt(1));
             j.setNom(rs.getString(2));
             j.setCapacite(rs.getInt(3));
             j.setNum(rs.getInt(4));
             j.setMail(rs.getString(5));
             j.setActivite(rs.getString(6));
             j.setLocalisation(rs.getString(7));
             list.add(j);

         }
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;     }
           
           
           public ObservableList<jardinsEnfants> displayall() {
        ObservableList<jardinsEnfants> listeJardin=FXCollections.observableArrayList();
        String req= "select * from jardins";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=cn.prepareStatement(req);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
             jardinsEnfants JardinsEnfants = null;
             /*  Utilisateur cl =new UtilisateurService().rechercheUtilisateurParUsername(resultSet.getString(4));
             System.out.println(cl);*/
             
             JardinsEnfants = new jardinsEnfants(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
                boolean add = listeJardin.add(JardinsEnfants);
                
            }
        } catch (SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
        }
        return listeJardin;
    }
           
           public void remove(Integer r) throws SQLException {
               jardinsEnfants ca=new jardinsEnfants(r, "", 0, 0, "", "", "");
      
        PreparedStatement pre=con.prepareStatement("DELETE FROM `pidev`.`jardins` WHERE `id` = ? ");
        pre.setInt(1,ca.getId());
        pre.executeUpdate();
           
        
    }
         /*  String req = "delete from jardins where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            preparedStatement.setInt(1, r);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());*/
}