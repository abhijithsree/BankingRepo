package com.banking.user.user;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.user.model.BankingResponse;
import com.banking.user.model.BankingUser;
import com.banking.user.model.HttpResponseStatusValues;
import com.banking.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService bankUserService;

	@PostMapping("/createUser")
	@ResponseBody
	public ResponseEntity<BankingResponse> createUser(@RequestBody Map<String,Object> user){
		logger.info("createUser Request {}", user);
		BankingUser bankingUser =bankUserService.createUser(user);
		logger.info("createUser Response {}", bankingUser);
		BankingResponse response = new BankingResponse();
		response.setData(bankingUser);
		response.setMessage(HttpResponseStatusValues.SUCCESS.toString());
		response.setStatusCode(HttpStatus.OK.value());
		ResponseEntity<BankingResponse> responsefinal = new ResponseEntity<BankingResponse>(response,HttpStatus.OK);
		return responsefinal;
		
	}
	
	
}
