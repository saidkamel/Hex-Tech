/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Club;
import com.codenameone1.uikit.entities.Subscription;
import com.codenameone1.uikit.services.ServiceClub;
import com.codenameone1.uikit.services.ServiceMedia;
import java.util.ArrayList;


/**
 *
 * @author mekki
 */
 public class SubscribeForm extends BaseForm{
    private Resources theme;

    public SubscribeForm(Resources res,int id) {
        theme = UIManager.initFirstTheme("/theme");

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
        

        ServiceMedia sm = new ServiceMedia();
        ServiceClub sc = new ServiceClub();
        ArrayList<Club> list = sc.getAllClubs();

        TextField tfNom = new TextField("","NomEnfant");
        tfNom.setUIID("TextFieldBlack");
        Button btnValider = new Button("Add Child");
        addAll(tfNom,btnValider);
            for (int i = 0 ; i<list.size(); i++) {
               
               if(list.get(i).getId()==id)
                   
               {
                

            
        
            
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { 
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Subscription p = new Subscription(tfNom.getText() );
                        if( ServiceClub.getInstance().addClub(p,id))
                            Dialog.show("L'enfant","a été inscrit ",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }  
            
                
            } 
        }); }
            }
    
    
    
    
    }
        
                
    }
    
    

