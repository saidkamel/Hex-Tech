/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Employe;
import Entities.addParent;
import IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Mekki
 */
public class ServiceParent implements IService<addParent> {

    private Connection con;
    private Statement ste;
    

    public ServiceParent() {
        con = DataBase.getInstance().getConnection();

    }
    

    @Override
    public void ajouter(addParent p) throws SQLException {      
       String requete="INSERT INTO parent ( `Nom`, `Prenom`, `Cin`, `Email`, `Phone`) VALUES ('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getCin()+"', '"+p.getEmail()+"','"+p.getPhone()+"')";
        
        try {
            Statement st = con.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    
    

    @Override
    public void delete(addParent t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("delete from `projet`.`parent` where Id=?");
			setEnfantNull(t.getId());
                        pre.setInt(1, t.getId());
			pre.executeUpdate();
                        
        
    }
                        
        
    

    
    public void modifierParent(int Id,String Nom,String Prenom, String Cin, String Email, String Phone) {
     String sql = "UPDATE   parent SET `Nom`='"+Nom+ "',`Prenom`='"+Prenom+ "',`Cin`='"+Cin+ "',`Phone`='"+Phone+ "',`Email`='"+Email+ "' WHERE Id='"+Id+"' ";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
        

        
    
    
    

    @Override
    public ObservableList<addParent> readAll() throws SQLException {
    ObservableList<addParent> arr= FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from parent");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String cin = rs.getString("cin");
               String email=rs.getString("email");
               String phone=rs.getString("phone");
               addParent p=new addParent( id, nom, prenom,cin, email, phone);
     arr.add(p);
     }  return arr;
}
    
    
    @Override
     public List<addParent> trieParent(String o ) 
    {
        String requete = "SELECT * FROM Parent order by "+o+" desc";
        PreparedStatement pst;
            List<addParent> list = new ArrayList<>();

        try {
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String email=rs.getString("email");
               String cin = rs.getString("cin");
               String phone=rs.getString("phone");
               addParent p=new addParent(id, nom, prenom,cin, email, phone);
            list.add(p);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
          
    }
    
    

public ObservableList<addParent> rechercheParent(String Nom) {
        
        String requete = "SELECT * FROM Parent where nom = '"+Nom+"' " ;
        PreparedStatement pst;
        ObservableList<addParent> list= FXCollections.observableArrayList();
            

        try {
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String cin = rs.getString("cin");
               String email=rs.getString("email");
               String phone=rs.getString("phone");
               addParent p=new addParent(id, nom, prenom,cin, email, phone);
            list.add(p);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
          
    }
public void setEnfantNull(int id)
{
String requete = "UPDATE `enfant` SET idParent=null  WHERE idparent= " +id;
PreparedStatement st;
        try {
           
            st = con.prepareStatement(requete);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void setEnfantNull2(int idenfant,int idparent)
{
String requete = "UPDATE `enfant` SET idParent=null  WHERE idparent= " +idparent+" and id="+idenfant;
PreparedStatement st;
        try {
           
            st = con.prepareStatement(requete);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public int checkParent(String nom) 
{
            String requete ="SELECT count(*) FROM `parent` WHERE nom = '"+nom+"'";
            PreparedStatement pst;
            int check=0;
        try {
            
            
            ResultSet rs;
            pst = con.prepareStatement(requete);
            rs = pst.executeQuery();
             while(rs.next())
            {
            check = rs.getInt(1);
            }
           
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return check;
}

    public addParent getParent(String mail) {
        addParent e = new addParent();
    String requete="select * from parent where email ='"+mail+"'";
        try {
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
           
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString("prenom"));
            e.setCin(rs.getString(6));
            e.setEmail(rs.getString(4));
      
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    
    return e; 
    
    }
   public int CheckParent(String mail){
          
           String requete ="SELECT count(*) FROM `parent` WHERE email = '"+mail+"'";
            PreparedStatement pst;
            int check=0;
        try {
            
            
            ResultSet rs;
            pst = con.prepareStatement(requete);
            rs = pst.executeQuery();
             while(rs.next())
            {
            check = rs.getInt(1);
            }
           
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return check;

   
}


}

   
    


          
        
    

    
   


