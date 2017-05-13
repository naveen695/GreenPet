package com.dgree.service;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.UserBean;
import com.dgree.userDAO.User;
import com.dgree.userDAO.UserDao;
import com.mongodb.client.MongoDatabase;

public class UserSignUp implements SignUp{

	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
		

	@Override
	public void sendMail(UserBean userBean) {
		logger.info("**** inside UserSignUP.sendmail() **** ");
		
		Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication("knnaveen695@gmail.com","9494931508");
                  }
                });	
		String link = "http://localhost:8000/GreenPet/SignUpServlet";
		StringBuilder bodyText = new StringBuilder(); 
        bodyText.append("<div>")
             .append("  Dear User<br/><br/>")
             .append("  Thank you for registration. Your mail ("+userBean.getEmail()+") is under verification<br/>")
             .append("  Please click <a href=\""+link+"\">here</a> or open below link in browser<br/>")
             .append("  <a href=\""+link+"\">"+link+"</a>")
             .append("  <br/><br/>")
             .append("  Thanks,<br/>")
             .append("  GreenPet Team")
             .append("</div>");
        Message message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress("knnaveen695@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(userBean.getEmail()));
			 message.setSubject("Email Registration testing from GreePet");
		        message.setContent(bodyText.toString(), "text/html; charset=utf-8");
		logger.info("**** sending email to : "+userBean.getEmail()+".");
		        Transport.send(message);        
        } catch (MessagingException e) {
			logger.info("**** Unable to send mail for Activation Link :"+e);
		}

	}
@Override
	public Boolean validateUser(MongoDatabase mongoDatabase,UserBean us) {
	logger.info("**** validating User New or Old ****");
		User user=new UserDao();
		return  user.validateNewUser(mongoDatabase, us);
	}

}
