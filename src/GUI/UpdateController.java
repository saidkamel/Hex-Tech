/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ParticipantService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class UpdateController implements Initializable {

    @FXML
    private TextField TfNom;
    @FXML
    private TextField TfPrenom;
    @FXML
    private TextField TfMdp;
    @FXML
    private Button btnConfirmer;
    @FXML
    private Label lbid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       lbid.setVisible(false);
    }    

    @FXML
    private void ConfirmerModification(ActionEvent event) throws IOException {
        
            ParticipantService ps= new ParticipantService();
            ps.modifierParticipant(Integer.parseInt(lbid.getText()), TfNom.getText(), TfPrenom.getText(), TfMdp.getText());
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjoutParticipant.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
    }

    void setNom(String nom_p) {
    this.TfNom.setText(nom_p);    
    
    
    }

    void setPrenom(String nom_p) {
    this.TfPrenom.setText(nom_p);    
         }

    void setmdp(String mdp) {
    this.TfMdp.setText(mdp);    
        
      }

    void setid(int id_p) {
    this.lbid.setText(String.valueOf(id_p));   
    
    }
    
}
