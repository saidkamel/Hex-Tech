/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enfant;
import Service.ServiceEnfant;
import Service.ServiceParent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class AffichageDetailParentController implements Initializable {

    @FXML
    private Label idparent;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbprenom;
    @FXML
    private TableView<Enfant> table;
    @FXML
    private TableColumn<?, ?> TbNom;
    @FXML
    private TableColumn<?, ?> TbPrenom;
    @FXML
    private TableColumn<?, ?> DateNaissances;
    @FXML
    private TableColumn<?, ? > nomclasse;
    @FXML
    private ComboBox<String> comboenfant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idparent.setVisible(false);
        LoadCombo();
              
          //LoadData();
    }    
    public void LoadCombo()
    {
    ServiceEnfant es = new ServiceEnfant();
        List<String> list = es.getListEnfants3();
        comboenfant.getItems().setAll(list);
               
    }
    public void LoadData(){
     ServiceEnfant es = new ServiceEnfant();
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
     TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
     DateNaissances.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));    
     nomclasse.setCellValueFactory(new PropertyValueFactory<>("nomclasse"));    
     table.setItems(es.getListEnfants2(Integer.parseInt(idparent.getText())));
    }

    void setId(int id) {
    this.idparent.setText(String.valueOf(id)) ;   
    }

    void setNom(String nom) {
    this.lbnom.setText(nom);   
    
    }
    void setPrenom(String nom) {
    this.lbprenom.setText(nom);   
    
    }

    @FXML
    private void affichertable(ActionEvent event) {
        LoadData();
    }

    @FXML
    private void gotoParent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionParent.fxml"));
        Parent root = loader.load();
        lbnom.getScene().setRoot(root);
    }

    @FXML
    private void affecterenfant(ActionEvent event) {
        ServiceEnfant es = new ServiceEnfant();
        es.affecterEnfant(comboenfant.getValue(),Integer.parseInt(idparent.getText()));
        System.out.println("c bon ");
        LoadData();
        LoadCombo();
        
    }

    @FXML
    private void SetParentNull(ActionEvent event) {
        //table.getSelectionModel().getSelectedItem();
        ServiceParent sp = new ServiceParent();
        sp.setEnfantNull2(table.getSelectionModel().getSelectedItem().getId(),Integer.valueOf(idparent.getText()));
        LoadData();
        LoadCombo();
    }
}
