/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Activite;
import Entities.ClasseActivite;
import Entities.ClasseActivite;
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
public class ServiceClassActivite implements IClasseActivite{

    private Connection con;
    private Statement ste;

    public ServiceClassActivite() {
        con = DataBase.getInstance().getConnection();
    }

    public void affecterActivite(ClasseActivite ca) {
        String requete = "insert into classeactivite (idClasse,idActivite) values ('"+ca.getIdClasse()+"','"+ca.getIdActivite()+"')";
        try {
            ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Affection avec sucée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClassActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void viderClasse(int idclass) throws SQLException
    {String requete = "Delete from classeactivite where idclasse = "+idclass;
    
            ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Supp avec sucée");
    }
    
    
    @Override
    public ObservableList<Activite> getListClasse(int classe){
    ObservableList<Activite> list = FXCollections.observableArrayList();

    String requete =  "select a.id,a.nomactivite,a.description, a.type FROM classe c join activite a JOIN classeactivite ca ON ca.IdClasse=c.id and ca.IdActivite=a.Id and c.id="+classe;
     
            
       
        try {
            ste = con.createStatement();
            ResultSet rs=ste.executeQuery(requete);
            while(rs.next())
            {
                
                       Activite a = new Activite();
                a.setIdActivite(rs.getInt(1));
                a.setNomActivite(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setType(rs.getString(4));
                list.add(a);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClassActivite.class.getName()).log(Level.SEVERE, null, ex);
        }

    return list;
    }
public List<String> getMail(int idclass) throws SQLException
{ List<String> mail= new ArrayList<>();
String requete = "select p.email from parent p,enfant e, classe c where p.id=e.idparent and c.id = e.idclasse and c.id= "+idclass;
        PreparedStatement pst;
         pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            mail.add(rs.getString(1));
           
            }

    
    
return mail;
}

    public void remove(int idclasse, int idactivite) {
        try {
            String requete = "Delete from classeactivite where idclasse = '"+idclasse+"' and idactivite = '"+idactivite+"'";
            
            ste = con.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Supp avec sucée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClassActivite.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
