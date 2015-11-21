/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.teste;

/**
 *
 * @author Lucas
 */
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail
{
      public static void main(String[] args) {
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("lucaswerner26@gmail.com", "weyand10");
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("lucaswerner26@gmail.com")); //Remetente

                Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("lucaswerner26@gmail.com");  

                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject("Enviando email com JavaMail");//Assunto
                  
                  
                  
                String htmlMessage = "<html>"
                          + "<h1>titulo</h1>"
                          + "<p>Código HTML da mensagem</p>"
                          + "<a href='www.google.com.br'>Google</a>"
                          + "</html>";
                  
                  message.setContent(htmlMessage, "text/html; charset=UTF-8");
                  
                  //message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
                  
                  Multipart multipart = new MimeMultipart();

                //criando a primeira parte da mensagem
                MimeBodyPart attachment0 = new MimeBodyPart();
                //configurando o htmlMessage com o mime type
                attachment0.setContent(htmlMessage,"text/html; charset=UTF-8");
                //adicionando na multipart
                multipart.addBodyPart(attachment0);
                message.setContent(multipart);
  
                  
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);

                  System.out.println("Feito!!!");

             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
            
        }
      

 
      }
  




