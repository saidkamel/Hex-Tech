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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import Utils.controleSaisie;
import Entities.Employe;
import Service.EmployeService;
import Utils.MailVerification;

/**
 * FXML Controller class
 *
 * @author alaka
 */
public class ModifierEmployeController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button btn;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfSalaire;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAbsence;
    @FXML
    private Label Lbid;
    @FXML
    private TextField tfCin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficheEmploye.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
    }

    @FXML
    private void ModiferEmploye(ActionEvent event) throws IOException {
        EmployeService es = new EmployeService();
         controleSaisie cs = new controleSaisie();
        if (!MailVerification.validate(tfEmail.getText())){
            
                    JOptionPane.showMessageDialog(null,"mail incorrect");
                          
        
         }
        else if(!cs.testnomprenom(tfNom.getText()) )
        {
        JOptionPane.showMessageDialog(null,"nom incorrect");
                         
        } else if(!cs.testnomprenom(tfPrenom.getText()) )
        {
        JOptionPane.showMessageDialog(null,"prenom incorrect");
                         
        }else if(Integer.parseInt(tfSalaire.getText()) < 0 &&  !cs.num(tfSalaire.getText()) )
        {
        JOptionPane.showMessageDialog(null,"salaire incorrect");
                         
        }
        else if( !cs.Cin(tfCin.getText()) )
        {
        JOptionPane.showMessageDialog(null,"cin incorrect");
                         
        }
        else if(Integer.parseInt(tfAbsence.getText()) < 0 &&  !cs.num(tfAbsence.getText()) )
        {
        JOptionPane.showMessageDialog(null,"absence incorrect");
                         
        }else {
        es.modifierEmploye(Integer.parseInt(Lbid.getText()),tfNom.getText(),tfPrenom.getText(),
                Integer.parseInt(tfCin.getText()),tfTitre.getText(),
                Integer.parseInt(tfSalaire.getText()),tfEmail.getText(),Integer.parseInt(tfAbsence.getText()));
        JOptionPane.showMessageDialog(null,"Employe Modife");
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficheEmploye.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
        }     
        
    }

    void setLbid(int id) {
       this.Lbid.setText(String.valueOf(id));   
    }

    void setNom(String nom) {
    this.tfNom.setText(nom);    
    
    }

    void setPrenom(String prenom) {
    this.tfPrenom.setText(prenom);    
        }
     void setCin(int cin) {
        this.tfCin.setText(String.valueOf(cin));  
         }

    void setTitre(String titre) {
    this.tfTitre.setText(titre);    
         }

    void setSalaire(int salaire) {
        this.tfSalaire.setText(String.valueOf(salaire));  
         }

    void setEmail(String email) {
       this.tfEmail.setText(email);    
     }

    void setAbsence(int absence) {
      this.tfAbsence.setText(String.valueOf(absence));  
         }
    
}