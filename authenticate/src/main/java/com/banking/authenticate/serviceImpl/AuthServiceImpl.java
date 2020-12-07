package com.banking.authenticate.serviceImpl;

import java.util.Map;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.banking.authenticate.entities.LoginUser;
import com.banking.authenticate.model.BankingUser;
import com.banking.authenticate.producer.AuthenticateProducer;
import com.banking.authenticate.repository.LoginRepository;
import com.banking.authenticate.service.AuthService;
import com.banking.authenticate.util.AuthUtil;
@Service
@EnableBinding(AuthenticateProducer.class)
public class AuthServiceImpl implements AuthService{

@Autowired
AuthUtil authutil;

@Autowired
AuthenticateProducer callEmail;

@Autowired
LoginRepository loginRepo;
	@Override
	public BankingUser createLogin(Map<String, Object> loginuser) {
        System.out.println("loginuser "+loginuser);
		LoginUser user = new LoginUser();
		user.setEmailid((String) loginuser.get("emailId"));
		user.setName((String) loginuser.get("name"));
		user.setPassword(authutil.generateStrongPassword());
        LoginUser outputUser=loginRepo.save(user);
		BankingUser finaluser = new BankingUser(outputUser);
        System.out.println("finaluser "+finaluser);
		if(finaluser!=null) {
			callEmail.passwordUpdationChannel().send(MessageBuilder.withPayload(finaluser).build());
		}
		return finaluser;
	}
	@Override
	public boolean validateUser(Map<String, Object> user) {
		String userName=(String) user.get("name");
		String password = (String) user.get("password");
		Object [] userData =loginRepo.getUserDetails(userName);
		if( userData.length>0 && 
				userData[0]!=null && password.equals(userData[0].toString())) {
			return true;
		}
		return false;
	}

}
