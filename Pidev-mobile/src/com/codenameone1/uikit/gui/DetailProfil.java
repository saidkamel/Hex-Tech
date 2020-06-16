/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codenameone1.uikit.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.codenameone1.uikit.entities.Profilmedicale;
import com.codenameone1.uikit.services.ServiceProfil;
import java.util.ArrayList;

/**
 *
 * @author sa3do
 */
public class DetailProfil extends BaseForm {

    public DetailProfil(Resources res, int id, Form previous) {
        res = UIManager.initFirstTheme("/theme");
        
        setTitle("Subscription");

        setLayout(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        
        super.addSideMenu(res);
        
        //this.getToolbar().addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_ARROW_BACK , ev->previous.showBack() );
                
        //Image im2 = res.getImage("l2.jpg");
        // ImageViewer img2 = new ImageViewer(im2);

        Image img = res.getImage("l2.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
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

        ArrayList<Profilmedicale> list = new ArrayList<>();
        String idString = String.valueOf(id);
        Label lb = new Label(idString);
        list = ServiceProfil.getInstance().getAllTasks();
        TextField SendTo = new TextField();
        for (Profilmedicale p : list) {
            if (p.getId() == id) {
                SpanLabel nom = new SpanLabel();
                SpanLabel prenom = new SpanLabel();
                
                Container nomprenom = new Container(BoxLayout.x());
                nom.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                nom.setText(p.getNomE());
                prenom.setText(p.getPrenomE());
                prenom.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                nomprenom.add(nom);
                nomprenom.add(prenom);
                add(nomprenom);
                Container Ctaille = new Container(BoxLayout.x());
                Container Cpoids = new Container(BoxLayout.x());
                Container CIMC = new Container(BoxLayout.x());
                Container Cdescription = new Container(BoxLayout.y());
                SpanLabel lbDescription = new SpanLabel("Description: ");
                SpanLabel lbTaille = new SpanLabel("Taille: ");
                SpanLabel lbPoids = new SpanLabel("Poids: ");
                SpanLabel lbIMC = new SpanLabel("IMC: ");
                Label taille = new Label(String.valueOf(p.getTaille())+" CM");
                Label poids = new Label(String.valueOf(p.getPoids())+"KG");
                //poids/((enfant.profilmedicale.taille*0.01)*(enfant.profilmedicale.taille*0.01)
                double imc1 = p.getPoids()/((p.getTaille()*0.01)*(p.getTaille()*0.01));
                Label imc = new Label(String.valueOf(imc1));
                SpanLabel description = new SpanLabel();
                
                if(imc1<=18.5)
                {
                imc.setText(String.valueOf(imc1)+"-->Insuffisance ponderale");
                description.setText("Votre poids apparaît très insuffisant en regard de votre taille. Cette maigreur excessive peut être la conséquence d’une maladie ou de troubles du comportement alimentaire, et elle peut aussi être elle-même à l'origine d'autres maladies. Il est utile que vous consultiez un médecin afin qu'il constate l'ampleur de l'insuffisance pondérale et en recherche la cause éventuelle. Il pourra également vous faire une proposition de traitement si nécessaire.");
                }
                else if(imc1>18.5 && imc1 <= 25)
                {
                imc.setText(String.valueOf(imc1)+"-->Corpulence Normale");
                
  
                description.setText("vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré, à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier la notion de plaisir bien sûr !");
                }
                else if(imc1>25 && imc1 <= 30 )
                {
                imc.setText(String.valueOf(imc1)+"-->Surpoids");
                description.setText("Votre poids apparaît modérément excessif compte tenu de votre taille. Ce surpoids peut augmenter votre risque de maladies, en particulier de troubles cardiaques ou vasculaires et de diabète. Ce risque peut se cumuler avec d'autres, provoqués par le tabagisme, l'hypertension ou le cholestérol.\n" +
"Si vous présentez déjà un de ces facteurs de risque, une perte de poids vous sera bénéfique. Une alimentation moins riche en graisses, davantage de fruits et de légumes et une activité physique régulière vous permettront sans doute de retrouver un poids idéal. Votre médecin sera le mieux à même de vous donner les conseils adaptés pour atteindre cet objectif.\n" );
                }
                else if(imc1>30 && imc1 <= 35 )
                {
                imc.setText(String.valueOf(imc1)+"-->Obésité modérée");
                description.setText("Votre poids est beaucoup trop important compte tenu de votre taille. Vous souffrez d'obésité, une obésité dite 'modérée' ou de stade 1. Cela signifie que vous êtes exposé à un risque non négligeable d’être victime de maladies cardiaques (infarctus), vasculaires (accident vasculaire cérébral, insuffisance veineuse) ou métaboliques (diabète).\n" +
"        Cette obésité peut aussi être à l'origine d'essoufflement, de fatigue, de douleurs dorsales ou articulaires et difficultés psychologiques qui perturbent sérieusement vos activités quotidiennes. Heureusement, même une perte de poids modérée (5 à 10 %) peut avoir un effet positif sur votre santé et votre mental, à condition bien sûr de ne pas reprendre les kilos perdus.\n" +
"        Dans cette optique, il serait judicieux de pratiquer tous les jours un peu plus d'activité physique et de réduire la part des graisses dans votre alimentation. En tous cas, une consultation avec votre médecin s'impose, pour qu'il établisse un bilan et envisage avec vous les méthodes de perte de poids les plus adaptées, ainsi que les éventuels traitements possibles.\" \n");
                }
                else if(imc1>35 && imc1 <= 40 )
                {
                imc.setText(String.valueOf(imc1)+"-->Obésité sévère");
                description.setText("Votre poids est beaucoup trop important compte tenu de votre taille. Vous souffrez d'obésité, une obésité dite 'sévère' ou de stade 2. Cela signifie que vous êtes exposé à un risque important de maladies cardiaques (infarctus), vasculaires (accident vasculaire cérébral, insuffisance veineuse) ou métaboliques (diabète).\n" +
"        Cette obésité peut aussi être à l'origine d'essoufflement, de fatigue, de douleurs dorsales ou articulaires et difficultés psychologiques qui perturbent sérieusement vos activités quotidiennes. Une perte de poids s’impose ! Pour vous y encourager, sachez que même une même une perte de poids modérée (5 à 10 %) aura un effet positif sur votre santé et votre mental. Vous en ressentirez immédiatement les effets.\n" +
"\n" +
"        Dans cette optique, il serait judicieux de pratiquer tous les jours un peu plus d'activité physique et de réduire la part des graisses dans votre alimentation. En tous cas, une consultation avec votre médecin s'impose, pour qu'il établisse un bilan et envisage avec vous les méthodes de perte de poids les plus adaptées, ainsi que les éventuels traitements possibles.\" \n" +
"   ");
                }
                else if(imc1>40)
                {
                imc.setText(String.valueOf(imc1)+"-->Obésité morbide ou massive");
                description.setText("Votre poids est beaucoup trop important compte tenu de votre taille. Vous souffrez d'obésité, une obésité dite 'sévère' ou de stade 2. Cela signifie que vous êtes exposé à un risque important de maladies cardiaques (infarctus), vasculaires (accident vasculaire cérébral, insuffisance veineuse) ou métaboliques (diabète).\n" +
"        Cette obésité peut aussi être à l'origine d'essoufflement, de fatigue, de douleurs dorsales ou articulaires et difficultés psychologiques qui perturbent sérieusement vos activités quotidiennes. Une perte de poids s’impose ! Pour vous y encourager, sachez que même une même une perte de poids modérée (5 à 10 %) aura un effet positif sur votre santé et votre mental. Vous en ressentirez immédiatement les effets.\n" +
"\n" +
"        Dans cette optique, il serait judicieux de pratiquer tous les jours un peu plus d'activité physique et de réduire la part des graisses dans votre alimentation. En tous cas, une consultation avec votre médecin s'impose, pour qu'il établisse un bilan et envisage avec vous les méthodes de perte de poids les plus adaptées, ainsi que les éventuels traitements possibles.\" ");
               
                }
                Ctaille.add(lbTaille).add(taille);
                add(Ctaille);
                Cpoids.add(lbPoids).add(poids);
                add(Cpoids);
                CIMC.add(lbIMC).add(imc);
                add(CIMC);
                Cdescription.add(lbDescription);
                add(Cdescription);
                add(description);
                SpanLabel lbPediatre = new SpanLabel("Pediatre: Dr."+p.getNomP()+" "+p.getPrenomP());
                
                add(lbPediatre);
                SpanLabel envoiMail = new SpanLabel("Envoyer un mail pour plus d'information");
                envoiMail.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                add(envoiMail);
                SendTo.setText( p.getEmailP());
                 add(SendTo);

            }
        }

        Button bt = new Button("Envoyer");
        
        TextField Body = new TextField("","Contenu du mail");
        TextField Subject = new TextField("","Sujet");
       

       
        add(Subject);
        add(Body);
        add(bt);
        //Label lb=new Label(ServiceProfil.getInstance().getOne("15").toString());
        //add(lb);
        bt.addActionListener((evt) -> {
            Message m = new Message(Body.getText());
            //m.getAttachments().put(textAttachmentUri, "text/plain");
            //Display.getInstance().sendMessage(new String[] {"said.kamel@esprit.tn"}, "Subject of message", m);
            Display.getInstance().sendMessage(new String[]{SendTo.getText()}, Subject.getText(), m);
        });

    }

}
