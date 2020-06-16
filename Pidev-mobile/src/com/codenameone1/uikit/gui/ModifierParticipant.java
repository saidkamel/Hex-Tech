

package com.codenameone1.uikit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
import com.codenameone1.uikit.entities.Participant;
import com.codenameone1.uikit.services.ServiceParticipant;


/**
 *
 * @author ferie
 */
public class ModifierParticipant extends BaseForm {
               private Resources theme;

    public ModifierParticipant(Resources res) {
         setTitle("Modifier un participant");
        setLayout(BoxLayout.y());
        
           Toolbar tb = new Toolbar(true);
            setToolbar(tb);
                   super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
          

        theme = UIManager.initFirstTheme("/theme");
            //Image im = theme.getImage("im age.jpg");
            //ImageViewer img = new ImageViewer(im);
          theme = UIManager.initFirstTheme("/theme");
          
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
      
            
  TextField tfid = new TextField("","ID");
        tfid.setUIID("TextFieldBlack");

             TextField tfnom = new TextField("","Nom");
        tfnom.setUIID("TextFieldBlack");
            TextField tfprenom = new TextField("","Prenom");
        tfprenom.setUIID("TextFieldBlack");

        TextField tfmail = new TextField("nom.prenom@esprit.tn", "E-Mail", 20, TextField.EMAILADDR);
        tfmail.setUIID("TextFieldBlack");

        
        Button btnModif = new Button("Modifier");
        
        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfid.getText().length()==0)||(tfnom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                            Participant t = new Participant(Integer.parseInt(tfid.getText()),tfnom.getText(),tfprenom.getText(),tfmail.getText());

                        if( ServiceParticipant.getInstance().ModifierParticipant(t))
                            Dialog.show("Succès","Votre candidature a été modifiée",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfid,tfnom,tfprenom,tfmail,btnModif);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> p.showBack());
                
    }
        
        
}
