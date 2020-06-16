/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.codenameone1.uikit.entities.Evenement;
import com.codenameone1.uikit.entities.Participant;
import com.codenameone1.uikit.services.ServiceParticipant;
import java.util.ArrayList;

public class AddParticipantForm extends BaseForm{
               private Resources theme;


    public AddParticipantForm(Resources res,int id) {
                theme = UIManager.initFirstTheme("/theme");

        setTitle("Add a new Participant");

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
        

//        Image img = res.getImage("learn.jpg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
//        ScaleImageLabel sl = new ScaleImageLabel(img);
//        sl.setUIID("BottomPad");
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        

    TextField tfNom = new TextField("","Nom");
        tfNom.setUIID("TextFieldBlack");
        
            TextField tfPrenom = new TextField("","Prenom");
        tfPrenom.setUIID("TextFieldBlack");
       
        TextField tfEmail = new TextField("nom.prenom@esprit.tn", "E-Mail", 20, TextField.EMAILADDR);
        tfEmail.setUIID("TextFieldBlack");
        
         
         //TextField tfEvent = new TextField("","Nom de l'evenement");
      //  tfEvent.setUIID("TextFieldBlack");

     
   
        
        
        
      //  TextField tfNom = new TextField("","Nom du Participant",20, TextArea.ANY);
        //TextField tfPrenom= new TextField("", "Prenom du Participant");
       // TextField tfEmail= new TextField("", "Mail du Participant");
       
      
       
       
       
        Button btnValider = new Button("Add Participant");
                addAll(tfNom,tfPrenom,tfEmail,btnValider);
                
                
                   ServiceParticipant sc = new ServiceParticipant();
              ArrayList<Evenement> list = sc.getAllEvents();
               
                for (int i = 0 ; i<list.size(); i++) {
               
               if(list.get(i).getId()==id)      
               {

               
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Participant p = new Participant(0,tfNom.getText(), tfPrenom.getText(),tfEmail.getText());
                        if( ServiceParticipant.getInstance().AddParticipant(p,id))
                            Dialog.show("Le participant","a été bien ajouté ",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
            
                
                
            }
        }); }
        
                
    }}

 

  
    
    
                }
