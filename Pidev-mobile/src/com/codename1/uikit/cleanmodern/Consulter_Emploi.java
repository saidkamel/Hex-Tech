/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codenameone1.uikit.gui.Acceuil;
import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codenameone1.uikit.entities.EmploiDuTemps;
import com.codenameone1.uikit.entities.Enfant;
import com.codenameone1.uikit.services.ServiceEmploiDuTemps;
import com.codenameone1.uikit.services.ServiceEnfant;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class Consulter_Emploi extends BaseForm {

    public Consulter_Emploi(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Emploi Du Temps");
        getContentPane().setScrollVisible(false);
        tb.addMaterialCommandToRightBar("Retour", FontImage.MATERIAL_BACKUP, e -> new Acceuil(res).show());
    
        super.addSideMenu(res);
        tb.setUIID("TextField1");
 
        
        
        Image img = res.getImage("logo3.png");
        
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
                                new Label(res.getImage("profil1.png"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));
for (EmploiDuTemps ev : ServiceEmploiDuTemps.getInstance().getEmploiDuTemps()){

        Label EmploiDuTemps = new Label("");      
        addStringValue("Emploi Du Temps                         :", EmploiDuTemps);
        add( new Label(res.getImage("emp.PNG") ,("LogoLabel")));

        Label Classe = new Label("");
       Classe.setText(""+ev.getClasse());
        Classe.setUIID("TextField0");
        addStringValue("Classe                    :", Classe);              
}
   
    }
      
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }}

