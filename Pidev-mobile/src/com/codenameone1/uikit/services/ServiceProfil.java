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
import com.codenameone1.uikit.entities.Profilmedicale;
import com.codenameone1.uikit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sa3do
 */
public class ServiceProfil {
    public ArrayList<Profilmedicale> tasks;
    
    public static ServiceProfil instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProfil() {
         req = new ConnectionRequest();
    }

    public static ServiceProfil getInstance() {
        if (instance == null) {
            instance = new ServiceProfil();
        }
        return instance;
    }

   

   public ArrayList<Profilmedicale> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Profilmedicale t = new Profilmedicale();
                float id = Float.parseFloat(obj.get("id").toString());
                
                t.setId((int)id);
                t.setTaille(((int)Float.parseFloat(obj.get("taille").toString())));
                t.setPoids(((int)Float.parseFloat(obj.get("poids").toString())));
                t.setEtat(obj.get("etat").toString());
                //t.setEnfant(obj.get("enfant")).toString();
                float idpediatre= Float.parseFloat(((Map)obj.get("idpediatre")).get("id").toString());
                String nomP= ((Map)obj.get("idpediatre")).get("nom").toString();
                String prenomP= ((Map)obj.get("idpediatre")).get("prenom").toString();
                String email= ((Map)obj.get("idpediatre")).get("email").toString();
                t.setEmailP(email);
                t.setNomP(nomP);
                t.setPrenomP(prenomP);
                String nomE= ((Map)obj.get("enfant")).get("nom").toString();
                String prenomE= ((Map)obj.get("enfant")).get("prenom").toString();
               
                t.setNomE(nomE);
                t.setPrenomE(prenomE);
                
                tasks.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Profilmedicale> getAllTasks(){
        String url = Statics.BASE_URL+"/profilmedicale/all";
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
    
    public ArrayList<Profilmedicale> getOne(String id){
        String url = Statics.BASE_URL+"/profilmedicale/getOne/"+id;
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
     //              ^
     //mochkla lehne |
    }
