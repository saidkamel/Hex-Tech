/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enfant;
import Service.ServiceEnfant;
import Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import IService.IServiceEnfant;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class UpdateEnfantController implements Initializable {

    @FXML
    private AnchorPane btn1;
    @FXML
    private TextField TfNom;
    @FXML
    private Button bntConfirmer;
    @FXML
    private Label lb;
    @FXML
    private DatePicker DateNaissances;
    @FXML
    private TextField TfPrenom;
    @FXML
    private Button bntRetour;
    @FXML
    private Label lbid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      lb.setVisible(false);
         lbid.setVisible(false);

    }    

    @FXML
    private void Confirmer_Modification(ActionEvent event) throws IOException {
   
                  //String dateNaissance = "2020-07-07";
               LocalDate ldn=DateNaissances.getValue();
                 Date dateN = Date.valueOf(ldn);
              

            ServiceEnfant es= new ServiceEnfant();  
        es.modifierEnfant( Integer.parseInt(lbid.getText()),TfNom.getText(), TfPrenom.getText(),dateN);
                
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
    }   

           @FXML
    private void back(ActionEvent event) throws IOException {
        
       FXMLLoader loader= new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
        Parent root = loader.load();
        TfNom.getScene().setRoot(root);
    }
      
    void setnom(String nom) {
        this.TfNom.setText(nom);
    }

   

    void setDateNaissance(Date dateNaissance) {
        
    }

    void setPrenom(String prenom) {
        this.TfPrenom.setText(prenom);
    }


    
    
      void setid(int id) {
        this.lbid.setText(String.valueOf(id));
    }

    public AnchorPane getBtn1() {
        return btn1;
    }

    public TextField getTfNom() {
        return TfNom;
    }

    public Button getBntConfirmer() {
        return bntConfirmer;
    }

    public Label getLb() {
        return lb;
    }

    

    public TextField getTfPrenom() {
        return TfPrenom;
    }
      
}

