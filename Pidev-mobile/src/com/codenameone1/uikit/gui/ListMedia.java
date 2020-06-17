/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.photo;
import com.codenameone1.uikit.services.ServiceMedia;
import java.util.ArrayList;

/**
 *
 * @author mekki
 */
public class ListMedia extends BaseForm {
     public ListMedia(Resources res ) throws ParseException {        
        setTitle("List Club");
        ServiceMedia sm = new ServiceMedia();
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
              EncodedImage enc ;
        ArrayList <photo> list = sm.getAllphoto();
        
        for (int i = 0; i<list.size();i++){
            
            Container c1 = new Container();
            


 ImageViewer iv = new ImageViewer();
            System.out.println(list.get(i).getImg());
            Image placeholder = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + list.get(i).getImg(),
            "http://127.0.0.1/PIWEB/web/img"+ list.get(i).getImg()));
            
              ImageViewer iv1 = new ImageViewer();
            System.out.println(list.get(i).getImg1());
            Image placeholder1 = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
            EncodedImage encImage1 = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage1, "file" + list.get(i).getImg1(),
            "http://127.0.0.1/PIWEB/web/img"+ list.get(i).getImg1()));
            
             ImageViewer iv2 = new ImageViewer();
            System.out.println(list.get(i).getImg2());
            Image placeholder2 = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
            EncodedImage encImage2 = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img2 = new ImageViewer(URLImage.createToStorage(encImage2, "file" + list.get(i).getImg2(),
            "http://127.0.0.1/PIWEB/web/img"+ list.get(i).getImg2()));
            
            ImageViewer iv3 = new ImageViewer();
            System.out.println(list.get(i).getImg3());
            Image placeholder3 = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
            EncodedImage encImage3 = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img3 = new ImageViewer(URLImage.createToStorage(encImage3, "file" + list.get(i).getImg3(),
            "http://127.0.0.1/PIWEB/web/img"+ list.get(i).getImg3()));
            
            c1.add(img);
            c1.add(img1);
            c1.add(img2);
            c1.add(img3);
            
            
            
        }
 
        
        
        
}

  
}
