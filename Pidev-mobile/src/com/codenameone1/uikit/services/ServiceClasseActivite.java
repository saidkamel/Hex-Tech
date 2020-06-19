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
import com.codenameone1.uikit.entities.Activite;
import com.codenameone1.uikit.entities.classe;
import com.codenameone1.uikit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceClasseActivite {

    public ArrayList<classe> classes;
    public ArrayList<Activite> activites;
    
    public static ServiceClasseActivite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceClasseActivite() {
         req = new ConnectionRequest();
    }

    public static ServiceClasseActivite getInstance() {
        if (instance == null) {
            instance = new ServiceClasseActivite();
        }
        return instance;
    }

    public boolean AddParticipant(classe p) {
        String urll = Statics.BASE_URL + "/new?nomP=" + p.getNomClasse() ;

        req.setUrl(urll);
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
 
    

    public ArrayList<classe> parseClasse(String jsonText){
        try {
            classes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> participantListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)participantListJson.get("root");
            for(Map<String,Object> obj : list){
                classe p = new classe();
                float id = Float.parseFloat(obj.get("idclasse").toString());
                p.setId((int)id);
                p.setNomClasse(obj.get("nomclasse").toString());
                //p.setActivite(obj.get("activite").toString());
               List<Map<String,Object>> list1 = (List<Map<String,Object>>)obj.get("activite");
              for(Map<String,Object> obj1 : list1)
              {
              float idactivite = Float.parseFloat(obj1.get("idactivite").toString());
              String nomActivite = obj1.get("nomactivite").toString();
              String desc = obj1.get("description").toString();
              String type = obj1.get("type").toString();
                  Activite A = new Activite(idactivite,nomActivite,desc,type);
                 p.addList(A);
              
              }
                classes.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return classes;
    }
    
    
     public boolean SupprimerParticipant(classe p ) {
         
        String url = Statics.BASE_URL + "/delete?id=" + p.getId()  ;
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
    
    
  
      
     public boolean ModifierParticipant(classe p ) {
     String url = Statics.BASE_URL + "/ModifieP/"+ p.getId()+"?"+"nomP=" + p.getNomClasse() ; 
           //String url = Statics.BASE_URL + "/participant/edit?id=" + p.getId() +"&nomP=" + p.getNom() + "&Prenom="+p.getPrenom()+ "&Email=" + p.getEmail(); 

         
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
    
    public ArrayList<classe> getAllClasse(){
        String url = Statics.BASE_URL+"/showclasseM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                classes = parseClasse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return classes;
    }
    
    
    
    
    
    public ArrayList<Activite> parseActivite(String jsonText){
        try {
            activites=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> participantListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)participantListJson.get("root");
            for(Map<String,Object> obj : list){
                Activite e = new Activite();
                float id = Float.parseFloat(obj.get("idactivite").toString());
                e.setId((int)id);
                e.setNom(obj.get("nomactivite").toString());
                e.setDescription(obj.get("description").toString());
                e.setType(obj.get("type").toString());
                
                activites.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
        return activites;
    }
    
     public ArrayList<Activite> getAllActivite(){
        String url = Statics.BASE_URL+"/showclasseA";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                activites = parseActivite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return activites;
    }
   
    
    
}
