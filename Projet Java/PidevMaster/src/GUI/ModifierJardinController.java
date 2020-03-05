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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.jardinService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierJardinController implements Initializable {

    @FXML
    private Label Tid;
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tcap;
    @FXML
    private TextField Tnum;
    @FXML
    private TextField Tlocalisation;
    @FXML
    private Button val;
    @FXML
    private TextField Tmail;
    @FXML
    private TextField Tactivite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) throws IOException {
        jardinService js = new jardinService();
        js.modifierJardins(Integer.parseInt(Tid.getText()), Tnom.getText(), Integer.parseInt(Tcap.getText()), Integer.parseInt(Tnum.getText()), Tmail.getText(), Tactivite.getText(), Tlocalisation.getText());
    
    FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("JardinEnfantsAjout.fxml"));
        Parent root= loader.load();
        Tnum.getScene().setRoot(root);
    }

    void setTnom(String nom) {
        this.Tnom.setText(nom);
    
    }

    void setTcapacite(String capacite) {
         this.Tcap.setText(String.valueOf(capacite));
    }

    void setTnum(String num) {
         this.Tnum.setText(String.valueOf(num));
    }

    void setTmail(String mail) {
        this.Tmail.setText(mail);
    }

    void setTactivite(String activite) {
        this.Tactivite.setText(activite);
    }

    void setTlocalisation(String localisation) {
        this.Tlocalisation.setText(localisation);
    }

    void setTid(int id) {
    this.Tid.setText(String.valueOf(id));
    
    }

   
    
}
