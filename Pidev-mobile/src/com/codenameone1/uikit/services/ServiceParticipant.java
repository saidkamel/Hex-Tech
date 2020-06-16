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
import com.codenameone1.uikit.entities.Evenement;
import com.codenameone1.uikit.entities.Participant;
import com.codenameone1.uikit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceParticipant {

    public ArrayList<Participant> participants;
    public ArrayList<Evenement> events;
    
    public static ServiceParticipant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceParticipant() {
         req = new ConnectionRequest();
    }

    public static ServiceParticipant getInstance() {
        if (instance == null) {
            instance = new ServiceParticipant();
        }
        return instance;
    }

    public boolean AddParticipant(Participant p,int id) {
        String urll = Statics.BASE_URL + "/new/"+id+"?nomP="+ p.getNom() + "&prenom=" + p.getPrenom() + "&Email="+ p.getEmail() ;

        req.setUrl(urll);
        req.addResponseListener(new ActionListener<NetworkEvent>() {  
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);  //supprimé l'ecouteur
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); 
         
        return resultOK;
    }
 
    

    public ArrayList<Participant> parseParticipant(String jsonText){
        try {
            participants=new ArrayList<>();
            
            JSONParser j = new JSONParser(); 
            Map<String,Object> participantListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)participantListJson.get("root");
            for(Map<String,Object> obj : list){
                Participant p = new Participant();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNom(obj.get("nomP").toString());
                p.setPrenom(obj.get("prenom").toString());
                p.setEmail(obj.get("Email").toString());
//                p.setEvenement(obj.get("evenement").toString());
//               float evenement=Float.parseFloat(((Map)obj.get("evenement")).get("id").toString());
              String event= ((Map)obj.get("evenement")).get("nomE").toString();
              p.setEvenement(event);
            
               participants.add(p);
               
            }
            
            
        } catch (IOException ex) {
            
        }
        return participants;
    }
    
    
     public boolean SupprimerParticipant(int id ) {
         
        String url = Statics.BASE_URL + "/delete?id=" + id;
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
    
    
  
      
     public boolean ModifierParticipant(Participant p ) {
     String url = Statics.BASE_URL + "/ModifieP/"+ p.getId()+"?nomP=" + p.getNom() + "&prenom="+p.getPrenom()+ "&Email=" + p.getEmail(); 
           //String url = Statics.BASE_URL + "/participant/edit?id=" + p.getId() +"&nomP=" + p.getNom() + "&Prenom="+p.getPrenom()+ "&Email=" + p.getEmail(); 
                                         //ModifieP/112?nomP=s&prenom=p&Email=e
         
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
    
    public ArrayList<Participant> getAllParticipants(){
        String url = Statics.BASE_URL+"/participant/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participants = parseParticipant(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participants;
    }
    
    
    
    
    public ArrayList<Evenement> parseEvenement(String jsonText){
        try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> participantListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)participantListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement p = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNom(obj.get("nomE").toString());
                p.setType(obj.get("type").toString());
                p.setDateDebut(p.getDateDebut());
                p.setDateFin(p.getDateFin());
                p.setDescription(obj.get("description").toString());
                p.setNbrParticipants(((int)Float.parseFloat(obj.get("nbrParticipants").toString())));
                p.setNbrPlaces(((int)Float.parseFloat(obj.get("nbrPlaces").toString())));
                events.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return events;
    }
    
     public ArrayList<Evenement> getAllEvents(){
        String url = Statics.BASE_URL+"/evenement/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvenement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
   
     
     
     
    
    
}
