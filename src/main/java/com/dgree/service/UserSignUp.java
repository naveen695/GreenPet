package com.dgree.service;

import java.util.HashMap;
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

import com.dgree.model.LoginUserDetails;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.userDAO.User;
import com.dgree.userDAO.UserDao;
import com.dgree.userDAO.Util;
import com.mongodb.client.MongoDatabase;

public class UserSignUp implements UserDetails{
	private Map<String, String> prop=new HashMap<>();
	public static Logger logger= LogManager.getLogger();
		
	 Map<String, String> properties = new HashMap<>();
	 
	@Override
	public void sendMail(UserBean userBean,StringBuffer url) {
		
		logger.info("**** inside UserSignUP.sendmail() **** ");
		StringBuffer url1= new StringBuffer("https://dgree.com/GreenPet/");
		          /* Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(Util.MAIL_USERNAME,Util.MAIL_PASSWORD);
                  }
                });	*/
		url1.append(Util.Sign_up_link).append("?scope=activation&userId="+userBean.getUserid()+"&hash="+userBean.getHash());
		StringBuilder bodyText = new StringBuilder(); 
        bodyText.append("<div>")
             .append("  Dear User<br/><br/>")
             .append("  Thank you for registration. Your mail ("+userBean.getEmail()+") is under verification<br/>")
             .append("  Please click <a href=\""+url.toString()+"\">here</a> or open below link in browser<br/>")
             .append("  <a href=\""+url1.toString()+"\">"+url.toString()+"</a>")
             .append("  <br/><br/>")
             .append("  Thanks,<br/>")
             .append("  GreenPet Team")
             .append("</div>");
        userBean.setSubject("Email Registration testing from GreePet");
        sendMail(userBean, bodyText,this.prop);

	}
	public static boolean sendMail(UserBean userBean, StringBuilder bodyText, Map<String, String> prop2) {
		// Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", prop2.get("MailTransportProtocol"));
    	props.put("mail.smtp.port",prop2.get("MailSMTPPort")); 
    	props.put("mail.smtp.ssl.enable", prop2.get("MailSMTPEnable"));
    	props.put("mail.smtp.auth",prop2.get("MailSMTPAuth"));

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);


    
        Message message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress(prop2.get("FROM")));    
			message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(userBean.getEmail()));
			message.setSubject(userBean.getSubject());
		        message.setContent(bodyText.toString(), "text/html; charset=utf-8");
		logger.info("**** sending email to : "+userBean.getEmail()+".");
		   Transport transport = session.getTransport("smtps");
		     			   // Send the message.
	        try
	        {
	            System.out.println("Sending...");
	            
	            // Connect to Amazon SES using the SMTP username and password you specified above.
	            transport.connect(prop2.get("MAILSMTPHOST"), prop2.get("SMTPUSERNAME"), prop2.get("SMTPPASSWORD"));
	        	
	            // Send the email.
	            transport.sendMessage(message,message.getAllRecipients());
	            System.out.println("Email sent!");
	            return true;
	        }
	        catch (Exception ex) {
	            System.out.println("The email was not sent.");
	            System.out.println("Error message: " + ex.getMessage());
	        }
	        finally
	        {
	            // Close and terminate the connection.
	            transport.close();
	        }
			
		      //s  Transport.send(message);        
        } catch (MessagingException e) {
			logger.info("**** Unable to send mail for Activation Link :"+e);
		}
		return false;
	}
@Override
	public ValidateUser validateUser(MongoDatabase mongoDatabase,UserBean us) {
	logger.info("**** validating User New or Old ****");
		User user=new UserDao();
		return  user.validateNewUser(mongoDatabase, us);
	}

@Override
public boolean validateUserMail(MongoDatabase mongoDatabase, UserBean us) {
logger.info("**** validating email address.****");
	User userDao=new UserDao();
	return	userDao.validateUserEmail(mongoDatabase, us);
}
@Override
public LoginUserDetails  updateUserDetails(MongoDatabase mongoDatabase, UserBean us) {
	logger.info("****  updateUserDetails .****");
	User userDao=new UserDao();
	return	userDao.updateUserDetails(mongoDatabase, us);

	
}
public Map<String, String> getProp() {
	return prop;
}
public void setProp(Map<String, String> prop) {
	this.prop = prop;
}

}
