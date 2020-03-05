/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.evenement;
import Entities.participant;
import Service.EvenementService;
import Service.ParticipantService;
import Utils.mail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

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
    private ComboBox<String> btnCombo;
    @FXML
    private TableColumn<participant,String> TbMail;
    @FXML
    private TextField TfMail;

    

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
    private void AjouterParticipant(ActionEvent event) throws IOException, SQLException, Exception{

        ParticipantService es = new ParticipantService();
        participant p = new participant();
        EvenementService ps = new EvenementService();
        p.setNom_p(TfNom.getText());
        p.setPrenom_p(TfPrenom.getText());
        p.setMdp(TfPass.getText());
        p.setMail(TfMail.getText());
        String Body = "Nom: "+TfNom.getText()+" ,Prenom: "+TfPrenom.getText()+" ,Mot de Passe: "+TfPass.getText();
        ps.ParticipantsNumber(btnCombo.getSelectionModel().getSelectedItem());
        //mail.sendMail(TfMail.getText(), Body);
        es.AjouterParticipant(p);
        mail.sendMail(TfMail.getText(),"Nom: "+TfNom.getText()+", Prenom :"+TfPrenom.getText()+", Password: "+TfPass.getText());
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
     TbMail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
     
        tableP.getSelectionModel().getSelectedItem();
        TbNom.setCellFactory(TextFieldTableCell.forTableColumn());
        TbNom.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom_p(e.getNewValue());
        });
        TbPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
        TbPrenom.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom_p(e.getNewValue());
            
        });
        TbMail.setCellFactory(TextFieldTableCell.forTableColumn());
        TbMail.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMail(e.getNewValue());
            
        });
   
     tableP.setEditable(true);
    }
    
    
    @FXML
    private void DeleteParticipant(ActionEvent event) throws IOException, SQLException{
        participant p = tableP.getSelectionModel().getSelectedItem();
        ParticipantService ps =new ParticipantService();
        EvenementService es = new EvenementService();
        evenement e = new evenement();
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

    private void modifierParticipants(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/Update.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
            UpdateController uc = loader.getController();
            uc.setid(tableP.getSelectionModel().getSelectedItem().getId_p());
            uc.setNom(tableP.getSelectionModel().getSelectedItem().getNom_p());
            uc.setPrenom(tableP.getSelectionModel().getSelectedItem().getPrenom_p());
            uc.setmdp(tableP.getSelectionModel().getSelectedItem().getMdp());
            uc.setmdp(tableP.getSelectionModel().getSelectedItem().getMail());
       
    }
     private void edittable(){
      
        TbNom.setCellFactory(TextFieldTableCell.forTableColumn());
        TbNom.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom_p(e.getNewValue());
        });
        TbPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
        TbPrenom.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom_p(e.getNewValue());
            
        });
   
     tableP.setEditable(true);
    }
@FXML
private void addButtonToTable() {
        TableColumn<participant, Void> colBtn = new TableColumn("Edit");

        Callback<TableColumn<participant, Void>, TableCell<participant, Void>> cellFactory = (final TableColumn<participant, Void> param) -> {
            final TableCell<participant, Void> cell = new TableCell<participant, Void>() {
                
                private final Button btn = new Button("Update");
                
                {
                    btn.setOnAction((ActionEvent event) -> {
                        participant data = getTableView().getItems().get(getIndex());
                        ParticipantService sa = new ParticipantService();
                        sa.modifierParticipant(data.getId_p(), data.getNom_p(), data.getPrenom_p(),data.getMdp(),data.getMail());
                        System.out.println("selectedData: " + data);
                    });
                }
                
                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        };

        colBtn.setCellFactory(cellFactory);

       tableP.getColumns().add(colBtn);

    }
        
        
   
}