package com.banking.user.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="BANKUSERTRANSACTION")
public class BankTransaction {
	
	@Id
	
	@TableGenerator(
			   name = "AccountTrans_gen", 
			   table = "ID_GEN", 
			   pkColumnName = "GEN_NAME", 
			   valueColumnName = "GEN_VAL", 
			   pkColumnValue = "AccountTrans_gen", 
			   initialValue = 10000, 
			   allocationSize = 100)
 @GeneratedValue(strategy=GenerationType.TABLE, generator="AccountTrans_gen")
	@Column(name ="id")
	Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="accountnumber")
	private String accountnumber;
	
	public String getAccountNumber() {
		return accountnumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountnumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionStatus() {
		return transactionstatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionstatus = transactionStatus;
	}
	@Column(name="amount")
	private double amount;
	@Column(name="transactionstatus")
	private String transactionstatus;
	
	

}
