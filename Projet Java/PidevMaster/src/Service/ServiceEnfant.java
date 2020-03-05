/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Classe;
import Entities.Enfant;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import IService.IServiceEnfant;
/**
 *
 * @author House
 */
public class ServiceEnfant implements IServiceEnfant<Enfant> {

    private Connection con;
    private Statement ste;

    public ServiceEnfant() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Enfant t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `projet`.`enfant` (`id`, `nom`, `prenom`,`DateNaissance`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getDateNaissance()+"');";
        ste.executeUpdate(requeteInsert);
    }
          

    public boolean delete(Enfant t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public void SupprimerEnfant(int id){
        String requete="Delete from enfant where id=?";
        
        try {
         PreparedStatement st = con.prepareStatement(requete);  
         st.setInt(1,id);
         st.executeUpdate();
          System.out.println("Enfant supprimé!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
     
       public void modifierEnfant(int id,String nom, String prenom, Date DateNaissance) {
     String sql = "UPDATE  enfant SET `nom`='"+nom+ "',`prenom`='"+prenom+ "',`DateNaissance`='"+DateNaissance+ "' where id = "+id+";";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("Enfant modifié!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
     
      public ObservableList<Enfant> getListEnfants(){
        ObservableList<Enfant> list =FXCollections.observableArrayList();
        String requete="select * from enfant";
        try {
            Statement st = con.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               Enfant p = new Enfant();
               p.setId(rs.getInt(1)); 
               p.setNom(rs.getString(2)); 
               p.setPrenom(rs.getString(3));
               p.setDateNaissance(rs.getDate(4));
               
               list.add(p);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public String getNomClasse(int id){
    ObservableList<Classe> list = FXCollections.observableArrayList();
    
    String requete = "select * from classe where idclasse="+id;
     
            
       
        try {
            ste = con.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while(rs.next())
            {
                
            Classe c = new Classe();
            c.setIdClasse(rs.getInt(1));
            c.setNomClasse(rs.getString(2));
            
            list.add(c);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }

    return list.get(0).getNomClasse();
    }
      public ObservableList<Enfant> getListEnfants2(int id){
        ObservableList<Enfant> list =FXCollections.observableArrayList();
        String requete="select * from enfant where idparent="+id;
        try {
            Statement st = con.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               Enfant p = new Enfant(rs.getInt(1),
              rs.getString(2),
              rs.getString(3),
               rs.getDate(4)
               ,getNomClasse(rs.getInt(6))
               );
               
               list.add(p);
               System.out.println(p);
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ObservableList<String> getListEnfants3(){
        ObservableList<String> list =FXCollections.observableArrayList();
        String requete="select nom from enfant where idparent is NULL";
        try {
            Statement st = con.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               
               
               list.add(rs.getString(1));
             
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public List<Enfant> rechercherEnfant(String Nom,String Enfant1) {
        List<Enfant> list = new ArrayList<>();
        String requete;
      
            requete = "select * from enfant where "+Nom+" like '%"+Enfant1+"%'";
     
        try {
            Statement st = con.createStatement();       
           ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {

            Enfant p = new Enfant();
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
            p.setPrenom(rs.getString(3));
            p.setDateNaissance(rs.getDate(4));
           
            list.add(p);
        }
    }
    catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list ;
}

   public List<Enfant> afficherEnfant() throws SQLException {
        List<Enfant> l = new ArrayList<>();       
       
        try {
            String req="SELECT * FROM enfant";
            Statement s=DataBase.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Enfant e = new Enfant();
            e.setNom(rs.getString("nom"));
            e.setPrenom(rs.getString("prenom"));
            e.setDateNaissance(rs.getDate("DateNaissance"));
            l.add(e);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
}
 
    public List<Enfant>  readAll() throws SQLException {
        ArrayList l = new ArrayList();
        return l;
    }

 
    public void update(Enfant p) throws SQLException {
        
    PreparedStatement pre=con.prepareStatement("update enfant set nom=?, prenom=? where Id=?");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setInt(3, p.getId());
    pre.executeUpdate();
    }
    
     public ObservableList<Enfant> rechercheEnfant(String esmElcolumn, String elibechtlawej3lih) {
    ObservableList<Enfant> list = FXCollections.observableArrayList();
        String requete;
      
            requete = "select * from enfant where "+esmElcolumn+" like '%"+elibechtlawej3lih+"%'";
     
        try {
            Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {

            Enfant e = new Enfant();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString(3));
            e.setDateNaissance(rs.getDate(4));
            
            list.add(e);
        }
    }
    catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list ;
}
     public void affecterEnfant(String nom, int idparent) {
     String sql = "UPDATE  enfant SET idparent="+idparent+ " where nom = '"+nom+"';";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("Enfant modifié!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
     public void affecterEnfantClasse(String nom, int idparent) {
     String sql = "UPDATE  enfant SET idclasse="+idparent+ " where nom = '"+nom+"';";

        PreparedStatement st;
        try {
           
            st = con.prepareStatement(sql);
       
        st.executeUpdate();
        System.out.println("Enfant modifié!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
     public ObservableList<Enfant> getListEnfants1(){
        ObservableList<Enfant> list =FXCollections.observableArrayList();
        String requete="SELECT enfant.*, classe.NomClasse FROM enfant,classe WHERE classe.IdClasse=enfant.idclasse";
        try {
            Statement st = con.createStatement();
           ResultSet rs =  st.executeQuery(requete);
           while(rs.next()){
               Enfant p = new Enfant();
               p.setId(rs.getInt(1)); 
               p.setNom(rs.getString(2)); 
               p.setPrenom(rs.getString(3));
               p.setDateNaissance(rs.getDate(4));
               p.setNomclasse(rs.getString(7));
               
               list.add(p);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     
     
}
