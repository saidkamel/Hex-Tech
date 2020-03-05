/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button gJardins;
    @FXML
    private Button gReclamation;
    private Button gCommentaire;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
      
        
    }    
    
    @FXML
    public void gereJardins(ActionEvent event){
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("JardinEnfantsAjout.fxml"));
            Parent root = loader.load();
            JardinEnfantsAjoutController dcc=loader.getController();
            gJardins.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gereCommentaire(ActionEvent event){
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Commentaire.fxml"));
            Parent root = loader.load();
            CommentaireController dc=loader.getController();
            gCommentaire.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void gereReclamation(ActionEvent event){
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("reclamation.fxml"));
            Parent root = loader.load();
            ReclamationController dca=loader.getController();
            gReclamation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gototBack(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Back.fxml"));
            Parent root = loader.load();
            gReclamation.getScene().setRoot(root);
    }
}
