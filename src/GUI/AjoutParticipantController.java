/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entitiy.participant;
import Services.EvenementService;
import Services.ParticipantService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class AjoutParticipantController implements Initializable {

    @FXML
    private TextField TfNom;
    @FXML
    private TextField TfPrenom;
    @FXML
    private PasswordField TfPass;
    @FXML
    private Button bntAjouter;
    @FXML
    private TableView<participant> tableP;
   @FXML
    private TableColumn<participant,Integer> TbId;
    @FXML
    private TableColumn<participant, String> TbNom;
    @FXML
    private TableColumn<participant, String> TbPrenom;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEvenement;
    @FXML
    private Button btnModifier;
    @FXML
    private ComboBox<String> btnCombo;
    

    /** 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData2();
        EvenementService es = new EvenementService();
        try {
            btnCombo.getItems().setAll(es.AffecterParticipants());
        } catch (SQLException ex) {
            Logger.getLogger(AjoutParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @FXML
    private void AjouterParticipant(ActionEvent event) throws IOException, SQLException{

        ParticipantService es = new ParticipantService();
        participant p = new participant();
        EvenementService ps = new EvenementService();
        p.setNom_p(TfNom.getText());
        p.setPrenom_p(TfPrenom.getText());
        p.setMdp(TfPass.getText());
        
        ps.ParticipantsNumber(btnCombo.getSelectionModel().getSelectedItem());
        
        es.AjouterParticipant(p);
        
        System.out.println("Participant ajouté !");
        
      
       FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjoutParticipant.fxml"));
       Parent root=loader.load();
       TfNom.getScene().setRoot(root);
     
        
}

    @FXML
 public void LoadData2(){
     ParticipantService ps = new ParticipantService();
     tableP.setItems(ps.getListParticipants());
     TbId.setCellValueFactory(new PropertyValueFactory<>("Id_p"));
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom_p"));
     TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom_p"));
     
     tableP.getSelectionModel().getSelectedItem();
        
    }
    
    @FXML
    private void DeleteParticipant(ActionEvent event) throws IOException{
        participant p = tableP.getSelectionModel().getSelectedItem();
        ParticipantService ps =new ParticipantService();
        ps.SupprimerParticipant(p);
        tableP.getItems().removeAll();
          LoadData2();

    }
    
    @FXML
        private void RetourEvent(ActionEvent event) throws IOException{
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjouterEvenement.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
            
             }

    @FXML
    private void modifierParticipants(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Update.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
            UpdateController uc = loader.getController();
            uc.setid(tableP.getSelectionModel().getSelectedItem().getId_p());
            uc.setNom(tableP.getSelectionModel().getSelectedItem().getNom_p());
            uc.setPrenom(tableP.getSelectionModel().getSelectedItem().getPrenom_p());
            uc.setmdp(tableP.getSelectionModel().getSelectedItem().getMdp());   
       
    }
        
        
   
}