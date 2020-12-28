package com.banking.user.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.banking.user.entities.BankAccount;
import com.banking.user.impl.UserServiceImpl;
import com.banking.user.model.BankingUser;
import com.banking.user.repository.BankAccountRepository;
import com.banking.user.service.UserService;

import junit.framework.Assert;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceTest{
	@Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;
 
    @Test
    public void testAddUser() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/users/createUser";
        URI uri = new URI(baseUrl);
        Map<String,Object> requestMap=new HashMap<String, Object>();   
        requestMap.put("name","abhijith");
        requestMap.put("emailid","sreekumar.abhijith93@gmail.com");
        requestMap.put("amount",new Double(3000));
        requestMap.put("transactionstatus","C");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(requestMap, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
