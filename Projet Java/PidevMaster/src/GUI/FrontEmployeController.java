/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Employe;
import Entities.Session;
import Entities.User;
import Service.EmployeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class FrontEmployeController implements Initializable {

    @FXML
    private ImageView imagelogout;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbprenom;
    @FXML
    private Label lbemail;
    @FXML
    private Label lbcin;
    @FXML
    private Label lbtitre;
    @FXML
    private Label lbsalaire;
    @FXML
    private Label lbabsence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmployeService es = new EmployeService();
        User usersession = Session.get();
        System.out.println(usersession.getMail());
        Employe e = es.getEmploye(usersession.getMail());
        lbnom.setText(e.getNom());
        lbprenom.setText(e.getPrenom());
        lbtitre.setText(e.getTitre());
        lbabsence.setText(String.valueOf(e.getAbsence()));
        lbcin.setText(String.valueOf(e.getCin()));
        lbsalaire.setText(String.valueOf(e.getSalaire()));
        lbemail.setText(e.getEmail());
     
    }    

    @FXML
    private void LogOut(MouseEvent event) throws Exception {
        System.out.println("se déconnecter");
        Session.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        lbnom.getScene().setRoot(root);
    }
    
}
