/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Employe;
import Entities.Session;
import Entities.User;
import Entities.addParent;
import Service.EmployeService;
import Service.ServiceParent;
import Utils.Notification;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class FrontParentController implements Initializable {

    @FXML
    private Label nomprenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceParent es = new ServiceParent();
        User usersession = Session.get();
        System.out.println(usersession.getMail());
        addParent e = es.getParent(usersession.getMail());
        nomprenom.setText("Mr/Mrs "+e.getNom()+" "+e.getPrenom());
        
       
    }    

    @FXML
    private void GotoFrontEnfant(ActionEvent event) throws IOException {
        ServiceParent es = new ServiceParent();
        User usersession = Session.get();
        System.out.println(usersession.getMail());
        addParent e = es.getParent(usersession.getMail());
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Frontenfant.fxml"));
        Parent root = loader.load();
        FrontEnfantController mec = loader.getController();
        mec.setLbid(e.getId());
        nomprenom.getScene().setRoot(root);
    }

    @FXML
    private void GotoCommentaire(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Commentaire.fxml"));
        Parent root = loader.load();
        nomprenom.getScene().setRoot(root);
    }

    @FXML
    private void GotoFrontEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Frontevent.fxml"));
        Parent root = loader.load();
        nomprenom.getScene().setRoot(root);
    }

    @FXML
    private void LogOut(MouseEvent event) throws IOException, Exception {
        System.out.println("se déconnecter");
        Session.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        nomprenom.getScene().setRoot(root);
    }
    
}
