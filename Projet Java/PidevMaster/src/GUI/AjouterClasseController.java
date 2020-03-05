/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Classe;
import Service.ServiceClasse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class AjouterClasseController implements Initializable {

    @FXML
    private TextField NomClasse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterClasse(ActionEvent event) throws IOException {
        ServiceClasse sc = new ServiceClasse();
        sc.ajouterClasse(new Classe(NomClasse.getText()));
        JOptionPane.showMessageDialog(null,"Classe ajoute");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageClasse.fxml"));
        Parent root = loader.load();
        NomClasse.getScene().setRoot(root);
    }

    @FXML
    private void AfficherClasse(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageClasse.fxml"));
        Parent root = loader.load();
        NomClasse.getScene().setRoot(root);
        
    }
    
}
