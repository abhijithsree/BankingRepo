package com.banking.authenticate.authenticate;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

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

import com.banking.authenticate.entities.LoginUser;
import com.banking.authenticate.repository.LoginRepository;
import com.banking.authenticate.serviceImpl.AuthServiceImpl;



@RunWith(SpringRunner.class)
public class AuthUnitTest  {

	
	    @Mock
	    private LoginRepository bankRepo;
	    @Before
		 public void setUp() {
			 LoginUser account = new LoginUser();

		     Mockito.when(bankRepo.save(account))
		       .thenReturn(account);
		 }
	    @Mock
	    private AuthServiceImpl authservice;

	   
	@Test
	public void testUser() {
		assertNotNull(authservice.createLogin(new HashMap<String,Object>()));
	}
}
