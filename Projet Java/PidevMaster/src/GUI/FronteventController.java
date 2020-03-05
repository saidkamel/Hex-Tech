/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Employe;
import Entities.evenement;
import Service.EmployeService;
import Service.EvenementService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class FronteventController implements Initializable {

    @FXML
    private TableView<evenement> table;
    @FXML
    private TableColumn<?, ?> TbNom;
    @FXML
    private TableColumn<?, ?> TbType;
    @FXML
    private TableColumn<?, ?> TbDateDebut;
    @FXML
    private TableColumn<?, ?> TbDateFin;
    @FXML
    private TableColumn<?, ?> TbDescription;
    @FXML
    private TableColumn<?, ?> TbNp;
    @FXML
    private TextField TfRech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
    }    

    @FXML
    private void TfRecherche(KeyEvent event) {
        
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
    public void LoadData(){
     EvenementService es = new EvenementService();
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
     TbType.setCellValueFactory(new PropertyValueFactory<>("Type"));
     TbDateDebut.setCellValueFactory(new PropertyValueFactory<>("Date_Debut"));
     TbDateFin.setCellValueFactory(new PropertyValueFactory<>("Date_Fin"));
     TbDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
     TbNp.setCellValueFactory(new PropertyValueFactory<>("nbr_participant"));
     table.setItems(es.getListEvenements());

     
        
    }

    @FXML
    private void gotoFrontParent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontParent.fxml"));
        Parent root = loader.load();
        TfRech.getScene().setRoot(root);
    }
}
