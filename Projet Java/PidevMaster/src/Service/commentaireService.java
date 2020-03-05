/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.commentaire;
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

/**
 *
 * @author asus
 */
public class commentaireService {
     Connection cn =DataBase.getInstance().getConnection();
    
    
 public void ajouterCommentaire (commentaire c )
    {
         controleSaisie CS = new controleSaisie();
        if ((CS.testnomprenom(c.getNom())==true)||(CS.testnomprenom(c.getPrenom())==true)) {
          /* String str =Integer.toString(c.getNum());
            
            if (CS.GSM(str)==true){*/
   String sql =  " INSERT INTO `commentaire`(`nom`, `prenom`,`num`,`sujet`,`comm`) VALUES"
           + " ('"+c.getNom()+"','"+c.getPrenom()+"','"+c.getNum()+"','"+c.getSujet()+"','"+c.getCommentaire()+"')";  
   
   
        try {Statement st = cn.createStatement();
                       /* String sqlbad = "Select * from badwords";
                        
                        ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String bad = rs.getString(sqlbad);
                if (bad.equals(bad)){
                    System.out.println("bad word ");
                }}*/
            
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }//}else System.out.println("veifier numero");
        }else System.out.println("verifier nom ou prenom ");
           
}
 public void ajouterCom (commentaire c ){
   //  String sql2= "insert into test (commtest) values (?"
     String sql = "insert into commentaire (nom,prenom,num,sujet, comm) values (?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setInt(3, c.getNum());
            pst.setString(4, c.getSujet());
            pst.setString(5, c.getCommentaire());
            String sqlbad = "Select * from badwords";
            /*while(rs.next()){
                String bad = rs.getString(sqlbad);
                if (bad.equals(bad)){
                    System.out.println("bad word ");
                }
                
                
            }*/
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
 
 public List<commentaire> getlistcommentaire(){
     List<commentaire> list = new  ArrayList<>();
     String sql = "select * from commentaire ";
     Statement st;
        try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             commentaire c = new commentaire();
             c.setNom(rs.getString(1));
             c.setPrenom(rs.getString(2));
             c.setNum(rs.getInt(3));
             c.setSujet(rs.getString(4));
             c.setCommentaire(rs.getString(5));
           
             list.add(c);
         }
       } catch (SQLException ex) {
            Logger.getLogger(commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
   
     return list;
 }
  
 
    public void SupprimerCommentaire(String nom) {

    String sql = "DELETE FROM `commentaire` where nom ='"+nom+"';";
  
    try {
            Statement stl = cn.createStatement();
           int rs =stl.executeUpdate(sql);
                   } catch (SQLException ex) {
            Logger.getLogger(commentaire.class.getName()).log(Level.SEVERE, null, ex);
            
        }        }
    
    
    
        
         public commentaire rechercheCommentaireNom(String nom) {
            
             commentaire c = new commentaire();
        

            String sql = "SELECT * FROM commentaire  WHERE nom='"+nom+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             c.setNom(rs.getString(1));
             c.setPrenom(rs.getString(2));
             c.setNum(rs.getInt(3));
             c.setSujet(rs.getString(4));
             c.setCommentaire(rs.getString(5));
           
         }
        } catch (SQLException ex) {
            Logger.getLogger(commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;     }
         
         
         
         /**
     * @throws java.sql.SQLException**************************************************/
         
          public List<String> badwordss() throws SQLException
          {
              try{
              List<String> listb = new ArrayList<>();
              String req4 = "select `badword` from badwords";
              Statement st = cn.createStatement();
            st.executeQuery(req4);
            ResultSet rs = st.executeQuery(req4);
            while(rs.next())
            {
               // badwords bd = new badwords();
                listb.add(rs.getString("badword").toUpperCase());
               
               
            }
             return listb ;
              }catch(SQLException ex){
                  
              }
            return null;
            
          }
         
         
          public commentaire rechercheCommentaireSujet(String suj) {
            
             commentaire c = new commentaire();
        

            String sql = "SELECT * FROM commentaire  WHERE sujet='"+suj+"' ;";
                
            Statement st;

            try {
            st = cn.createStatement();
         ResultSet rs=      st.executeQuery(sql);
         while(rs.next()){
             c.setNom(rs.getString(1));
             c.setPrenom(rs.getString(2));
             c.setNum(rs.getInt(3));
             c.setSujet(rs.getString(4));
             c.setCommentaire(rs.getString(5));
            
         }
        } catch (SQLException ex) {
            Logger.getLogger(commentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;     }
          
          
          
        
           
       
           
           
           
    
}
