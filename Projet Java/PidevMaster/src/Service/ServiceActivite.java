/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Activite;
import Entities.Classe;
import Entities.ClasseActivite;
import Entities.ClasseActivite;
import IService.IActivite;
import IService.IClasseActivite;
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
public class ServiceActivite implements IActivite{

    private Connection con;
    private Statement ste;

    public ServiceActivite() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouterActivite(Activite A) {
        String requete = "insert into activite (nomactivite,description,type) values('" + A.getNomActivite() + "','" + A.getDescription() + "','" + A.getType() + "')";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("activite ajouté avec sucée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Activite> getListActivite() {
        ObservableList<Activite> list = FXCollections.observableArrayList();
        String requete = "select * from activite";

        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {

                Activite a = new Activite();
                a.setIdActivite(rs.getInt(1));
                a.setNomActivite(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setType(rs.getString(4));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public List<Activite> getListActivite2() {
        List<Activite> list = new ArrayList<>();
        String requete = "select * from activite";

        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {

                Activite a = new Activite();
                a.setIdActivite(rs.getInt(1));
                a.setNomActivite(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setType(rs.getString(4));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public void supprimerActivite(int id) {

        String requete = "delete from activite where id = '" + id + "'";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("supp avec succée ! ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierActivite(int id, String nom, String desc, String type) {
        String requete = "update activite set nomactivite='" + nom + "',description='" + desc + "',type='" + type + "' where id = " + id + "";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("modif avec succée ! ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Activite> trieActivite(String o) {
        String requete = "SELECT * FROM Activite order by " + o;
        PreparedStatement pst;
        List<Activite> list = new ArrayList<>();

        try {
            pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Activite a = new Activite();
                a.setIdActivite(rs.getInt(1));
                a.setNomActivite(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setType(rs.getString(4));
                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public ObservableList<Activite> rechercheActivite(String column, String o) {
        ObservableList<Activite> list = FXCollections.observableArrayList();
        String requete;
      
            requete = "select * from activite where "+column+" like '%"+o+"%'";
     
        try {
            ste = con.createStatement();
        ResultSet rs = ste.executeQuery(requete);
        while (rs.next()) {

            Activite a = new Activite();
            a.setIdActivite(rs.getInt(1));
            a.setNomActivite(rs.getString(2));
            a.setDescription(rs.getString(3));
            a.setType(rs.getString(4));
            list.add(a);
        }
    }
    catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
    }

    return list ;
}
    public List<String> getListActivite3(){
        List<String> list = new ArrayList<>();
        String requete="select nomactivite from activite where (select count(*) from classeactivite where activite.id=classeactivite.idactivite) = 0";
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

    public int getIdActivite(String value) {
       
    ObservableList<Activite> list = FXCollections.observableArrayList();
    
    String requete = "select * from activite where nomactivite='"+value+"';" ;
     
            
       
        try {
            ste = con.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while(rs.next())
            {
                
            Activite c = new Activite();
            
            c.setIdActivite(rs.getInt(1));
            c.setNomActivite(rs.getString(2));
            
            list.add(c);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClasse.class.getName()).log(Level.SEVERE, null, ex);
        }

    return list.get(0).getIdActivite();
    
 }

}
