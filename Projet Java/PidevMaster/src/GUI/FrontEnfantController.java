/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enfant;
import Service.ServiceEnfant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sa3do
 */
public class FrontEnfantController implements Initializable {

    @FXML
    private TableView<Enfant> table;
    @FXML
    private TableColumn<?, ?> TbNom;
    @FXML
    private TableColumn<?, ?> TbPrenom;
    @FXML
    private TableColumn<?, ?> DateNaissances;
    @FXML
    private TableColumn<?, ?> nomclasse;
    @FXML
    private Label Lbid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void LoadData(){
     ServiceEnfant es = new ServiceEnfant();
     TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
     TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
     DateNaissances.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));    
     nomclasse.setCellValueFactory(new PropertyValueFactory<>("nomclasse"));    
     table.setItems(es.getListEnfants2(Integer.parseInt(Lbid.getText())));
    }

    void setLbid(int id) {
        this.Lbid.setText(String.valueOf(id));
                }

    @FXML
    private void actualiser(ActionEvent event) {
        LoadData();
    }

    @FXML
    private void gotoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontParent.fxml"));
        Parent root = loader.load();
        Lbid.getScene().setRoot(root);
    }
}
