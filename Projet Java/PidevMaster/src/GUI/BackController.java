/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Utils.Notification;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class BackController implements Initializable {

    @FXML
    private Button boutonParent;
    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
    }    

    @FXML
    private void gotoActivite(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageActivite.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoClasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageClasse.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoParent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionParent.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
        
    }

    @FXML
    private void LogOut(ActionEvent event) throws Exception {
        System.out.println("se déconnecter");
        Session.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoJardin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoEvent(ActionEvent event) throws IOException {
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoEmploye(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheEmploye.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoEnfant(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }
    
}
