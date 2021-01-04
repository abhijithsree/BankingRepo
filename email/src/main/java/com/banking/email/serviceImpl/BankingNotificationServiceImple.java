package com.banking.email.serviceImpl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.banking.email.service.BankingNotificationService;

@Service
public class BankingNotificationServiceImple implements BankingNotificationService{



	@Override
	public String sendNotificationToUser(String emailId,String password,String name) {

		try {
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("bnkdmnuser@gmail.com", "bankadmin@123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("bnkdmnuser@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
		   msg.setSubject("test");
		   msg.setContent("Your passowrd for login:"+name+":"+password, "text/html");
		   msg.setSentDate(new Date());

		   
		   Transport.send(msg);  
		   
		}
		catch(Exception e) {
			return e.getMessage();
		}
	
		return "Password and Details Shared";
	}
}
