package com.banking.authenticate.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.authenticate.model.BankingResponse;
import com.banking.authenticate.service.AuthService;

/**
 * @author abhi
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthService authService;

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<BankingResponse> validateUser(@RequestBody Map<String,Object> request){
		logger.info("Auth flow is calling");
		boolean valid = authService.validateUser(request);
		logger.info("valid user",valid);
		BankingResponse response = new BankingResponse();
		response.setMessage("success");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(valid);
		ResponseEntity<BankingResponse> bankingresponse = new ResponseEntity<BankingResponse> (response,HttpStatus.OK);
		return bankingresponse;
		
	}
	
	
}
