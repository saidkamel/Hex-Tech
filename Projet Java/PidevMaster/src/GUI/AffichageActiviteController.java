/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Service.ServiceActivite;
import Service.ServiceEnfant;
import Utils.Notification;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class AffichageActiviteController implements Initializable {

    @FXML
    private TableView<Activite> tableActivite;
    @FXML
    private TableColumn<Activite, Integer> idactivite;
    @FXML
    private TableColumn<Activite, String> nomactivite;
    @FXML
    private TableColumn<Activite, String> descactivite;
    @FXML
    private TableColumn<Activite, String> typeactivite;
    private TextField inputRecherche;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button AjouterAc;
    @FXML
    private TextField tfrech;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceActivite sa = new ServiceActivite();
    ObservableList<Activite> liste = FXCollections.observableArrayList();
            liste= sa.getListActivite();
    remplirTable(liste );
    edittable();
    addButtonToTable();
    
    
    } 
   
	//modifier : 
    private void edittable(){
        nomactivite.setCellFactory(TextFieldTableCell.forTableColumn());
        nomactivite.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomActivite(e.getNewValue());
                      
        
        });
        descactivite.setCellFactory(TextFieldTableCell.forTableColumn());
        descactivite.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue());
        });
        typeactivite.setCellFactory(TextFieldTableCell.forTableColumn());
        typeactivite.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue());
        });
     tableActivite.setEditable(true);
    }

   
   
   
    @FXML
    private void SupprimerActivite(ActionEvent event) throws AWTException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression d'activite");
        alert.setHeaderText("Supprimer " + tableActivite.getSelectionModel().getSelectedItem().getIdActivite());
        alert.setContentText("Vous voulez vraiment supprimer l'activite " + tableActivite.getSelectionModel().getSelectedItem().getNomActivite() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceActivite sa = new ServiceActivite();
        Activite A1 = new Activite();
        A1 = tableActivite.getSelectionModel().getSelectedItem();
        sa.supprimerActivite(A1.getIdActivite());
        Notification.main("Activite ! ", "Activite supprime avec succé");
        ObservableList<Activite> liste = FXCollections.observableArrayList();
            liste = sa.getListActivite();
            remplirTable(liste);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        
    }

    @FXML
    private void AjouterActivite(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterActivite.fxml"));
        Parent root = loader.load();
        tfrech.getScene().setRoot(root);
    }
    private void remplirTable(ObservableList<Activite> liste ){
     idactivite.setCellValueFactory(new PropertyValueFactory<>("IdActivite"));
    nomactivite.setCellValueFactory(new PropertyValueFactory<>("NomActivite"));
    descactivite.setCellValueFactory(new PropertyValueFactory<>("Description"));
    typeactivite.setCellValueFactory(new PropertyValueFactory<>("Type"));
    tableActivite.setItems(liste);
    }
	//ajout bouton modifier: 
    private void addButtonToTable() {
        TableColumn<Activite, Void> colBtn = new TableColumn("Edit");

        Callback<TableColumn<Activite, Void>, TableCell<Activite, Void>> cellFactory = new Callback<TableColumn<Activite, Void>, TableCell<Activite, Void>>() {
            @Override
            public TableCell<Activite, Void> call(final TableColumn<Activite, Void> param) {
                final TableCell<Activite, Void> cell = new TableCell<Activite, Void>() {

                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Activite data = getTableView().getItems().get(getIndex());
                            ServiceActivite sa = new ServiceActivite();
                            sa.modifierActivite(data.getIdActivite(), data.getNomActivite(), data.getDescription(), data.getType());
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
            }
        };

        colBtn.setCellFactory(cellFactory);

       tableActivite.getColumns().add(colBtn);

    }

    

    @FXML
    private void gotoHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
        Parent root;
        root = loader.load();
        tfrech.getScene().setRoot(root);
    }
    @FXML
    private void tfrech(KeyEvent event) {
       
        ObservableList<Activite> data2 = FXCollections.observableArrayList();
        Activite c = new Activite();
        ServiceActivite rc = new ServiceActivite();
        List<Activite> listActivite = rc.getListActivite2();
        data2= FXCollections.observableArrayList(listActivite);
        FilteredList<Activite> filterData = new FilteredList<>(data2, p -> true);
       tfrech.setOnKeyReleased(e -> {
        tfrech.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            
            filterData.setPredicate(pers -> {
                // what is press ?
                if (newvalue == null || newvalue.isEmpty()) {
               
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getNomActivite().toLowerCase().contains(typedText) ||
                        
                        pers.getDescription().toLowerCase().contains(typedText)||
                        
                        pers.getType().toLowerCase().contains(typedText)
                        
                        ) {
                 //|| (Double.toString(cat.getPrix()).toLowerCase().contains(toLowerCaseNewValue))   
                     
               
                 return true;
                }
                
             
                return false;
            });
            
        });

            SortedList<Activite> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableActivite.comparatorProperty());
            tableActivite.setItems(sortedList);
            
       });       
 
    }

  

    
    
    
}
