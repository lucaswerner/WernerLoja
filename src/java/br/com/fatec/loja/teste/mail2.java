/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.loja.teste;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Lucas
 */
public class mail2 {
    //o tratamento de excessoes foi simplificado (throws MessagingException)
 //para facilitar o entendimento do exemplo
 public static void main(String[] args) throws MessagingException{
  //objeto para definicao das propriedades de configuracao do provider
  Properties props = new Properties();
  //o valor "smtp.host.com.br" deve ser alterado para o seu servidor SMTP
  props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
  //obtendo um Session com a configuração 
  //do servidor SMTP definida em props
  Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("lucaswerner26@gmail.com", "weyand10");
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);
            
  MimeMessage message = new MimeMessage(session);

  //substituir pelos e-mails desejados
  message.setFrom(new InternetAddress("lucaswerner26@gmail.com")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("lucaswerner26@gmail.com"); 
  //configurando o remetente e o destinatario
  
   //configurando a data de envio,  o assunto e o texto da mensagem
  message.setSentDate(new Date());
  message.setSubject("Enviando uma mensagem formatada com anexo");
  
  // conteudo html que sera atribuido a mensagem
  String htmlMessage = "< h t m l > Código HTML da mensagem < / h t m l >";

  //criando a Multipart
  Multipart multipart = new MimeMultipart();

  //criando a primeira parte da mensagem
  MimeBodyPart attachment0 = new MimeBodyPart();
  //configurando o htmlMessage com o mime type
  attachment0.setContent(htmlMessage,"text/html; charset=UTF-8");
  //adicionando na multipart
  multipart.addBodyPart(attachment0);
  
  //arquivo que será anexado
 
  //criando a segunda parte da mensagem
  
  //configurando o DataHandler para o arquivo desejado
  //a leitura dos bytes, descoberta e configuracao do tipo
  //do arquivo serão resolvidos pelo JAF (DataHandler e FileDataSource)
 
  //configurando o nome do arquivo que pode ser diferente do arquivo
  //original Ex: setFileName("outroNome.png")
 
  //adicionando o anexo na multipart
 

  //adicionando a multipart como conteudo da mensagem 
  message.setContent(multipart);
  
  //enviando
  Transport.send(message);
  
  System.out.println("E-mail enviado com sucesso!");
 }
}

