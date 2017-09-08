package com.dgree.service;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.userDAO.Util;

public class MailService {
	private static Logger logger = LogManager.getLogger();

	public void sendMail(Map<String, String> details,StringBuffer url){

	logger.info("**** inside UserSignUP.sendmail() **** ");
	Properties props = new Properties();
    props.put("mail.smtp.auth", Util.MAIL_SMTP_ATHU);
    props.put("mail.smtp.starttls.enable",Util.MAIL_SMTP_ENABLE);
    props.put("mail.smtp.host",Util.MAIL_SMTP_HOST);
    props.put("mail.smtp.port",Util.MAIL_SMTP_PORT);
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(Util.MAIL_USERNAME,Util.MAIL_PASSWORD);
              }
            });	
    
    String link = url.toString();
	StringBuilder bodyText = new StringBuilder(); 
    bodyText.append("<div>")

    .append("RE ::  "+details.get(Constants.COMMENTS)+"<br/><br/>")
    .append("<br/><br/>")
    .append("Name :  "+details.get(Constants.NAME)+"<br/><br/>")
	.append("Mobile Number :  "+details.get(Constants.MOBILE)+"<br/><br/>")
	.append("E-mail :  "+details.get(Constants.EMAIL)+"<br/><br/><br/><br/>")
    .append("  Thanks & Regards ,<br/>")
    .append(details.get(Constants.NAME))
    .append("</div>");
     
    Message message = new MimeMessage(session);
    try {
		message.setFrom(new InternetAddress(Util.MAIL_USERNAME));
		message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(details.get(Constants.EMAIL)));
		message.setSubject(details.get(Constants.HEADING));
	    message.setContent(bodyText.toString(), "text/html; charset=utf-8");
	    logger.info("**** sending email to : "+Constants.EMAIL+".");
	    Transport.send(message);        
    } catch (MessagingException e) {	
		logger.info("**** Unable to send mail for Activation Link :"+e);
	}
}
}
