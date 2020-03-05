/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Service.EmployeService;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;



/**
 *
 * @author alaka
 */
public class MailEmploye {
    public static void sendMail(String recepient, String Body) throws Exception{
        System.out.println("Preparing to send:");
        Properties properties = new Properties();
        
        
        String myAccountEmail ="jardindenfant01@gmail.com";
        String password ="jardin123";
        
        properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
        
        
        
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        Message message = prepareMessage(session,myAccountEmail,recepient,Body);
        
        Transport.send(message);
        System.out.println("message send successfully");
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail,String recepient, String Body){
        try {
            EmployeService Act = new EmployeService();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Information de compte");
            message.setText(Body);
            //message.setText(Act.getListActivite2().toString());
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(MailEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
}
