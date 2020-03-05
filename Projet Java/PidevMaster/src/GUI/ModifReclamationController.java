/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Service.jardinService;
import Service.reclamationService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifReclamationController implements Initializable {

    @FXML
    private Label Tnom;
    @FXML
    private Label Tprenom;
    @FXML
    private TextField Tnote;
    @FXML
    private TextField Tmotif;
    @FXML
    private TextField Tdescription;
    @FXML
    private Button Bvalider;
    @FXML
    private Label Tcin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) throws IOException {
          reclamationService rs = new reclamationService();
          Date date = new Date();
        rs.modifierReclamation(Integer.parseInt(Tcin.getText()), Integer.parseInt(Tnote.getText()), Tmotif.getText(), Tdescription.getText());
    
    FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("reclamation.fxml"));
        Parent root= loader.load();
        Tcin.getScene().setRoot(root);
    
        
        
        
    }

    void setcinn(String cinParent) {
         this.Tcin.setText(String.valueOf(cinParent));
    }

    void setnomm(String nomParent) {
        this.Tnom.setText(nomParent);
    }

    void setprenomm(String prenomParent) {
        this.Tprenom.setText(prenomParent);
    }

    void setnotee(String ca) {
         this.Tnote.setText(String.valueOf(ca));
    }

    void setmotiff(String type) {
        this.Tmotif.setText(type);
    }

    void setdescc(String description) {
        this.Tdescription.setText(description);
    }
    
}
