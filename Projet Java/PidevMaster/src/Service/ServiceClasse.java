/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Activite;
import Entities.Classe;
import IService.IClasse;
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

/**
 *
 * @author sa3do
 */
public class ServiceClasse implements IClasse{
    private Connection con;
    private Statement ste;

    public ServiceClasse() {
        con = DataBase.getInstance().getConnection();
    }
    public void ajouterClasse(Classe c)
    {
    String requete="insert into classe (nomclasse) values('"+c.getNomClasse()+"')";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public ObservableList<Classe> getListClasse(){
    ObservableList<Classe> list = FXCollections.observableArrayList();
    String requete = "select * from classe";
     
            
       
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

    return list;
    }
    public void supprimerClasse(int id){
    
    String requete = "delete from classe where id = '" +id+ "'";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete);
            setEnfantVide(id);
            System.out.println("supp avec succée ! ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierClasse(int id,String nom){
    String requete = "update Classe set nomClasse='"+nom+"' where id = "+id+"";
        try {
            ste=con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("modif avec succée ! ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Classe> trieClasse(String o ) 
    {
        String requete = "SELECT * FROM Classe order by "+o;
        PreparedStatement pst;
            List<Classe> list = new ArrayList<>();

        try {
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
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
       return list;
          
    }
    
    public ObservableList<Classe> rechercheClasse(String column, String o) {
        ObservableList<Classe> list = FXCollections.observableArrayList();
        String requete;
      
            requete = "select * from classe where "+column+" like '%"+o+"%'";
     
        try {
            ste = con.createStatement();
        ResultSet rs = ste.executeQuery(requete);
        while(rs.next())
            {
                
            Classe c = new Classe();
            c.setIdClasse(rs.getInt(1));
            c.setNomClasse(rs.getString(2));
            
            list.add(c);
            }
    }
    catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list ;
}
    public List<String> getListClasse2(){
    List<String> list = new ArrayList<>();
    String requete = "select nomclasse from classe";
     
            
       
        try {
            ste = con.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while(rs.next())
            {
                
            
            list.add(rs.getString(1));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }

    return list;
    }
    public int getIdClasse(String nom){
    ObservableList<Classe> list = FXCollections.observableArrayList();
    
    String requete = "select * from classe where nomclasse='"+nom+"';" ;
     
            
       
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

    return list.get(0).getIdClasse();
    }
    public void setEnfantVide(int id)
{
String requete = "UPDATE `enfant` SET id=1  WHERE id= " +id;
PreparedStatement st;
        try {
           
            st = con.prepareStatement(requete);
       
        st.executeUpdate();
        System.out.println("update done!");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}

