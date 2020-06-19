/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codenameone1.uikit.services.ServiceClasseActivite;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Activite;
import com.codenameone1.uikit.entities.classe;
import java.util.ArrayList;


public class ListClasse extends BaseForm{

    public ListClasse(Resources res) {
        setTitle("List classes");
        
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
       /* SpanLabel sp = new SpanLabel();
        sp.setText(ServiceParticipant.getInstance().getAllClasse().toString());
        addAll(sp);*/
        
        SpanLabel titre = new SpanLabel("Classe: ");
        titre.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        add(titre);
        ArrayList<classe> list = new ArrayList<>();
        list=ServiceClasseActivite.getInstance().getAllClasse();
        //sp.setText(ServiceParticipant.getInstance().getAllActivite().toString());
        //sp.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        int i =0;
        for(classe p:list)
        {
            
        Label nomC = new Label();
        nomC.setText(String.valueOf(i+1)+"."+p.getNomClasse());
        addAll(nomC);
       
       
        
        float id=p.getId();
        DetailClasse D = new DetailClasse(res,id);
        nomC.addPointerPressedListener((evt1)->{
       
        D.show();
        
        });
        i++;
        }
        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
