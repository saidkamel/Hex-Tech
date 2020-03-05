package Service;


import Entities.Employe;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alaka
 */
public class EmployeService {
    Connection cn = DataBase.getInstance().getConnection();
   
    public void ajouterEmploye(Employe e){
    String requete="insert into employe(nom,prenom,cin,titre,salaire,email,absence)values  ( '"+e.getNom()+"', '"+e.getPrenom()+"', '"+e.getCin()+"', '"+e.getTitre()+"', '"+e.getSalaire()+"', '"+e.getEmail()+"', '"+e.getAbsence()+"')";
        try { 
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Employe ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Employe getEmploye(String mail)
    {
    Employe e = new Employe();
    String requete="select * from employe where email ='"+mail+"'";
        try {
            Statement st =cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
           
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString("prenom"));
            e.setCin(rs.getInt("cin"));
            e.setTitre(rs.getString("titre"));
            e.setSalaire(rs.getInt("salaire"));
            e.setEmail(rs.getString("email"));
            e.setAbsence(rs.getInt(8));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    
    return e;
    
    }
    public void modifierEmploye(int id ,String nom, String prenom,int cin, String titre, int salaire, String email,int absence){
    String requete="update employe set nom='"+nom+"',prenom='"+prenom+"',cin='"+cin+"', titre='"+titre+"', salaire='"+salaire+"', email='"+email+"', absence='"+absence+"' where id="+id+"";
        try { 
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Employe modifié");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }

    
    public void supprimerEmploye(int id){
        String requete="delete from employe where id="+id+"";
        try { 
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("Employe supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Employe consulterSalaire(int id){
        String requete="select salaire from employe where id="+id+"";
        Employe e = new Employe();

        try { 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            e.setSalaire(rs.getInt("salaire"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    
    public Employe nombreAbsence(int id){
        String requete="select absence from employe where id="+id+"";
        Employe e = new Employe();

        try { 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            e.setAbsence(rs.getInt("absence"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    
     public Employe loginAccount(int id){
        String requete="select email,cin from employe where id="+id+"";
        Employe e = new Employe();

        try { 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            e.setEmail(rs.getString("email"));
            e.setCin(rs.getInt("cin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    public ObservableList<Employe> consulterEmploye(){
    ObservableList<Employe> list = FXCollections.observableArrayList();
    String requete="select * from employe";
        try {
            Statement st =cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            Employe e = new Employe();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString("prenom"));
            e.setCin(rs.getInt("cin"));
            e.setTitre(rs.getString("titre"));
            e.setSalaire(rs.getInt("salaire"));
            e.setEmail(rs.getString("email"));
            e.setAbsence(rs.getInt(8));
            list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return list ;
    }
    public List<Employe> trieEmploye(String o ) 
    {
        String requete = "SELECT * FROM employe order by " +o;
        PreparedStatement pst;
            List<Employe> list = new ArrayList<>();

        try {
            pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            Employe e = new Employe();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString(3));
            e.setCin(rs.getInt(8));
            e.setTitre(rs.getString(4));
            e.setSalaire(rs.getInt(5));
            e.setEmail(rs.getString(6));
            e.setAbsence(rs.getInt(7));
            list.add(e);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
             
    }
    public ObservableList<Employe> rechercheEmploye(String esmElcolumn, String elibechtlawej3lih) {
    ObservableList<Employe> list = FXCollections.observableArrayList();
        String requete;
      
            requete = "select * from employe where "+esmElcolumn+" like '%"+elibechtlawej3lih+"%'";
     
        try {
            Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {

            Employe e = new Employe();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString(3));
            e.setCin(rs.getInt(4));
            e.setTitre(rs.getString(5));
            e.setSalaire(rs.getInt(6));
            e.setEmail(rs.getString(7));
            e.setAbsence(rs.getInt(8));
            list.add(e);
        }
    }
    catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list ;
}

       }
