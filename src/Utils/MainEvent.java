/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Services.EvenementService;
import Services.ParticipantService;
import Entitiy.evenement;
import Entitiy.participant;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 *
 * @author mondh
 */
public class MainEvent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
            //SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String date_Debut = "2020-02-21";
            String date_Fin = "2020-02-22";
            
            
            
        Connexion c1 = Connexion.getInstance();
        evenement e = new evenement();
        evenement e1 = new evenement("Event1","Loisir",Date.valueOf(date_Debut),Date.valueOf(date_Fin),"Desc",2);
        evenement e2 = new evenement("Event2","Activity",Date.valueOf(date_Debut),Date.valueOf(date_Fin),"Description",3);
        evenement e3 = new evenement("Event3","Education",Date.valueOf(date_Debut),Date.valueOf(date_Fin),"balblablabla",5);

      
         EvenementService es = new EvenementService();
         //es.SupprimerEvenement(e3);
         
      
        //es.modifierEvenement(25,"Event1","Loisir----------------",Date.valueOf(date_Debut),Date.valueOf(date_Fin),"Desccript",10);
       //es.AjouterEvenement(e1);
       //  es.AjouterEvenement(e3);
        
       // System.out.println(es.rechercherEvenement("Nom","Event1"));
      //  System.out.println(es.trieEvenement("Nom"));
    // System.out.println(es.getListEvenement());
    System.out.println(es.AffecterParticipants());
         
      
        //-----------------------------------------------------------------------------------------------
         
         participant p1= new participant("Mondher","Mallek","123");
         ParticipantService ps = new ParticipantService();
         es.ParticipantsNumber("aa");
           //ps.AjouterParticipant(p1);
         //ps.modifierParticipant(1,"Mallek","Mondher-----------------","mot de pass");
        // ps.SupprimerParticipant(p1);
      //  System.out.println(ps.getListParticipant());
       //  ps.AffectParticipantInEvent(p1, e1);
        // System.out.println(es.RetournerNombreParticipant());
        
    }
    
}

