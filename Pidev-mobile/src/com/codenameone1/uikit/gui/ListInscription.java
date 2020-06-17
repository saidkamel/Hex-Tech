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
import static com.codename1.ui.Component.BOTTOM;
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
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Club;
import com.codenameone1.uikit.entities.Subscription;
import com.codenameone1.uikit.services.ServiceClub;
import java.util.ArrayList;

/**
 *
 * @author mekki
 */
public class ListInscription extends BaseForm{
private Resources theme;
    public ListInscription(Resources res) {
        
     
        
        setTitle("Mes Club");
        
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
        ServiceClub sc = new ServiceClub();
        ArrayList<Subscription> list = sc.getMyClubs();

        
            for (Subscription r : list) {
                int id= r.getId();
                System.out.println(id);    
                    
               

                Container c1 = new Container(BoxLayout.x());
                Container c3 = new Container(BoxLayout.y());
                Container c4 = new Container(BoxLayout.xCenter());

                Button consult = new Button("Unsubscribe");
                consult.addActionListener((evt) -> {
                
                   
                 if(new ServiceClub().Supprimer(id))
                 {
                    
                      ToastBar.showInfoMessage("Vous êtes désinscrit");
                    c1.remove();
                    c3.remove();
                    c4.remove();
                    
                    consult.remove();
                  
                    this.refreshTheme();
                 }else{
                    ToastBar.showErrorMessage("Erreur de suppression");
                }
               
            });
                c1.add(c3);
                c4.add(consult);
                SpanLabel s = new SpanLabel("Enfant: " +r.getNomEnfant());
                SpanLabel k = new SpanLabel("Club: " + r.getNomC().toUpperCase());
                s.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                s.getAllStyles().setBgColor(0xF36B08);
                k.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
                c3.add(s);
                c3.add(k);
                add(c1);
                add(c4);
                
            }
        }}  
        
        
    
    
    

