/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Entities.Classe;
import Service.ServiceActivite;
import Service.ServiceClassActivite;
import Service.ServiceClasse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class AffichageClasseController implements Initializable {

    @FXML
    private TableView<Classe> TableClasse;
    @FXML
    private TableColumn<Classe, String> Nomclasse;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField inputRecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceClasse sa = new ServiceClasse();
    ObservableList<Classe> liste = FXCollections.observableArrayList();
            liste= sa.getListClasse();
   
    remplirTable(liste );
    edittable();
    addButtonToTable();
   
    }    
    private void edittable(){
        Nomclasse.setCellFactory(TextFieldTableCell.forTableColumn());
        Nomclasse.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNomClasse(e.getNewValue());
        });
       
     TableClasse.setEditable(true);
    }
    
    private void remplirTable(ObservableList<Classe> liste ){
    
    Nomclasse.setCellValueFactory(new PropertyValueFactory<>("NomClasse"));
   
    TableClasse.setItems(liste);
    }
    private void addButtonToTable() {
        TableColumn<Classe, Void> colBtn = new TableColumn("Edit");

        Callback<TableColumn<Classe, Void>, TableCell<Classe, Void>> cellFactory = new Callback<TableColumn<Classe, Void>, TableCell<Classe, Void>>() {
            @Override
            public TableCell<Classe, Void> call(final TableColumn<Classe, Void> param) {
                final TableCell<Classe, Void> cell = new TableCell<Classe, Void>() {

                    private final Button btn = new Button("modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Classe data = getTableView().getItems().get(getIndex());
                            ServiceClasse sa = new ServiceClasse();
                            sa.modifierClasse(data.getIdClasse(),data.getNomClasse());
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

       TableClasse.getColumns().add(colBtn);

    }
   

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException 
    {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression dde Classe");
        alert.setHeaderText("Supprimer " + TableClasse.getSelectionModel().getSelectedItem().getIdClasse());
        alert.setContentText("Vous voulez vraiment supprimer la Classe " + TableClasse.getSelectionModel().getSelectedItem().getNomClasse()+ " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceClasse sa = new ServiceClasse();
        ServiceClassActivite sca = new ServiceClassActivite();
        sca.viderClasse(TableClasse.getSelectionModel().getSelectedItem().getIdClasse());
        Classe A1 = new Classe();
        A1 = TableClasse.getSelectionModel().getSelectedItem();
        sa.supprimerClasse(A1.getIdClasse());
       
    ObservableList<Classe> liste = FXCollections.observableArrayList();
            liste = sa.getListClasse();
            remplirTable(liste);
        }
        if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    private void recherche(ActionEvent event) {
        System.out.println(inputRecherche.getText());
         ServiceClasse sa = new ServiceClasse();
    ObservableList<Classe> liste = FXCollections.observableArrayList();
            liste= sa.rechercheClasse("nomclasse", inputRecherche.getText());
    remplirTable(liste );
    }

    @FXML
    private void afficherdetail(ActionEvent event) throws IOException {
        Classe data = new Classe();
        data = TableClasse.getSelectionModel().getSelectedItem();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageClasseActivite.fxml"));
                                
                                 Parent root;
                                root = loader.load();
                                inputRecherche.getScene().setRoot(root);
                                AffichageClasseActiviteController aca = loader.getController();
                                aca.setId(data.getIdClasse());
                                aca.setNom(data.getNomClasse());
                                //System.out.println(data.getIdClasse());
                                //System.out.println(data.getNomClasse());
        
    }

    @FXML
    private void gotoHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
        Parent root;
        root = loader.load();
        inputRecherche.getScene().setRoot(root);
    }

    @FXML
    private void gotoAjouterClasse(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterClasse.fxml"));
        Parent root;
        root = loader.load();
        inputRecherche.getScene().setRoot(root);
    }

    @FXML
    private void TfRecherche(KeyEvent event) {
        ObservableList<Classe> data2 = FXCollections.observableArrayList();
        Activite c = new Activite();
        ServiceClasse rc = new ServiceClasse();
        List<Classe> listActivite = rc.getListClasse();
        data2= FXCollections.observableArrayList(listActivite);
        FilteredList<Classe> filterData = new FilteredList<>(data2, p -> true);
       inputRecherche.setOnKeyReleased(e -> {
        inputRecherche.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            
            filterData.setPredicate(pers -> {
                // what is press ?
                if (newvalue == null || newvalue.isEmpty()) {
                System.out.println("first if empty "); 
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getNomClasse().toLowerCase().contains(typedText)
                        
                        ) {
                 //|| (Double.toString(cat.getPrix()).toLowerCase().contains(toLowerCaseNewValue))   
                     
                System.out.println("true"); 
                 return true;
                }
                
                System.out.println("false"); 
                return false;
            });
            
        });

            SortedList<Classe> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(TableClasse.comparatorProperty());
            TableClasse.setItems(sortedList);
            
       });   
    }
}
