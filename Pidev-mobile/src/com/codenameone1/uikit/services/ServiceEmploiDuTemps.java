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
import com.codenameone1.uikit.entities.EmploiDuTemps;
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
public class ServiceEmploiDuTemps {
    
     public ArrayList<EmploiDuTemps> EmploiDuTemps;
    
    public static ServiceEmploiDuTemps instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEmploiDuTemps() {
         req = new ConnectionRequest();
    }

    public static ServiceEmploiDuTemps getInstance() {
        if (instance == null) {
            instance = new ServiceEmploiDuTemps();
        }
        return instance;
    }
  
      public ArrayList<EmploiDuTemps> AfficherEmploiDuTemps(String jsonText){
        try {
            EmploiDuTemps=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                         
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
               
                //Création des tâches et récupération de leurs données
        
             EmploiDuTemps t1 = new EmploiDuTemps();    
             float idE = Float.parseFloat(obj.get("id").toString());
             t1.setId((int)idE);
             t1.setImage(obj.get("img").toString());
             t1.setClasse(obj.get("Classe").toString());
                      
                //Ajouter la tâche extraite de la réponse Json à la liste
                EmploiDuTemps.add(t1);
                
            }          
        } catch (IOException ex) {
            
        }

        return EmploiDuTemps;
    }
      
         public ArrayList<EmploiDuTemps> getEmploiDuTemps(){
        String url = Statics.BASE_URL+"/tableEmm";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EmploiDuTemps = AfficherEmploiDuTemps(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return EmploiDuTemps;
    }
                           
}
