/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class HomepageController implements Initializable {

    @FXML
    private Label titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoAffClasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageClasse.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }

    @FXML
    private void gotoAffActivite(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageActivite.fxml"));
        Parent root = loader.load();
        titre.getScene().setRoot(root);
    }
    
}
