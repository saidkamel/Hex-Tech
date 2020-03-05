/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Entities.Enfant;
import Service.ServiceEnfant;
import Utils.DataBase;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import IService.IServiceEnfant;
import Service.ServiceActivite;
import Service.ServiceClasse;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class AjouterEnfantController implements Initializable {

    
  
    @FXML
    private TableView<Enfant> table;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private TextField TfPrenom;
    @FXML
    private TableColumn<?, ?> TbPrenom;
    @FXML
    private AnchorPane btn1;
    @FXML
    private TextField TfNom;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<?, ?> TbId;
    @FXML
    private TableColumn<?, ?> TbNom;
    @FXML
    private TableColumn<?, ?> DateNaissances;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField TfRecherche;
    @FXML
    private ComboBox<String> comboclasse;
    @FXML
    private TableColumn<?, ?> nomclasse;
      
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              LoadData();
              ServiceClasse es = new ServiceClasse();
              List<String> list = es.getListClasse2();
              comboclasse.getItems().setAll(list);
              
    }    
    
    @FXML
    private void AjouterEnfant(ActionEvent event) throws IOException{

        ServiceEnfant es = new ServiceEnfant();
        Enfant e = new Enfant();
        LocalDate ldd=DateNaissance.getValue();
        Date dateD = Date.valueOf(ldd);
        
        e.setNom(TfNom.getText());
        e.setPrenom(TfPrenom.getText());
        e.setDateNaissance(dateD);
       


      
        try {
            es.ajouter(e);
            es.affecterEnfantClasse(e.getNom(), 1);
            
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Enfant ajouté !");
        
       FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
         Parent root=loader.load();
         TfPrenom.getScene().setRoot(root);
          
   
     }
       @FXML
    private void DeleteEnfant(ActionEvent event) throws IOException{
        Enfant e = table.getSelectionModel().getSelectedItem();
        ServiceEnfant es =new ServiceEnfant();
        es.SupprimerEnfant(e.getId());
        table.getItems().removeAll();
          LoadData();

    }
       
    public void LoadData(){
     ServiceEnfant es = new ServiceEnfant();
     
     TbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
     TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
     DateNaissances.setCellValueFactory(new PropertyValueFactory<>("DateNaissance")); 
     nomclasse.setCellValueFactory(new PropertyValueFactory<>("nomclasse")); 
     table.setItems(es.getListEnfants1());
    }
    private void RechercherEnfant(ActionEvent event) {
        
        ServiceEnfant es = new ServiceEnfant();
         ObservableList<Enfant> list = FXCollections.observableArrayList();
         list=es.rechercheEnfant("nom", TfRecherche.getText());
         TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
         TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
         DateNaissances.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
         
         
         table.setItems(list);

        
    }
    
@FXML
    private void UpdateEnfant(ActionEvent event) throws IOException{
              FXMLLoader loader=new FXMLLoader(getClass().getResource("UpdateEnfant_1.fxml"));
         Parent root=loader.load();
         TfNom.getScene().setRoot(root);
            UpdateEnfantController uec = loader.getController();
            
            uec.setid(table.getSelectionModel().getSelectedItem().getId());
            uec.setnom(table.getSelectionModel().getSelectedItem().getNom());
            uec.setPrenom(table.getSelectionModel().getSelectedItem().getPrenom());
            uec.setDateNaissance(table.getSelectionModel().getSelectedItem().getDateNaissance());

        
       
         

    }

    @FXML
    private void GotoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
        Parent root = loader.load();
        TfNom.getScene().setRoot(root);
        
    }

    @FXML
    private void affecterclasse(ActionEvent event) {
        ServiceClasse sc = new ServiceClasse();
        ServiceEnfant se = new ServiceEnfant();
        se.affecterEnfantClasse(table.getSelectionModel().getSelectedItem().getNom(),sc.getIdClasse(comboclasse.getValue()));
           LoadData();     
    }

    @FXML
    private void TfRecherche(KeyEvent event) {
         ObservableList<Enfant> data2 = FXCollections.observableArrayList();
        Enfant c = new Enfant();
        ServiceEnfant rc = new ServiceEnfant();
        ObservableList<Enfant> listActivite = rc.getListEnfants1();
        data2= FXCollections.observableArrayList(listActivite);
        FilteredList<Enfant> filterData = new FilteredList<>(data2, p -> true);
       TfRecherche.setOnKeyReleased(e -> {
        TfRecherche.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            
            filterData.setPredicate(pers -> {
                // what is press ?
                if (newvalue == null || newvalue.isEmpty()) {
                
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getNom().toLowerCase().contains(typedText) ||
                        
                        pers.getNomclasse().toLowerCase().contains(typedText)||
                        
                        pers.getPrenom().toLowerCase().contains(typedText)||
                        
                        pers.getDateNaissance().toString().contains(typedText)
                        
                        ) {
                 //|| (Double.toString(cat.getPrix()).toLowerCase().contains(toLowerCaseNewValue))   
                     
               
                 return true;
                }
                
               
                return false;
            });
            
        });

            SortedList<Enfant> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
            
       }); 
    }

    

    
}