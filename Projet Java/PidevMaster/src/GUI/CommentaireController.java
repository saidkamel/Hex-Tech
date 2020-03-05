/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.commentaire;
import Entities.reclamation;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Service.commentaireService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CommentaireController implements Initializable {

    @FXML
    private ListView<String> lstCommentaire;
    @FXML
    private TextField tfCommentaire;
    @FXML
    private Button btnSub;
         @FXML
    private ImageView imghome;
    @FXML
    private TextField tfnom;


    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commentaireService cs=new commentaireService();
        List <commentaire> list=new ArrayList<>();
        list=cs.getlistcommentaire();
      //
      List<String> lstS=new ArrayList<>();
      for (int i=0;i<list.size();i++){   
            lstS.add(" Mr/M "+list.get(i).getNom()+" commentaire ="+list.get(i).getCommentaire());
        }
        ObservableList<String> listC = FXCollections.observableArrayList(lstS);
lstCommentaire.setItems(listC);

    }    

    @FXML
    private void upCommentaire(ActionEvent event) {
        commentaireService cs=new commentaireService();
        commentaire c=new commentaire(tfnom.getText(), "", 0, "",tfCommentaire.getText());
        cs.ajouterCom(c);
        List <commentaire> list=new ArrayList<>();
        list=cs.getlistcommentaire();
      List<String> lstS=new ArrayList<>();
      for (int i=0;i<list.size();i++){   
            lstS.add(" Mr/M "+list.get(i).getNom()+" commentaire ="+list.get(i).getCommentaire());
        }
        ObservableList<String> listC = FXCollections.observableArrayList(lstS);
lstCommentaire.setItems(listC);
       
    }
    
       @FXML
    private void gohome(MouseEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontParent.fxml"));
            Parent root = loader.load();
          
            imghome.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(JardinEnfantsAjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
