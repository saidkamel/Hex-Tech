/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import Entities.Employe;
import Service.EmployeService;
import Service.ServiceActivite;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author alaka
 */
public class AfficheEmployeController implements Initializable {

    @FXML
    private TableColumn<Employe, String> tfNom;
    @FXML
    private TableColumn<Employe, String> tfPrenom;
    @FXML
    private TableColumn<Employe, String> tfTitre;
    @FXML
    private TableColumn<Employe, Integer> tfSalaire;
    @FXML
    private TableColumn<Employe, String> tfEmail;
    @FXML
    private TableColumn<Employe, Integer> tfAbsence;
    @FXML
    private TableView<Employe> TableEmp;
    @FXML
    private Button tfSupp;
    @FXML
    private TextField tfRech;
    @FXML
    private Button btnRech;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private TableColumn<Employe, Integer> tfCin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         EmployeService es = new EmployeService();
         ObservableList<Employe> list = FXCollections.observableArrayList();
         list=es.consulterEmploye();
         tfNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         tfPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         tfCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
         tfTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
         tfSalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
         tfEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         tfAbsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
         TableEmp.setItems(list);

         

    }    

    @FXML
    private void SupprimerEmploye(ActionEvent event) {
        
         EmployeService es = new EmployeService();
         Employe e = new Employe();
         e = TableEmp.getSelectionModel().getSelectedItem();
         es.supprimerEmploye(e.getId());
         
         
         ObservableList<Employe> list = FXCollections.observableArrayList();
         list=es.consulterEmploye();
         tfNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         tfPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         tfCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
         tfTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
         tfSalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
         tfEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         tfAbsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
         TableEmp.setItems(list);

        
    }

    @FXML
    private void RechercherEmploye(ActionEvent event) {
        
        EmployeService es = new EmployeService();
         ObservableList<Employe> list = FXCollections.observableArrayList();
         list=es.rechercheEmploye("nom", tfRech.getText());
         tfNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         tfPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         tfCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
         tfTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
         tfSalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
         tfEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         tfAbsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
         TableEmp.setItems(list);

        
    }

    @FXML
    private void ajouterEmploye(ActionEvent event) throws IOException {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("ajoutPersonne.fxml"));
        Parent root = loader.load();
        tfRech.getScene().setRoot(root);
    }

    @FXML
    private void modifierEmploye(ActionEvent event) throws IOException {
        EmployeService es = new EmployeService();
         Employe e = new Employe();
         e = TableEmp.getSelectionModel().getSelectedItem();
         FXMLLoader loader= new FXMLLoader(getClass().getResource("modifierEmploye.fxml"));
        Parent root = loader.load();
        tfRech.getScene().setRoot(root);
        ModifierEmployeController mec = loader.getController();
        mec.setLbid(e.getId());
        mec.setNom(e.getNom());
        mec.setPrenom(e.getPrenom());
        mec.setCin(e.getCin());
        mec.setTitre(e.getTitre());
        mec.setSalaire(e.getSalaire());
        mec.setEmail(e.getEmail());
        mec.setAbsence(e.getAbsence());
    }

    @FXML
    private void rechercheE(KeyEvent event) {
         ObservableList<Employe> data2 = FXCollections.observableArrayList();
        Employe c = new Employe();
        EmployeService rc = new EmployeService();
        List<Employe> listEmp = rc.consulterEmploye();
        data2= FXCollections.observableArrayList(listEmp);
        FilteredList<Employe> filterData = new FilteredList<>(data2, p -> true);
       tfRech.setOnKeyReleased(e -> {
        tfRech.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            
            filterData.setPredicate(pers -> {
                // what is press ?
                if (newvalue == null || newvalue.isEmpty()) {
                System.out.println("first if empty "); 
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getNom().toLowerCase().contains(typedText) ||
                        
                        pers.getEmail().toLowerCase().contains(typedText)||
                        
                        pers.getTitre().toLowerCase().contains(typedText)||
                        
                        pers.getPrenom().toLowerCase().contains(typedText)
                        
                        ) {
                 //|| (Double.toString(cat.getPrix()).toLowerCase().contains(toLowerCaseNewValue))   
                     
                System.out.println("true"); 
                 return true;
                }
                
                System.out.println("false"); 
                return false;
            });
            
        });

            SortedList<Employe> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(TableEmp.comparatorProperty());
            TableEmp.setItems(sortedList);
            
       });      
    }

    @FXML
    private void GotoBack(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Back.fxml"));
        Parent root = loader.load();
        tfRech.getScene().setRoot(root);
    }
    
}
