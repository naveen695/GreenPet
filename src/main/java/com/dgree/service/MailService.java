package com.dgree.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.model.UserBean;

public class MailService {
	private static Logger logger = LogManager.getLogger();
	private Map<String, String> prop=new HashMap<>();

	public boolean sendMail(Map<String, String> details,StringBuffer url){

	logger.info("**** inside UserSignUP.sendmail() **** ");
	StringBuilder bodyText = new StringBuilder(); 
    bodyText.append("<div>")


    .append("RE ::  "+details.get(Constants.COMMENTS)+"<br/><br/>")
    .append("<br/><br/>")
    .append("Name :  "+details.get(Constants.NAME)+"<br/><br/>")
	.append("Mobile Number :  "+details.get(Constants.MOBILE)+"<br/><br/>")
	.append("E-mail :  "+details.get(Constants.EMAIL)+"<br/><br/><br/><br/>")
    .append("  Thanks & Regards ,<br/>")
    .append("Team @ Dgree .")
    .append("</div>");
     UserBean bean=new UserBean();
     bean.setEmail(details.get(Constants.EMAIL));
     bean.setSubject(details.get(Constants.HEADING));
     
     return UserSignUp.sendMail(bean, bodyText,this.prop);
     
      
}

	public Map<String, String> getProp() {
		return prop;
	}

	public void setProp(Map<String, String> prop) {
		this.prop = prop;
	}
}
