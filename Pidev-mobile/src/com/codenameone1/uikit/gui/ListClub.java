/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Command;
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
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Club;
import com.codenameone1.uikit.entities.Subscription;
import com.codenameone1.uikit.entities.photo;
import com.codenameone1.uikit.services.ServiceClub;
import com.codenameone1.uikit.services.ServiceMedia;
import com.codenameone1.uikit.utils.UserSession;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author mekki
 */
public class ListClub extends BaseForm{
private Resources theme;
    public ListClub(Resources res) {        
        setTitle("List Club");
        
        
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
        ServiceMedia sm = new ServiceMedia();
        ServiceClub sc = new ServiceClub();
        ArrayList<Club> list = sc.getAllClubs();

        
            for (Club r : list) {

                Container c1 = new Container(BoxLayout.x());
                Container c3 = new Container(BoxLayout.y());
                Container c4 = new Container(BoxLayout.xCenter());
                
                Button subscribe = new Button("Subscribe");
                subscribe.addActionListener(new ActionListener() {
                   
                   
                    
                    
                public void actionPerformed(ActionEvent ev) {
                  new SubscribeForm(res,r.getId()).show();
        // button was clicked, you can do anything you want here...
    }
});
               
         
                        
                   
                    
                 
                 
          
                        
                    
                    
                c1.add(c3);
                c4.add(subscribe);
                SpanLabel s = new SpanLabel(r.getNom().toUpperCase());
                SpanLabel k = new SpanLabel("Description: " + r.getDescription());
                s.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE));
                s.getAllStyles().setBgColor(0xF36B08);
                k.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
                c3.add(s);
                c3.add(k);
                ShareButton share = new ShareButton();
                share.setUIID("LoginButton");
                share.getAllStyles().setBorder(Border.createRoundBorder(5, 5));             
                c4.add(share);
                add(c1);
                add(c4);
                
                }}}
                    
        

    
    

