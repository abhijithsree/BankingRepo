package com.banking.user.user;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.Asserts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.banking.user.entities.BankAccount;
import com.banking.user.impl.UserServiceImpl;
import com.banking.user.repository.BankAccountRepository;
import com.banking.user.service.UserService;

@SpringBootTest
public class UserUnitTest  {
	 @Mock
	    private BankAccountRepository accountRepo;
    @BeforeEach
	 public void setUp() {
		 BankAccount account = new BankAccount();

	     Mockito.when(accountRepo.save(account))
	       .thenReturn(account);
	 }
	   

	    @InjectMocks
	    private UserServiceImpl userservice =new UserServiceImpl();

	   
	//@Test
	public void testUser() {
		Map<String,Object> user= new HashMap<String, Object>();
		user.put("amount", new Double(567));
		assertNotNull(userservice.createUser(user));
	}
}
