package com.banking.accountbalance.controller;

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

import com.banking.accountbalance.model.BankingResponse;
import com.banking.accountbalance.model.BankingUser;
import com.banking.accountbalance.model.HttpResponseStatusValues;
import com.banking.accountbalance.service.AccountBalanceService;


@RestController
@RequestMapping("/viewBalance")
public class AccountBalanceController {
    Logger logger = LoggerFactory.getLogger(AccountBalanceController.class);

	@Autowired
	AccountBalanceService accountService;
	
	@PostMapping("/getBalance")
	@ResponseBody
	public ResponseEntity<BankingResponse> getBalance(@RequestBody Map<String,Object> user){
		logger.info("input request for getBalance",user);
		BankingUser bankingUser =accountService.getBankAccountDetails(user);
		logger.info("Out put response for getBalance",bankingUser);
		BankingResponse response = new BankingResponse();
		response.setData(bankingUser);
		response.setMessage(HttpResponseStatusValues.SUCCESS.toString());
		response.setStatusCode(HttpStatus.OK.value());
		ResponseEntity<BankingResponse> responsefinal = new ResponseEntity<BankingResponse>(response,HttpStatus.OK);
		return responsefinal;
		
	}
}
