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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import Utils.controleSaisie;
import Entities.Employe;
import Entities.User;
import Service.EmployeService;
import Service.User_service;
import Utils.MailVerification;
import Utils.MailEmploye;

/**
 * FXML Controller class
 *
 * @author alaka
 */
public class AjoutEmployeController implements Initializable {

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
    private TextField tfCin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void ajouterUnePersone(ActionEvent event) throws Exception {
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
               //Integer.parseInt(tfSalaire.getText()) < 0 ||          
        }else if(  !cs.num(tfSalaire.getText()) || Integer.parseInt(tfSalaire.getText()) < 0 )
        {
        JOptionPane.showMessageDialog(null,"salaire incorrect");
                         
        }
        else if( !cs.Cin(tfCin.getText()) )
        {
        JOptionPane.showMessageDialog(null,"cin incorrect");
                         
        }
        else if(  !cs.num(tfAbsence.getText()) || Integer.parseInt(tfAbsence.getText()) < 0)
        {
        JOptionPane.showMessageDialog(null,"absence incorrect");
                         
        }

        else { 
            
            Employe E = new Employe(tfNom.getText(),tfPrenom.getText(),Integer.parseInt(tfCin.getText()),tfTitre.getText(),
                Integer.parseInt(tfSalaire.getText()),tfEmail.getText(),Integer.parseInt(tfAbsence.getText()));
            es.ajouterEmploye(E);
            User_service us = new User_service();
            us.ajouterUser(new User(tfEmail.getText(),tfCin.getText(),"employe"));
            MailEmploye.sendMail(tfEmail.getText(), "Votre email est "+tfEmail.getText()+ " et votre mot de passe est: "+Integer.parseInt(tfCin.getText()));
            JOptionPane.showMessageDialog(null,"Employe ajouté");
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficheEmploye.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
            
            
            

        
        } 
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficheEmploye.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
    }
    
}
