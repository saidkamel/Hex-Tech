/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codenameone1.uikit.entities.Enfant;
import com.codenameone1.uikit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceEnfant {
    
     public ArrayList<Enfant> Enfant;
    
    public static ServiceEnfant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEnfant() {
         req = new ConnectionRequest();
    }

    public static ServiceEnfant getInstance() {
        if (instance == null) {
            instance = new ServiceEnfant();
        }
        return instance;
    }

    public boolean add(Enfant t) {
        String url = Statics.BASE_URL + "/enfant/new?Nom="+t.getNom()+ "&Prenom="+ t.getPrenom()+ "&DateNaissance="+ t.getDateNaissance(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


      public ArrayList<Enfant> AfficherEnfant(String jsonText){
        try {
            Enfant=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                         
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
               
                //Création des tâches et récupération de leurs données
        
             Enfant t1 = new Enfant();    
             float idE = Float.parseFloat(obj.get("id").toString());
             t1.setId((int)idE);
             t1.setNom(obj.get("Nom").toString());
             t1.setPrenom(obj.get("Prenom").toString());
             t1.setDateNaissance(obj.get("DateNaissance").toString());
             
                //Ajouter la tâche extraite de la réponse Json à la liste
                Enfant.add(t1);
                
            }          
        } catch (IOException ex) {
            
        }

        return Enfant;
    }
      
         public ArrayList<Enfant> getEnfant(){
        String url = Statics.BASE_URL+"/enfantall";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Enfant = AfficherEnfant(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Enfant;
    }
         
                 public boolean editEnfant(Enfant t) {
        String url = Statics.BASE_URL + "/enfant/ModifierEnfant/"+t.getId()+"?Nom="+ t.getNom()+ "&Prenom="+ t.getPrenom()+ "&DateNaissance="+ t.getDateNaissance();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
                 
                   public boolean DeleteEnfant(Enfant t) {
        String url = Statics.BASE_URL + "/enfant/DeleteEnfant/"+t.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
                   
}
