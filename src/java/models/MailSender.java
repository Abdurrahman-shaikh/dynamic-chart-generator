/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;

/**
 *
 * @author Abdur Rahman
 */
public class MailSender {
    public void sendMail(String emailto){
        String from = "projectmini743@gmail.com";
        final String username = "projectmini743";
        final String password = "photrkmuwwlsatso";
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.ssl.trust","smtp.gmail.com");
        props.put("mail.smtp.starttls.enable","true");          // tls for encryption
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");                      // port no of email is 587
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username, password);
           } 
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailto));
            msg.setSubject("Welcome to My App");
            msg.setContent("Please verify by clicking here <a href='http://localhost:8080/mp/fc/?type=model&page=EmailVerificationModel&email="+emailto +"'>Verify</a>","text/html");
            Transport.send(msg);
        } catch (MessagingException msge) {
            msge.printStackTrace();
        }
    }
}
