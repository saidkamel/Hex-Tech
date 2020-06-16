/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Profilmedicale;
import com.codenameone1.uikit.services.ServiceProfil;
import java.util.ArrayList;

/**
 *
 * @author sa3do
 */
public class listProfil extends BaseForm {
   Form current;
   
       
         
   public listProfil(Resources res) {
       
       res = UIManager.initFirstTheme("/theme");

        setTitle("Subscription");

        setLayout(BoxLayout.y());
           Toolbar tb = new Toolbar(true);
            setToolbar(tb);
                   super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
          tb.addSearchCommand(e -> {});
          //Image im2 = res.getImage("l2.jpg");
           // ImageViewer img2 = new ImageViewer(im2);
            
       
        Image img = res.getImage("l2.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

              Label facebook = new Label("", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label("", "")),
                            twitter
                    )
                )
        ));
        ArrayList<Profilmedicale> list = new ArrayList<>();
        list=ServiceProfil.getInstance().getAllTasks();
        //sp.setText(ServiceProfil.getInstance().getAllTasks().toString());
       
         SpanLabel titre = new SpanLabel("Liste des Enfants: ");
         titre.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
         add(titre);
       
         for(Profilmedicale p:list)
        {
         
        //ImageViewer next = new ImageViewer(res.getImage("next.png"));
        Button next = new Button();
         Label nom = new Label();
         Label prenom = new Label();
         Container nomprenom = new Container(BoxLayout.x());
         nom.setText(p.getNomE());
         prenom.setText(p.getPrenomE());
            //System.out.println(p.getNomE());
           Label star = new Label("*");
           
           //nomprenom.add(star);
           nomprenom.add(next);
           nomprenom.add(nom);
           nomprenom.add(prenom);
           
            add(nomprenom);
           Label space = new Label("------------------------------------");
           add(space);
            int id=p.getId();
        DetailProfil D=new DetailProfil(res,id,current);
        
        nomprenom.addPointerPressedListener((evt1)->{
        
       
        D.show();
        
        });



        }
       
        
        
        //current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
