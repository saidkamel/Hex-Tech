/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Service.ServiceActivite;
import Utils.Notification;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class AjouterActiviteController implements Initializable {

    @FXML
    private TextField nomfield;
    @FXML
    private TextField descfield;
    @FXML
    private Button ajouterac;
    @FXML
    private ComboBox<String> typefield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadCombo();
    }    
     public void LoadCombo()
    {
    String a="Dessin";
              String b="Sport";
              String c="Culture";
              String d="Relaxation";
              ArrayList l = new ArrayList();
              l.add(a);
              l.add(b);
              l.add(c);
              l.add(d);
              typefield.getItems().setAll(l);
    }
    @FXML
    private void AjouterActivite(ActionEvent event) throws IOException, AWTException {
        
        
        
       ServiceActivite  sa = new ServiceActivite();
        sa.ajouterActivite(new Activite(nomfield.getText(),descfield.getText(),typefield.getValue()));
        Notification.main("Ajout! ", "Activite ajoute avec succé");
        //JOptionPane.showMessageDialog(null,"Activité ajoute");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageActivite.fxml"));
        Parent root = loader.load();
        nomfield.getScene().setRoot(root);
    }

    @FXML
    private void Cancel(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageActivite.fxml"));
        Parent root = loader.load();
        nomfield.getScene().setRoot(root);
    }
    
}
