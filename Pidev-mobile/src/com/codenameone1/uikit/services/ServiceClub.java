/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.services;


import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codenameone1.uikit.utils.Statics;
import com.codenameone1.uikit.entities.Club;
import com.codenameone1.uikit.entities.Subscription;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mekki
 */
public class ServiceClub {

    public ArrayList<Club> tasks;
    public ArrayList<Subscription> subscription;
    public Map<String, Object> h = null;
    
    public static ServiceClub instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceClub() {
         req = new ConnectionRequest();
    }

    public static ServiceClub getInstance() {
        if (instance == null) {
            instance = new ServiceClub();
        }
        return instance;
    }

    public boolean addClub(Subscription i,int id) {
        String url = Statics.BASE_URL + "/tasks/add/" + id + "?NomEnfant=" + i.getNomEnfant() ; 
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


        public ArrayList<Club> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Club c = new Club();
                float id = Float.parseFloat(obj.get("id").toString());
            c.setId((int)id);
                c.setNom(obj.get("nom").toString());
                c.setDescription(obj.get("description").toString());
                tasks.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Club> getAllClubs(){
        String url = Statics.BASE_URL+"/tasks";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    public boolean Supprimer(int id ) {
         
        String url = Statics.BASE_URL + "/tasks/deleteuser/"+ id;
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
    public ArrayList<Subscription> parseClubs(String jsonText){
  
        try {
            subscription=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
           List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Subscription s = new Subscription();
                
                 int id = (int)Float.parseFloat(obj.get("id").toString());
            s.setId((int)id);
                String nomEnfant = obj.get("nomEnfant").toString();
               float idclub = Float.parseFloat(((Map)obj.get("Club")).get("id").toString());
               String nomC= ((Map)obj.get("Club")).get("nom").toString();
               s.setNomC(nomC);
               
                subscription.add(new Subscription(id, nomEnfant, nomC));
            }
            
            
            
        } catch (IOException ex) {
            
        }
        return subscription;
    } 

    
    
    public ArrayList<Subscription> getMyClubs(){
        String url = Statics.BASE_URL+"/tasks/showallsub";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                subscription = parseClubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return subscription;
    }
    
    
    
    
    
    
    
    
    
    
    
 /*   public Map<String, Object> getResponse(String url) {
        url = "http://127.0.0.1:8000" + url;
        System.out.println(url);
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(url);
        //r.setPost(false);

        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);

                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                h = p.parseJSON(targetReader);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h;
    } */
}

