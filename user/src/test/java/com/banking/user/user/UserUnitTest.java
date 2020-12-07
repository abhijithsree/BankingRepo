package com.banking.user.user;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.apache.http.util.Asserts;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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

@RunWith(SpringRunner.class)
public class UserUnitTest  {

	 @Before
	 public void setUp() {
		 BankAccount account = new BankAccount();

	     Mockito.when(userRepository.save(account))
	       .thenReturn(account);
	 }
	    @Mock
	    private BankAccountRepository userRepository;

	    @Mock
	    private UserServiceImpl userservice;

	   
	@Test
	public void testUser() {
		assertNotNull(userservice.createUser(new HashMap<String, Object>()));
	}
}
