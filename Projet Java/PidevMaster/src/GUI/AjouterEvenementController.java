/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.evenement;
import Service.EvenementService;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    private ComboBox<String> TfType;
    @FXML
    private DatePicker DateDebut;
    @FXML
    private DatePicker DateFin;
    @FXML
    private TextField TfDescription;
    //@FXML
   // private TextField TfNbr;
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
    @FXML
    private TextField TfRech;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              LoadData();
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
    private void AjouterEvent(ActionEvent event) throws IOException, SQLException{

        EvenementService es = new EvenementService();
        evenement e = new evenement();
        LocalDate ldd=DateDebut.getValue();
        LocalDate ldf=DateFin.getValue();
        Date dateD = Date.valueOf(ldd);
        Date dateF = Date.valueOf(ldf);
        
        e.setNom(TfNom.getText());
        e.setType(TfType.getValue());
        e.setDate_Debut(dateD);
        e.setDate_Fin(dateF);
        e.setDescription(TfDescription.getText());
       // e.setNbr_participant(Integer.parseInt(TfNbr.getText()));

      
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

    @FXML
    private void gotoBack(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Back.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
    }

    @FXML
    private void TfRech(KeyEvent event) {
        ObservableList<evenement> data2 = FXCollections.observableArrayList();
        evenement c = new evenement();
        EvenementService rc = new EvenementService();
        ObservableList<evenement> listEmp = rc.getListEvenements();
        data2= FXCollections.observableArrayList(listEmp);
        FilteredList<evenement> filterData = new FilteredList<>(data2, p -> true);
       TfRech.setOnKeyReleased(e -> {
        TfRech.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            
            filterData.setPredicate(pers -> {
                // what is press ?
                if (newvalue == null || newvalue.isEmpty()) {
                System.out.println("first if empty "); 
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getNom().toLowerCase().contains(typedText) ||
                        
                        pers.getType().toLowerCase().contains(typedText)||
                        
                        pers.getDate_Debut().toString().contains(typedText)||
                        
                        pers.getDate_Fin().toString().contains(typedText)||
                        
                        pers.getDescription().toLowerCase().contains(typedText) 
                        
                        ) {
                 //|| (Double.toString(cat.getPrix()).toLowerCase().contains(toLowerCaseNewValue))   
                     
                System.out.println("true"); 
                 return true;
                }
                
                System.out.println("false"); 
                return false;
            });
            
        });

            SortedList<evenement> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
            
       }); 
        
    }
}