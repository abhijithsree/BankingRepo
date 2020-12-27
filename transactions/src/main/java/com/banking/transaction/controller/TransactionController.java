package com.banking.transaction.controller;

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

import com.banking.transaction.model.BankingResponse;
import com.banking.transaction.model.HttpResponseStatusValues;
import com.banking.transaction.service.TransactionService;


@RestController
@RequestMapping("/financeTransaction")
public class TransactionController {
    Logger logger = LoggerFactory.getLogger(TransactionController.class);
	@Autowired
	TransactionService finaceService;
	
	@PostMapping("/financeService")
	@ResponseBody
	public ResponseEntity<BankingResponse> credit(@RequestBody Map<String,Object> user){
		logger.info("credit input:",user);
		String bankingUser =finaceService.financeTransactions(user);
		logger.info("credit output:",user);
		BankingResponse response = new BankingResponse();
		response.setData(bankingUser);
		response.setMessage(HttpResponseStatusValues.SUCCESS.toString());
		response.setStatusCode(HttpStatus.OK.value());
		ResponseEntity<BankingResponse> responsefinal = new ResponseEntity<BankingResponse>(response,HttpStatus.OK);
		return responsefinal;
		
	}
	}
