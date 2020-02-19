/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entitiy.evenement;
import Services.EvenementService;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class AjouterEvenementController implements Initializable {

    
    @FXML
    private TextField TfNom;
    @FXML
    private TextField TfType;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField TfDescription;
    @FXML
    private TextField TfNbr;
    @FXML
    private Button btnAjouter;
    @FXML
    private AnchorPane btn1;
    @FXML
    private Button btnDelete;
     @FXML
    private TableColumn<evenement,String> TbId;
    @FXML
    private TableColumn<evenement, String> TbNom;
    @FXML
    private TableColumn<evenement, String> TbType;
    @FXML
    private TableColumn<evenement, Date> TbDateDebut;
    @FXML
    private TableColumn<evenement, Date> TbDateFin;
    @FXML
    private TableColumn<evenement, String> TbDescription;
    @FXML
    private TableColumn<evenement,String> TbNp;
    @FXML
    private TableView<evenement> table;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAjoutP;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              LoadData();
              
    }    
    
    @FXML
    private void AjouterEvent(ActionEvent event) throws IOException{

        EvenementService es = new EvenementService();
        evenement e = new evenement();
        LocalDate ldd=DateDebut.getValue();
        LocalDate ldf=DateFin.getValue();
        Date dateD = Date.valueOf(ldd);
        Date dateF = Date.valueOf(ldf);
        
        e.setNom(TfNom.getText());
        e.setType(TfType.getText());
        e.setDate_Debut(dateD);
        e.setDate_Fin(dateF);
        e.setDescription(TfDescription.getText());
        e.setNbr_participant(Integer.parseInt(TfNbr.getText()));

      
        es.AjouterEvenement(e);
        System.out.println("Evenement ajouté !");
        
       FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjouterEvenement.fxml"));
         Parent root=loader.load();
         TfType.getScene().setRoot(root);
          
   
     }
       @FXML
    private void DeleteEvent(ActionEvent event) throws IOException{
        evenement e = table.getSelectionModel().getSelectedItem();
        EvenementService es =new EvenementService();
        es.SupprimerEvenement(e);
        table.getItems().removeAll();
          LoadData();

    }
       
    public void LoadData(){
     EvenementService es = new EvenementService();
     table.setItems(es.getListEvenements());
     TbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
     TbType.setCellValueFactory(new PropertyValueFactory<>("Type"));
     TbDateDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
     TbDateFin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
     TbDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
     TbNp.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));    
        
    }
       
    private void Rechercher(ActionEvent event) throws IOException{

        EvenementService es = new EvenementService();
        evenement e = new evenement();
        LocalDate ldd=DateDebut.getValue();
        LocalDate ldf=DateFin.getValue();
        Date dateD = Date.valueOf(ldd);
        Date dateF = Date.valueOf(ldf);
        
        e.setNom(TfNom.getText());
        e.setType(TfType.getText());
        e.setDate_Debut(dateD);
        e.setDate_Fin(dateF);
        e.setDescription(TfDescription.getText());
        e.setNbr_participant(Integer.parseInt(TfNbr.getText()));

      
        es.rechercherEvenement("mondher", "event1");
        System.out.println("recherche validé !");
        
       FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjouterEvenement.fxml"));
         Parent root=loader.load();
         TfType.getScene().setRoot(root);
          
          
        DataEvenementController dec=loader.getController();
        dec.setLbNom(TfNom.getText());
        dec.setLbType(TfType.getText());
        dec.setLbDescription(TfDescription.getText());
        dec.setLbDateDebut(DateDebut.toString());
        dec.setLbDateFin(DateFin.toString());
        dec.setLbNbrParticipant(TfNbr.getText());
     }

        @FXML
    private void UpdateEvent(ActionEvent event) throws IOException{
              FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/UpdateEvent.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
            UpdateEventController uec = loader.getController();
            uec.setid(table.getSelectionModel().getSelectedItem().getId());
            uec.setnom(table.getSelectionModel().getSelectedItem().getNom());
            uec.settype(table.getSelectionModel().getSelectedItem().getType());
            uec.setdesc(table.getSelectionModel().getSelectedItem().getDescription());
            uec.setdateD(table.getSelectionModel().getSelectedItem().getDate_Debut());
            uec.setdateF(table.getSelectionModel().getSelectedItem().getDate_Fin());
            uec.setnbr(table.getSelectionModel().getSelectedItem().getNbr_participant());
        
       
         

    }
       @FXML
        private void AjouterParticipant(ActionEvent event) throws IOException{
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/AjoutParticipant.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
         }
}