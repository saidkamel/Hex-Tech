/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.jardinsEnfants;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
 import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import Service.jardinService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Service.reclamationService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class JardinEnfantsAjoutController implements Initializable {

    @FXML
    private TextField Id;
    @FXML
    private TextField Nom;
    @FXML
    private TextField cap;
    @FXML
    private TextField Num;
    @FXML
    private TextField Mail;
    @FXML
    private TextField activite;
    @FXML
    private TextField localisation;
    @FXML
    private Button Ajout;
    @FXML
    private TableView<jardinsEnfants> tableJardin;
    @FXML
    private TableColumn<jardinsEnfants, String> Tid;
    @FXML
    private TableColumn<jardinsEnfants, String> Tnom;
    @FXML
    private TableColumn<jardinsEnfants, String> Tnom1;
    @FXML
    private TableColumn<jardinsEnfants, String> Tnom2;
    @FXML
    private TableColumn<jardinsEnfants, String> Tnom3;
    @FXML
    private TableColumn<jardinsEnfants, String> Tnom4;
    @FXML
    private TableColumn<jardinsEnfants, String> Tnom5;
    @FXML
    private ToggleButton supp;
    @FXML
    private ToggleGroup menu;
    @FXML
    private Button update;

    @FXML
    private TextField Trechercher;
    @FXML
    private ImageView imghome;
    jardinService js = new jardinService();
    private final ObservableList<jardinsEnfants> ListC = FXCollections.observableArrayList(js.getlistjardins());
    @FXML
    private Button btnqr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
        afficher();
            
            FilteredList<jardinsEnfants> filteredData = new FilteredList<>(ListC, b -> true);
 	// 2. Set the filter Predicate whenever the filter changes.
		Trechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Jard -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Jard.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Jard.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Jard.getCapacite()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
			
	
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<jardinsEnfants> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableJardin.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
tableJardin.setItems(sortedData);

//    tableJardin.getSelectionModel().selectedItemProperty().
//        addListener((observable, oldValue, newValue) -> {
//        });
   
    }    

      @FXML
    private void qrCode(ActionEvent event) throws SQLException{
       QrCodeController q=new QrCodeController();
       q.ini(tableJardin.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void Ajouter(Event evt) throws IOException{
        
        int id=Integer.parseInt(Id.getText());
        String nom=Nom.getText();
        int capacite=Integer.parseInt(cap.getText());
        int num=Integer.parseInt(Num.getText());
        String mail=Mail.getText();
        String Activite=activite.getText();
        String Localisation=localisation.getText();
        
        jardinsEnfants j= new jardinsEnfants(id, nom, capacite,num,mail,Activite,Localisation);
        jardinService js=new jardinService();
        js.ajouterJardins(j);
         ObservableList<jardinsEnfants> ListC = FXCollections.observableArrayList(js.getlistjardins());



tableJardin.getSelectionModel().selectedItemProperty().
        addListener((observable, oldValue, newValue) -> {
        });
afficher();

    }

void afficher(){
    jardinService aaa = new jardinService();/*try {
        jardinService js= new jardinService();
        // List<Command> list = sp.readAll();
        data.addAll(sc.readAll());
        idc.setCellValueFactory(new PropertyValueFactory<Comptable,Integer>("ID_comptable"));
        nomc.setCellValueFactory(new PropertyValueFactory<Comptable,String>("nom_comptable"));
        prenomc.setCellValueFactory(new PropertyValueFactory<Comptable,String>("prenom_comptable"));
        etatc.setCellValueFactory(new PropertyValueFactory<Comptable,String>("etat_comptable"));
        telc.setCellValueFactory(new PropertyValueFactory<Comptable,Integer>("telephone_comptable"));
        adressec.setCellValueFactory(new PropertyValueFactory<Comptable,String>("adressemail_comptable"));
        table.setItems(data);
        } catch (SQLException ex) {
        Logger.getLogger(AffichercomptableController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    ///   User aea = new User();
    tableJardin.setItems(aaa.displayall());
    /* Tid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<jardinsEnfants, String>, ObservableValue<String>>() {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<jardinsEnfants, String> param) {
    return new SimpleStringProperty(param.getValue().getUser().getNom());
    }
    });
    
    tbprenomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarteFidelite, String>, ObservableValue<String>>() {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarteFidelite, String> param) {
    return new SimpleStringProperty(param.getValue().getUser().getPrenom());
    }
    });*/
    
    Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
    Tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    Tnom1.setCellValueFactory(new PropertyValueFactory<>("capacite"));
    Tnom2.setCellValueFactory(new PropertyValueFactory<>("num"));
    Tnom3.setCellValueFactory(new PropertyValueFactory<>("mail"));
    Tnom4.setCellValueFactory(new PropertyValueFactory<>("activite"));
    Tnom5.setCellValueFactory(new PropertyValueFactory<>("localisation"));
}

    @FXML
    private void supprimerJardin(ActionEvent event) throws SQLException, IOException {
      jardinsEnfants r = (jardinsEnfants) tableJardin.getSelectionModel().getSelectedItem();
        jardinService rs = new jardinService();
        rs.Supprimerjardins(r.getId());
        tableJardin.getItems().removeAll();
               
                afficher();
 

    }
    

    
    
    private void pageReclamation(ActionEvent event) throws IOException {
        
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("reclamation.fxml"));
        Parent root= loader.load();
        Num.getScene().setRoot(root);
        reclamationService rs = new reclamationService();
        System.out.println("aaaa");
        System.out.println(rs.displayall());
        
    }

    
    public void initierJardin(jardinsEnfants j) {
        String id = Integer.toString(j.getId());
        Tid.setText(id);
        Tnom.setText(j.getNom());
         String capacite = Integer.toString(j.getCapacite());
        cap.setText(capacite);
        String num = Integer.toString(j.getNum());
        Num.setText(num);
        Mail.setText(j.getMail());  
        activite.setText(j.getActivite());
        localisation.setText(j.getLocalisation());



        
        
        
        
        
        /*date.setText(ca.getDate());
        String shd = Integer.toString(ca.getHeureDebut());
        heureDebut.setText(shd);
        String shf = Integer.toString(ca.getHeureFin());
        heureFin.setText(shf);
        salle.setText(ca.getSalle());*/

    }
    @FXML
    private void modifier(ActionEvent event) throws IOException {
    
jardinsEnfants j = new jardinsEnfants();
        tableJardin.getSelectionModel().getSelectedItem().getId();

        jardinService js = new jardinService();
   /* int id=Integer.parseInt(Id.getText());
       String nom=Nom.getText();
        int capacite=Integer.parseInt(cap.getText());
        int num=Integer.parseInt(Num.getText());
        String mail=Mail.getText();
        String Activite=activite.getText();
        String Localisation=localisation.getText();**/
        
      //  jardinsEnfants j= new jardinsEnfants(id, nom, capacite,num,mail,Activite,Localisation);


        /*js.modifierJardins(tableJardin.getSelectionModel().getSelectedItem().getId(),         tableJardin.getSelectionModel().getSelectedItem().getNom()
,         tableJardin.getSelectionModel().getSelectedItem().getCapacite()
,        tableJardin.getSelectionModel().getSelectedItem().getNum()
,        tableJardin.getSelectionModel().getSelectedItem().getMail()
,        tableJardin.getSelectionModel().getSelectedItem().getActivite()
,        tableJardin.getSelectionModel().getSelectedItem().getLocalisation()
);*/
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("modifierJardin.fxml"));
        Parent root= loader.load();
        Num.getScene().setRoot(root);
        ModifierJardinController mjc = loader.getController();
        mjc.setTid(tableJardin.getSelectionModel().getSelectedItem().getId());
        mjc.setTnom(tableJardin.getSelectionModel().getSelectedItem().getNom());
        int cap = tableJardin.getSelectionModel().getSelectedItem().getCapacite();
        String ca = Integer.toString(cap);
        mjc.setTcapacite(ca);
        int num = tableJardin.getSelectionModel().getSelectedItem().getNum();
        String n = Integer.toString(num);
        mjc.setTnum(n);
//        mjc.setTnum(tableJardin.getSelectionModel().getSelectedItem().getNum());
        mjc.setTmail(tableJardin.getSelectionModel().getSelectedItem().getMail());
        mjc.setTactivite(tableJardin.getSelectionModel().getSelectedItem().getActivite());
        mjc.setTlocalisation(tableJardin.getSelectionModel().getSelectedItem().getLocalisation());




    
    /* try {
            if (tableJardin.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setContentText("Aucun element n'est selectionné");
                alert.showAndWait();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierJardin.fxml"));
            Parent root = loader.load();
            JardinEnfantsAjoutController jeac = loader.getController();
            jardinsEnfants j= tableJardin.getSelectionModel().getSelectedItem();
            jardinService js = new jardinService();
            int id = j.getId();
            jeac.initierJardin(j);
            update.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(JardinEnfantsAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
        
     /*   modifier.setOnAction(e-> {
 jardinsEnfants j = tableJardin.getSelectionModel().getSelectedItem();
 jardinEnfantsAjoutController.id=j.getId();
 AfficherclubController.nom_c=ref.getNom_club();
 AfficherclubController.type_c=ref.getType_club();


 
           // AfficheremployeController.vv = selectedItems.toString().split(",")[0].substring(1);
             
            try {
            Parent root = FXMLLoader.load(getClass().getResource("Updateeleve.fxml"));
            Stage stage = (Stage) modifier.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        
    }
    
 
    */
    
    }

    @FXML
    private void gohome(MouseEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            MenuController cac = loader.getController();
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(JardinEnfantsAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
        
    }
    


