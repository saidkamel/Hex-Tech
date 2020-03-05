/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Entities.User;
import Utils.DataBase;
import Utils.Notification;
import java.awt.AWTException;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author aymen
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button btn_login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private Connection con = DataBase.getInstance().getConnection();
            //DataBase.getInstance().getConnection();
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
                if (logIn().equals("Success")) {
                    
                    
                User usersession = Session.get();
                if (usersession.getRole().equals("admin")) {
                        
                    try {
                        Notification.main("Welcome Admin !  ", " ");
                    } catch (AWTException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
      
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Back.fxml")));
                    stage.setScene(scene);
                    stage.show();
                }
                else if (usersession.getRole().equals("parent")) {
                       try {
                        Notification.main("Welcome Parent !  ", " ");
                    } catch (AWTException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FrontParent.fxml")));
                    stage.setScene(scene);
                    stage.show();
                }
                else {
                        try {
                        Notification.main("Welcome Employe !  ", " ");
                    } catch (AWTException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FrontEmploye.fxml")));
                    stage.setScene(scene);
                    stage.show();
                }

            

        }
    }
    
    private String logIn() throws SQLException {
        String status = "Success";
        String email = username.getText();
        String password = this.password.getText();
        //System.out.println(password);
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("erreur");
        } else {
            
            System.out.println("before");
            String requete = "SELECT * FROM user WHERE (email='" + email + "' and Password='" + password + "')";

           
                PreparedStatement ps = con.prepareStatement(requete);

                ResultSet rs = ps.executeQuery();
                
                if (!rs.next()) {
                
                    System.out.println("after");

                 
                    System.out.println( "Error");
                } else {
                          System.out.println( "Login");
                          System.out.println(rs.getInt(1));
                          Session.start(rs.getInt(1));
                }
            

        }

        return status;
    }
    
}
