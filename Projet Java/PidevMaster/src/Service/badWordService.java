/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Utils.badwords;
import Utils.DataBase;
import Service.reclamationService;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */


public class badWordService {
             Connection cn =DataBase.getInstance().getConnection();
             


    
    public void ajouterBadWords (badwords b ) {
        
        

    String sql = "INSERT INTO `badWords` ( `mot`)VALUES ('"+b.getWords()+ "');";

  try {
            Statement st = cn.createStatement();
            
            
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(reclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
