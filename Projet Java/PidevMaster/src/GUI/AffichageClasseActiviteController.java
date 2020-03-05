/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Entities.ClasseActivite;
import Service.ServiceActivite;
import Service.ServiceClassActivite;
import Utils.Notification;
import Utils.mail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class AffichageClasseActiviteController implements Initializable {

    @FXML
    private Label lbNom;
    @FXML
    private Label lbid;
    @FXML
    private TableView<Activite> tableactivite;
    @FXML
    private TableColumn<Activite, String> nomactivite;
    @FXML
    private TableColumn<Activite, String> descactivite;
    @FXML
    private TableColumn<Activite, String> typeactivite;
    @FXML
    private ComboBox<String> comboactivite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // System.out.println("hello
       LoadCombo();
        System.out.println(lbid.getText());
           /*ServiceClassActivite sca1 = new ServiceClassActivite();
            ObservableList<Activite> liste = FXCollections.observableArrayList();
            liste= sca1.getListClasse(Integer.parseInt(lbid.getText()));
   
        remplirTable(liste);*/
    }    
    private void remplirTable(ObservableList<Activite> liste ){
    nomactivite.setCellValueFactory(new PropertyValueFactory<>("NomActivite"));
    descactivite.setCellValueFactory(new PropertyValueFactory<>("Description"));
    typeactivite.setCellValueFactory(new PropertyValueFactory<>("Type"));
    tableactivite.setItems(liste);
    }
    @FXML
    private void Supprimer(ActionEvent event) {
        ServiceClassActivite sc = new ServiceClassActivite();
        ServiceActivite sa = new ServiceActivite();
        int idactivite = sa.getIdActivite(tableactivite.getSelectionModel().getSelectedItem().getNomActivite());
        sc.remove(Integer.parseInt(lbid.getText()), idactivite);
          LoadCombo();
    }

    void setId(int idClasse) {
        
        this.lbid.setText(String.valueOf(idClasse));
    
    }

    void setNom(String nomClasse) {
        this.lbNom.setText(nomClasse);
                }

    @FXML
    private void afficher(ActionEvent event) {
        ServiceClassActivite sca1 = new ServiceClassActivite();
            ObservableList<Activite> liste = FXCollections.observableArrayList();
            liste= sca1.getListClasse(Integer.parseInt(lbid.getText()));
   
        remplirTable(liste);
       
    }

    @FXML
    private void mailing(ActionEvent event) throws SQLException, Exception {
        ServiceClassActivite sca = new ServiceClassActivite();
        ObservableList<Activite> liste = FXCollections.observableArrayList();
            liste= sca.getListClasse(Integer.parseInt(lbid.getText()));
            List<String> listmail =sca.getMail(Integer.parseInt(lbid.getText()));
            String recipient="";
            for(int i =0 ;i<sca.getMail(Integer.parseInt(lbid.getText())).size(); i++)
            {
            recipient= listmail.get(i);
            mail.sendMail(recipient,"Voila votre liste d activite: \n"+liste);
        }
        Notification.main("Mail! ", "Mail Envoye avec succé! ");
            
        /*while (mail.hasNext()){
        
        
        }*/
        
        
        
    }

    @FXML
    private void gotoAfficherClasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageClasse.fxml"));
        Parent root;
        root = loader.load();
        lbid.getScene().setRoot(root);
        
    }
public void LoadCombo()
    {
        ServiceActivite sc = new ServiceActivite();
        List<String> list =  sc.getListActivite3();
        comboactivite.getItems().setAll(list);
               
    }
    @FXML
    private void affecteractivite(ActionEvent event) {
        ServiceClassActivite sc = new ServiceClassActivite();
        ServiceActivite sa = new ServiceActivite();
        ClasseActivite ca = new ClasseActivite(Integer.parseInt(lbid.getText()), sa.getIdActivite(comboactivite.getValue()));
        sc.affecterActivite(ca);
        
        
        
        LoadCombo();
    }
    
}
