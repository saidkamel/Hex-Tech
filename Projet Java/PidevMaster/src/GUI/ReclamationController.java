/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.jardinsEnfants;
import Entities.reclamation;
import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Service.jardinService;
import Service.reclamationService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextField Tcin;
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tprenom;
    @FXML
    private TextField Tnote;
    @FXML
    private TextField desc;
    @FXML
    private Button aj;
    @FXML
    private TextField Tmotif;
    
    @FXML
    private TableView<reclamation> tableReclamation;
    @FXML
    private TableColumn<reclamation, String> cinn;
    @FXML
    private TableColumn<reclamation, String> nomm;
    @FXML
    private TableColumn<reclamation, String> prenomm;
    @FXML
    private TableColumn<reclamation, String> notee;
    @FXML
    private TableColumn<reclamation, String> motiff;
    @FXML
    private TableColumn<reclamation, String> descc;
    @FXML
    private TableColumn<reclamation, String> datee;
    @FXML
    private Button modif;
    @FXML
    private Button supp;
         @FXML
    private ImageView imghome;
    @FXML
    private TableColumn<?, ?> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          reclamationService rs = new reclamationService();
            ObservableList<reclamation> ListC = FXCollections.observableArrayList(rs.displayall()); 
         
            LoadData();
    }    
    
        

    @FXML
    private void ajouter(ActionEvent event) throws ParseException {
        User u = new User();
        int cinParent=Integer.parseInt(Tcin.getText());
        String nomParent=Tnom.getText();
        String prenomParent=Tprenom.getText();
        int note=Integer.parseInt(Tnote.getText());
        String type=Tmotif.getText();
        String description=desc.getText();
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
//        Date d = sdf.parse(date.toString());
        u.setCin(cinParent);
        u.setNom(nomParent);
        u.setPrenom(prenomParent);
        reclamation r= new reclamation(u.getCin(),u.getNom(),u.getPrenom(), note, type, description, date);
        reclamationService rs=new reclamationService();
        rs.ajouterReclamation(r,u);
         ObservableList<reclamation> List = FXCollections.observableArrayList(rs.getlistReclamation());
LoadData();

    }
    public void LoadData(){
        reclamationService js = new reclamationService();
        tableReclamation.setItems(js.displayall());
        cinn.setCellValueFactory(new PropertyValueFactory<>("cinParent"));
        nomm.setCellValueFactory(new PropertyValueFactory<>("nomParent"));
        prenomm.setCellValueFactory(new PropertyValueFactory<>("prenomParent"));
        notee.setCellValueFactory(new PropertyValueFactory<>("note"));
        motiff.setCellValueFactory(new PropertyValueFactory<>("type"));
        descc.setCellValueFactory(new PropertyValueFactory<>("description"));
        datee.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        
    }

//void afficher(){
//   reclamationService rs = new reclamationService();
////            ObservableList<reclamation> List = FXCollections.observableArrayList(rs.displayall());
//
//
//    /*try {
//        jardinService js= new jardinService();
        // List<Command> list = sp.readAll();
//        data.addAll(sc.readAll());
//        idc.setCellValueFactory(new PropertyValueFactory<Comptable,Integer>("ID_comptable"));
//        nomc.setCellValueFactory(new PropertyValueFactory<Comptable,String>("nom_comptable"));
//        prenomc.setCellValueFactory(new PropertyValueFactory<Comptable,String>("prenom_comptable"));
//        etatc.setCellValueFactory(new PropertyValueFactory<Comptable,String>("etat_comptable"));
//        telc.setCellValueFactory(new PropertyValueFactory<Comptable,Integer>("telephone_comptable"));
//        adressec.setCellValueFactory(new PropertyValueFactory<Comptable,String>("adressemail_comptable"));
//        table.setItems(data);
//        } catch (SQLException ex) {
//        Logger.getLogger(AffichercomptableController.class.getName()).log(Level.SEVERE, null, ex);
//        }*/
//    ///   User aea = new User();
//    tableReclamation.setItems(rs.displayall());
//
//    /* Tid.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<jardinsEnfants, String>, ObservableValue<String>>() {
//    @Override
//    public ObservableValue<String> call(TableColumn.CellDataFeatures<jardinsEnfants, String> param) {
//    return new SimpleStringProperty(param.getValue().getUser().getNom());
//    }
//    });
//    
//    tbprenomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CarteFidelite, String>, ObservableValue<String>>() {
//    @Override
//    public ObservableValue<String> call(TableColumn.CellDataFeatures<CarteFidelite, String> param) {
//    return new SimpleStringProperty(param.getValue().getUser().getPrenom());
//    }
//    });*/
//    
//    cinn.setCellValueFactory(new PropertyValueFactory<>("cin"));
//    nomm.setCellValueFactory(new PropertyValueFactory<>("nom"));
//    prenomm.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//    notee.setCellValueFactory(new PropertyValueFactory<>("note"));
//    motiff.setCellValueFactory(new PropertyValueFactory<>("motif"));
//    descc.setCellValueFactory(new PropertyValueFactory<>("description"));
//    datee.setCellValueFactory(new PropertyValueFactory<>("date"));
//}

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        
        
    
reclamation r = new reclamation();
        tableReclamation.getSelectionModel().getSelectedItem().getCinParent();

        reclamationService rs = new reclamationService();
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
        loader = new FXMLLoader(getClass().getResource("modifReclamation.fxml"));
        Parent root= loader.load();
        Tnom.getScene().setRoot(root);
        ModifReclamationController mrc = loader.getController();
        int cin = tableReclamation.getSelectionModel().getSelectedItem().getCinParent();
        String ca = Integer.toString(cin);
        mrc.setcinn(ca);
        mrc.setnomm(tableReclamation.getSelectionModel().getSelectedItem().getNomParent());
        mrc.setprenomm(tableReclamation.getSelectionModel().getSelectedItem().getPrenomParent());

        int note = tableReclamation.getSelectionModel().getSelectedItem().getNote();
        String caa = Integer.toString(note);
        mrc.setnotee(caa);
       
//        mjc.setTnum(tableJardin.getSelectionModel().getSelectedItem().getNum());
        mrc.setmotiff(tableReclamation.getSelectionModel().getSelectedItem().getType());
        mrc.setdescc(tableReclamation.getSelectionModel().getSelectedItem().getDescription());
    }

    @FXML
    private void supprimer(ActionEvent event) {
    
     reclamation r = (reclamation) tableReclamation.getSelectionModel().getSelectedItem();
        reclamationService rs = new reclamationService();
        rs.SupprimerReclamation(r.getCinParent());
        tableReclamation.getItems().removeAll();
                LoadData();
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