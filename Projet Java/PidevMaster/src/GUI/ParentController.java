/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Enfant;
import Entities.User;
import com.teknikindustries.bulksms.SMS;

import Entities.addParent;
import Service.ServiceEnfant;
import Service.ServiceParent;
import Service.User_service;
import Utils.MailVerification;
import Utils.controleSaisie;
import java.awt.event.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Service;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author House
 */
public class ParentController implements Initializable {

    @FXML
    private ImageView add;
    @FXML
    private TextField TfRecherche;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
    }
     
    
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfCin;
    @FXML
    private TableColumn<addParent, Integer> colid;
    @FXML
    private TableColumn<addParent, String> colnom;
    @FXML
    private TableColumn<addParent, String> colPrenom;
    @FXML
    private TableColumn<addParent, String> colEmail;
    @FXML
    private TableColumn<addParent, String> colPhone;
    @FXML
    private TableColumn<addParent, String> colCin;
    @FXML
    private TableView table;
    

    /**
     * Initializes the controller class.
     */
    
    
   
     
    
    @FXML
    private void ajouter() {
        try {
            controleSaisie cs = new controleSaisie();
        
            ServiceParent sp1 = new ServiceParent();
            System.out.println("check: "+sp1.CheckParent(tfEmail.getText()));
            
            if(sp1.CheckParent(tfEmail.getText())!=0){
            JOptionPane.showMessageDialog(null,"Email Deja existe");
            }
            else if (!MailVerification.validate(tfEmail.getText())){
            
                    JOptionPane.showMessageDialog(null,"mail incorrect");
  
                }
            else if (!cs.testnomprenom(tfNom.getText())){
            
                    JOptionPane.showMessageDialog(null,"Nom incorrect");
  
                }
            else if (!cs.testnomprenom(tfPrenom.getText())){
            
                    JOptionPane.showMessageDialog(null,"Prenom incorrect");
  
                }
           else if (!cs.Cin(tfCin.getText())){
            
                    JOptionPane.showMessageDialog(null,"Cin 8 chiffre");
  
                }
            else if (!cs.GSM2(tfPhone.getText())){
            
                    JOptionPane.showMessageDialog(null,"Nombre de tel incorrect! Suivez la forme Suivante: 216{8 chiffre de numero}");
  
                }
            else {
            ServiceParent sp = new ServiceParent();
            String nomP = tfNom.getText();
            String prenomP = tfPrenom.getText();
            String cinP= tfCin.getText();
            String emailP = tfEmail.getText();
            String phoneP = tfPhone.getText();
            addParent p = new addParent(nomP, prenomP, cinP, emailP, phoneP);
            
            sp.ajouter(p);
            User_service us = new User_service();
            us.ajouterUser(new User(tfEmail.getText(),tfCin.getText(),"parent"));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Parent added with succes");
            alert.showAndWait();
            LoadData();
        SMS send = new SMS();
        send.SendSMS("said2000","Pidev2020", tfEmail.getText()+"Mot de passe: "+tfCin.getText(), tfPhone.getText(), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
            }
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
  
        
        
    
@FXML
        public void LoadData() {
         
        try {
            ServiceParent sp = new ServiceParent();
             ObservableList<addParent> list = FXCollections.observableArrayList();
            list= sp.readAll();
            //System.out.println(list);
            
         colid.setCellValueFactory(new PropertyValueFactory<>("id"));
         colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
         colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
         colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
         
         table.setItems(list);
         colnom.setCellFactory(TextFieldTableCell.forTableColumn());
        colnom.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        });
        colPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrenom.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue());
            
        });
        colCin.setCellFactory(TextFieldTableCell.forTableColumn());
        colCin.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCin(e.getNewValue());
            
        });
             colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
            
        });
             colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhone(e.getNewValue());
            
        });
     table.setEditable(true);
         
        
        
        } catch (SQLException ex) {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        @FXML
        public void LoadData2() {
         
            ServiceParent sp = new ServiceParent();
            ObservableList<addParent> list = FXCollections.observableArrayList();
            list= sp.rechercheParent(tfNom.getText());
            System.out.println(list);
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            table.setItems(list);
            colnom.setCellFactory(TextFieldTableCell.forTableColumn());
            colnom.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
            });
            colPrenom.setCellFactory(TextFieldTableCell.forTableColumn());
            colPrenom.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue());
                
            });
            colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
            colEmail.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
                
            });
            colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
            colPhone.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setPhone(e.getNewValue());
            
        });
        table.setEditable(true);
}

        

@FXML
private void addButtonToTable() {
        TableColumn<addParent, Void> colBtn = new TableColumn("Edit");

        Callback<TableColumn<addParent, Void>, TableCell<addParent, Void>> cellFactory = new Callback<TableColumn<addParent, Void>, TableCell<addParent, Void>>() {
            @Override
            public TableCell<addParent, Void> call(final TableColumn<addParent, Void> param) {
                final TableCell<addParent, Void> cell = new TableCell<addParent, Void>() {

                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            addParent data = getTableView().getItems().get(getIndex());
                            ServiceParent sa = new ServiceParent();
                            sa.modifierParent(data.getId(),data.getNom(), data.getPrenom(),data.getCin(),data.getEmail(),data.getPhone());
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

       table.getColumns().add(colBtn);

    }
        @FXML
    private void deleteparent() throws IOException {
  
        Object p = table.getSelectionModel().getSelectedItem();
        ServiceParent sp =new ServiceParent();
        try {
            sp.delete((addParent) p);
            sp.setEnfantNull(((addParent) p).getId());
        } catch (SQLException ex) {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.getItems().removeAll();
          LoadData();
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Parent deleted with succes");
            alert.showAndWait();

    }

    @FXML
    private void gotoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
    }

    @FXML
    private void detailParent(ActionEvent event) throws IOException {
        addParent data = new addParent();
        data = (addParent) table.getSelectionModel().getSelectedItem();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageDetailParent.fxml"));
                                
                                 Parent root;
                                root = loader.load();
                                tfNom.getScene().setRoot(root);
                                AffichageDetailParentController aca = loader.getController();
                                aca.setId(data.getId());
                                aca.setNom(data.getNom());
                                aca.setPrenom(data.getPrenom());
                                //System.out.println(data.getIdClasse());
                                //System.out.println(data.getNomClasse());
        
        
    }

    @FXML
    private void TfRecherche(KeyEvent event) throws SQLException {
        ObservableList<addParent> data2 = FXCollections.observableArrayList();
        addParent c = new addParent();
        ServiceParent rc = new ServiceParent();
        ObservableList<addParent> listActivite = rc.readAll();
        data2= FXCollections.observableArrayList(listActivite);
        FilteredList<addParent> filterData = new FilteredList<>(data2, p -> true);
       TfRecherche.setOnKeyReleased(e -> {
        TfRecherche.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            
            filterData.setPredicate(pers -> {
                // what is press ?
                if (newvalue == null || newvalue.isEmpty()) {
                
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (pers.getNom().toLowerCase().contains(typedText) ||
                        
                        pers.getCin().toLowerCase().contains(typedText)||
                        
                        pers.getPrenom().toLowerCase().contains(typedText)||
                        
                        pers.getEmail().toString().contains(typedText)||
                        
                        pers.getPhone().toString().contains(typedText)
                        
                        ) {
                 //|| (Double.toString(cat.getPrix()).toLowerCase().contains(toLowerCaseNewValue))   
                     
               
                 return true;
                }
                
               
                return false;
            });
            
        });

            SortedList<addParent> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);
            
       }); 
    }

    
    
        
      

    
        
        
        
}
 

        
        
            
            
            
        
               
    
    

