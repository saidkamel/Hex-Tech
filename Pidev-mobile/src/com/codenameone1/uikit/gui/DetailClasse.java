/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
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
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Activite;
import com.codenameone1.uikit.entities.classe;
import com.codenameone1.uikit.services.ServiceClasseActivite;
import java.util.ArrayList;

/**
 *
 * @author alaka
 */
public class DetailClasse extends BaseForm{
    public  DetailClasse(Resources res, float id)
    {
    setTitle("Detail Activités");
        
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
        
        
        ArrayList<classe> list = new ArrayList<>();
        list=ServiceClasseActivite.getInstance().getAllClasse();
        //sp.setText(ServiceParticipant.getInstance().getAllActivite().toString());
        //sp.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        
        for(classe p:list)
        {
            if(p.getId()==id)
            {
        SpanLabel titre = new SpanLabel("Detail classe: "+p.getNomClasse());
        titre.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        add(titre);
       /* Label nomC = new Label();
        nomC.setText("Classe: "+p.getNomClasse());
        add(nomC);*/
        Label nbActivite = new Label();
        nbActivite.setText("Nombre d'activite: "+String.valueOf(p.getListActivite1().size()));
        add(nbActivite);
       
        ArrayList<Activite> list1 = p.getListActivite1();
        int j = 0;
        for(Activite a:list1){
        Label ac= new Label("Activite "+String.valueOf(j+1));
        Label nomactivite = new Label();
        Label desc = new Label();
        Label type = new Label();
        nomactivite.setText("Nom: "+a.getNom().toString());
        desc.setText("Description: "+a.getDescription().toString());
        type.setText("Type: "+a.getType().toString());
        add(nomactivite).add(desc).add(type);
        j++;
        }
        
        
        }}
        
    
    }
    
}
