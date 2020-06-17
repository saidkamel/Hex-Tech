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
import com.codename1.media.Media;
import com.codename1.ui.events.ActionListener;
import com.codenameone1.uikit.entities.photo;
import com.codenameone1.uikit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mekki
 */
public class ServiceMedia {
     public ArrayList<photo> tasks;
   
    public Map<String, Object> h = null;
    
    public static ServiceMedia instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceMedia() {
         req = new ConnectionRequest();
    }

    public static ServiceMedia getInstance() {
        if (instance == null) {
            instance = new ServiceMedia();
        }
        return instance;
    }
    public ArrayList<photo> parsephoto(String jsonText){
  
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            photo m = new photo();
           
           List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String img=obj.get("img").toString();
                String img1=obj.get("img1").toString();
                String img2=obj.get("img2").toString();
                String img3=obj.get("img3").toString();
                
                tasks.add(new photo(id, img, img1, img2, img3));
                
                
               
                
           
                
          
            }
            
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    } 
    public ArrayList<photo> getAllphoto(){
        String url = Statics.BASE_URL+"/tasks/showall/"  ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parsephoto(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    } 
    
}
