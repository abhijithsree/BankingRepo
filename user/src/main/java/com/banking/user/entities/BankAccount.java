package com.banking.user.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="BANKUSERACCOUNT")
public class BankAccount {
	
	@Id
	@TableGenerator(
			   name = "Account_gen", 
			   table = "ID_GEN", 
			   pkColumnName = "gen_name", 
			   valueColumnName = "gen_value", 
			   pkColumnValue = "Account_gen", 
			   initialValue = 10000, 
			   allocationSize = 100)
 @GeneratedValue(strategy=GenerationType.TABLE, generator="Account_gen")
	@Column(name ="id")
	Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="emailid")
	private String emailid;
	@Column(name="name")
	private String name;
	@Column(name="accountnumber")
	private String accountnumber;
	public String getEmailId() {
		return emailid;
	}
	public void setEmailId(String emailId) {
		this.emailid = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountnumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountnumber = accountNumber;
	}

	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public BankTransaction getBankTransaction() {
		return bankTransaction;
	}
	public void setBankTransaction(BankTransaction bankTransaction) {
		this.bankTransaction = bankTransaction;
	}
    @PrimaryKeyJoinColumn  
    @OneToOne(cascade = CascadeType.ALL)
	private BankTransaction bankTransaction;
	
	

}
