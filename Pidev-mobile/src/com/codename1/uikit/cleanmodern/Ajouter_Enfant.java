/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codenameone1.uikit.gui.Acceuil;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codenameone1.uikit.entities.Enfant;
import com.codenameone1.uikit.services.ServiceEnfant;

/**
 *
 * @author bhk
 */
public class Ajouter_Enfant extends BaseForm{

    public Ajouter_Enfant(Resources res) {
           super(new BorderLayout());
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile Enfant");
        getContentPane().setScrollVisible(false);
        tb.addMaterialCommandToRightBar("Retour", FontImage.MATERIAL_BACKUP, e -> new Acceuil(res).show());
    
        super.addSideMenu(res);
        tb.setUIID("TextField1");
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un enfant");
      //  setLayout(BoxLayout.y());
      
       // add(BorderLayout.OVERLAY, new Label(res.getImage("copie.png") ,("LogoLabel")));
        TextField tfName = new TextField("","Nom");
          TextField tfPrenom = new TextField("","Prenom");
          Picker h= new Picker();
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Confirmer");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
   Enfant t = new Enfant( tfName.getText(),tfPrenom.getText(),h.getText());
   ServiceEnfant.getInstance().add(t);
                
                 
            }
        });
        btnValider.addActionListener(e -> new Consulter_Enfant(res).show());
        Container content = BoxLayout.encloseY(
                new FloatingHint(tfName),
                createLineSeparator(),
                new FloatingHint(tfPrenom),
                createLineSeparator(),
                h,
                createLineSeparator(),
                btnValider
        );        
        //content.setScrollableY(true);
        add( content);
        
      //  add(BorderLayout.south(tfName));
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
//                , e-> new NewsfeedForm(res).show()); // Revenir vers l'interface précédente
//                
    }
    
    
}
