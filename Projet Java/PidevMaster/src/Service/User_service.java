/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Activite;
import Entities.User;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sa3do
 */
public class User_service {
    private Connection c = DataBase.getInstance().getConnection();
    private Statement ste;
    public void ajouterUser(User A) {
        String requete = "insert into user (email,password,role) values('" + A.getMail()+ "','" + A.getPass()+ "','" + A.getRole()+ "')";
        try {
            ste = c.createStatement();
            ste.executeUpdate(requete);
            System.out.println("user ajouté avec sucée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceActivite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public User get_User(int id) {
        String requete = "SELECT * FROM `user` WHERE (id =" + String.valueOf(id) + ")";

        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User us = new User(rs.getString("email"),rs.getString("Password"), rs.getString("role"));
                return us;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public String get_User2(int id )
    {
    String requete = "SELECT email FROM `user` WHERE (id =" + String.valueOf(id) + ")";
    String mail="";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
             mail= rs.getString(1);
            return mail;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
   return mail;
    }
    
}
