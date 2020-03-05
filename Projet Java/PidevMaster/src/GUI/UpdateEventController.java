/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class UpdateEventController implements Initializable {

    @FXML
    private AnchorPane btn1;
    @FXML
    private TextField TfNom;
    @FXML
    private ComboBox<String> TfType;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField TfDescription;
    @FXML
    private TextField TfNbr;
    @FXML
    private Button bntConfirmer;
    @FXML
    private Label lb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      lb.setVisible(false);
              String a="Sport";
              String b="Loisir";
              String c="Culturel";
              String d="Festival";
              ArrayList l = new ArrayList();
              l.add(a);
              l.add(b);
              l.add(c);
              l.add(d);
              TfType.getItems().setAll(l);
              
    }    

    @FXML
    private void Confirmer_Modification(ActionEvent event) throws IOException {
   
                  
        LocalDate ldd=DateDebut.getValue();
        LocalDate ldf=DateFin.getValue();
        Date dateD = Date.valueOf(ldd);
        Date dateF = Date.valueOf(ldf);


            EvenementService es= new EvenementService();  
        es.modifierEvenement(Integer.parseInt(lb.getText()), TfNom.getText(), TfType.getValue(),dateD,dateF,TfDescription.getText());
                
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjouterEvenement.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
    }   

      
      
    void setnom(String nom) {
        this.TfNom.setText(nom);
    }

    void settype(String type) {
        this.TfType.setValue(type);
    }

    void setdesc(String description) {
        this.TfDescription.setText(description);
    }

    void setdateF(Date date_Fin) {
    }

    void setdateD(Date date_Debut) {
    }

    void setnbr(int nbr_participant) {
        this.TfNbr.setText(String.valueOf(nbr_participant));
    }
    
      void setid(int id) {
        this.lb.setText(String.valueOf(id));
    }
}

