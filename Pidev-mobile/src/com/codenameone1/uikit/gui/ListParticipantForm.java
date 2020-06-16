/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codenameone1.uikit.services.ServiceParticipant;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Evenement;
import com.codenameone1.uikit.entities.Participant;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;


public class ListParticipantForm extends BaseForm{

    public ListParticipantForm(Resources res) {
        setTitle("List Participants");
        
        setLayout(BoxLayout.y());
           Toolbar tb = new Toolbar(true);
            setToolbar(tb);
                   super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
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
      /*  ArrayList<Participant> list;
        list=new ArrayList<>();
        list=ServiceParticipant.getInstance().getAllParticipants();
         Container c1=new Container(BoxLayout.y());

        for(Participant t:list){
           Label l=new Label("Nom :"+" "+t.getNom()+" "+"Prenom :"+" "+t.getPrenom()+" "+"Nom de l'evenement"+" "+t.getNomEvent()+" "+"Email :"+" "+t.getEmail()) ;
            c1.add(l);
        }
              addAll(c1);

       */
      
        ServiceParticipant sc = new ServiceParticipant();
              ArrayList<Participant> list = sc.getAllParticipants();

        
            for (Participant r : list) {
                int id = r.getId();

                Container c1 = new Container(BoxLayout.x());
                Container c3 = new Container(BoxLayout.y());
                Container c4 = new Container(BoxLayout.xCenter());
                   Button deletebtn = new Button("Delete your participation");
                deletebtn.addActionListener((evt) -> {
                   
                 if(new ServiceParticipant().SupprimerParticipant(id))
                 {
                    
                      ToastBar.showInfoMessage("participation deleted");
                    c1.remove();
                    c3.remove();
                    c4.remove();
                      deletebtn.remove();
                      
                                      
                    this.refreshTheme();
                 }else{
                    ToastBar.showErrorMessage("Erreur de suppression");
                }
               
            });

                //Button consult = new Button("Delete");
                c1.add(c3);
                c4.add(deletebtn);
               //    private int id,nbrParticipants,nbrPlaces;
   // private String nom,type,description;
   // private Date DateDebut,DateFin;
                SpanLabel s = new SpanLabel("Nom du Participant :"+r.getNom().toUpperCase());
                SpanLabel a = new SpanLabel("Prenom :"+r.getPrenom());
                SpanLabel b = new SpanLabel("Email :"+r.getEmail());
                SpanLabel c = new SpanLabel("Nom de l'evenement associé :"+r.getEvenement());
                
               
                s.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE));
                s.getAllStyles().setBgColor(0xF36B08);
                a.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                b.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                c.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                c3.add(s);
                c3.add(a);
                c3.add(b);
                c3.add(c);
                
                
              //  ShareButton share = new ShareButton();
                //share.setUIID("LoginButton");
                //share.getAllStyles().setBorder(Border.createRoundBorder(10, 5));             
                //c4.add(share);
                add(c1);
                add(c4);
                
            }
                    
     
                    
        
        
        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceParticipant.getInstance().getAllParticipants().toString());
      //  addAll(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
