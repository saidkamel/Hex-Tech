/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mondh
 */
public class Connexion {
    Connection cnx;
    String url="jdbc:mysql://localhost:3306/evenement";
    String login="root";
    String mdp="";
    static Connexion mycnx;

    private Connexion() {
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.print("Connexion etablie !");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getCnx(){
        return cnx;
    }
    
    public static Connexion getInstance(){
        if(mycnx==null){
            mycnx=new Connexion();
        }
        return mycnx;
    }
    
    
}
