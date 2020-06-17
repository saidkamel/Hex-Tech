/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entitiy.evenement;
import Entitiy.participant;
import Services.EvenementService;
import Services.ParticipantService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class DataEvenementController implements Initializable {

    private Label lbNom;
    private Label lbType;
    private Label lbDateDebut;
    private Label lbDateFin;
    private Label lbDescription;
    private Label lbNbrParticipant;
    @FXML
    private TableColumn<participant,Integer> TbId;
    @FXML
    private TableColumn<participant, String> TbNom;
    @FXML
    private TableColumn<participant, String> TbPrenom;
    
    @FXML
    private TableView<participant> tableP;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
  
   
//  call the find all method
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//     TbId.setCellValueFactory(new PropertyValueFactory<evenement,String>("Id"));
//     TbNom.setCellValueFactory(new PropertyValueFactory<evenement,String>("Nom"));
//     TbType.setCellValueFactory(new PropertyValueFactory<evenement,String>("Type"));
//     TbDateDebut.setCellValueFactory(new PropertyValueFactory<evenement,Date>("Date_Debut"));
//     TbDateFin.setCellValueFactory(new PropertyValueFactory<evenement,Date>("Date_Fin"));
//     TbDescription.setCellValueFactory(new PropertyValueFactory<evenement,String>("Description"));
//     TbNp.setCellValueFactory(new PropertyValueFactory<evenement,String>("N_Participant"));
//       
//     EvenementService es = new EvenementService();
//     evenement e = new evenement();
//        data=  (ObservableList<evenement>) es.getListEvenements();
//        table.setEditable(true);
//        TbId.setCellFactory(TextFieldTableCell.forTableColumn());
//         table.setItems(data);
      LoadData2();
    }    
    
    public void LoadData2(){
     ParticipantService ps = new ParticipantService();
     tableP.setItems(ps.getListParticipants());
     TbId.setCellValueFactory(new PropertyValueFactory<>("Id_p"));
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom_p"));
     TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom_p"));
        
    }

    public void setLbNom(String lbNom) {
        this.lbNom.setText(lbNom);
    }

    public void setLbType(String lbType) {
        this.lbType.setText(lbType);
    }

    public void setLbDateDebut(String lbDateDebut) {
        this.lbDateDebut.setText(lbDateDebut);
    }

    public void setLbDateFin(String lbDateFin) {
        this.lbDateFin.setText(lbDateFin);
    }

    public void setLbDescription(String lbDescription) {
        this.lbDescription.setText(lbDescription);
    }

    public void setLbNbrParticipant(String lbNbrParticipant) {
        this.lbNbrParticipant.setText(lbNbrParticipant);
    }
//    private void DeleteEvent(ActionEvent event) throws IOException{
//
//        EvenementService es = new EvenementService();
//        evenement e = new evenement();
//       LocalDate ldd=TbDateDebut.getValue();
//        LocalDate ldf=TbDateFin.getValue();
//        Date dateD = Date.valueOf(ldd);
//        Date dateF = Date.valueOf(ldf);
//        
//        e.setNom(TbNom.getText());
//        e.setType(TbType.getText());
//        e.setDate_Debut(dateD);
//        e.setDate_Fin(dateF);
//        e.setDescription(TbDescription.getText());
//        e.setNbr_participant(Integer.parseInt(TbNp.getText()));
//      
//        es.SupprimerEvenement(e);
//        System.out.println("Evenement Supprimé !");
//        
//       FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/DataEvenement.fxml"));
//         Parent root=loader.load();
//         lbNom.getScene().setRoot(root);
//          
//     }

    @FXML
    private void Update(ActionEvent event) {
    }
    
    
       @FXML
    private void DeleteParticipant(ActionEvent event) throws IOException{
        participant p = tableP.getSelectionModel().getSelectedItem();
        ParticipantService ps =new ParticipantService();
        ps.SupprimerParticipant(p);
        tableP.getItems().removeAll();
          LoadData2();

    }
   
   

   
}
