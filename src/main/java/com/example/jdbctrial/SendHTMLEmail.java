package com.example.jdbctrial;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHTMLEmail {

   Session session;
   // Get a Properties object
   //
   SendHTMLEmail(){
      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
      Properties props = System.getProperties();
     props.setProperty("mail.smtp.host", "smtp.gmail.com");
     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
     props.setProperty("mail.smtp.socketFactory.fallback", "false");
     props.setProperty("mail.smtp.port", "465");
     props.setProperty("mail.smtp.socketFactory.port", "465");
     props.put("mail.smtp.auth", "true");
     props.put("mail.debug", "true");
     props.put("mail.store.protocol", "pop3");
     props.put("mail.transport.protocol", "smtp");
   final String username = "aniket.mp@gmail.com";//
   final String password = "#include<1989>";
     try{
         session = Session.getDefaultInstance(props,
              new Authenticator(){
                 protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                 }});

      // -- Create a new message --
   }catch (Exception e){ System.out.println(e);}
}

public void send(String message) {

   Message msg = new MimeMessage(session);

   // -- Set the FROM and TO fields --
   try {
      msg.setFrom(new InternetAddress("aniket.mp@gmail.com"));
      msg.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse("aniket.mp@gmail.com", false));
      msg.setSubject("Hello");
      msg.setText(message);
      msg.setSentDate(new Date());
      Transport.send(msg);
      System.out.println("Message sent.");
   }catch (Exception e) {

   }
}

}