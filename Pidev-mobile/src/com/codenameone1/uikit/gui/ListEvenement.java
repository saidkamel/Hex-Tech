/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Log;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codenameone1.uikit.services.ServiceParticipant;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Evenement;
import com.codenameone1.uikit.entities.Participant;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;



public class ListEvenement extends BaseForm{
    public ListEvenement(Resources res) {

        setTitle("List Events");
        
        setLayout(BoxLayout.y());
           Toolbar tb = new Toolbar(true);
            setToolbar(tb);
                   super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
           Image img = res.getImage("l2.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
    Button btn = new Button();
    btn.addActionListener((ActionListener) (ActionEvent evt) -> {
            
        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
         m.sendMessageViaCloud("Codename One", "", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");  
        });
        
        
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
        
        /*   ArrayList<Evenement> l;
        l=new ArrayList<>();
        l=ServiceParticipant.getInstance().getAllEvents();
         Container c2=new Container(BoxLayout.y());

        for(Evenement t:l){
           Label ll=new Label("Nom :"+" "+t.getNom()+" "+"Type :"+" "+t.getType()+
                   " "+"DateDebut: "+" "+t.getDateDebut()+" "+"DateFin:  :"+" "+t.getDateFin()+" "+"Description: "+" "+t.getDescription()+" "+
                   " Nombre de participants: "+" "+t.getNbrParticipants()+" "+"Nombre de palaces : "+" "+t.getNbrPlaces());
            c2.add(ll);
        }
              addAll(c2);
          
    }*/
              
                  ServiceParticipant sc = new ServiceParticipant();
              ArrayList<Evenement> list = sc.getAllEvents();

        
            for (Evenement r : list) {

                Container c1 = new Container(BoxLayout.x());
                Container c3 = new Container(BoxLayout.y());
                Container c4 = new Container(BoxLayout.xCenter());

                Button consult = new Button("Participate");
                c1.add(c3);
                c4.add(consult);
                consult.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                      new AddParticipantForm(res,r.getId()).show();
                    }
                });
               //    private int id,nbrParticipants,nbrPlaces;
   // private String nom,type,description;
   // private Date DateDebut,DateFin;
                SpanLabel s = new SpanLabel("Nom de l'evenement :"+r.getNom().toUpperCase());
                SpanLabel a = new SpanLabel("Type :"+r.getType());
                SpanLabel b = new SpanLabel("Date de Debut :"+r.getDateDebut());
                SpanLabel c = new SpanLabel("Date de Fin :"+r.getDateFin());
                SpanLabel d = new SpanLabel("Description :"+r.getDescription());
                SpanLabel e = new SpanLabel("nombre de participants :"+r.getNbrParticipants());
                SpanLabel k = new SpanLabel("nombre de places: " + r.getNbrPlaces());
                
              
                s.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE));
                s.getAllStyles().setBgColor(0xF36B08);
                k.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                a.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                b.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                c.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));k.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                d.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                e.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                c3.add(s);
                c3.add(a);
                c3.add(d);
                c3.add(e);
                c3.add(k);
               // c3.add(b);
               // c3.add(c);
                
                
              //  ShareButton share = new ShareButton();
              //  share.setUIID("LoginButton");
             //   share.getAllStyles().setBorder(Border.createRoundBorder(10, 5));             
                //c4.add(share);
                add(c1);
                add(c4);
                
            }
        
              
              
              
              
              
              
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    
    
}
}