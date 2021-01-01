package com.banking.authenticate.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.authenticate.entities.LoginUser;

@Repository
public interface LoginRepository extends CrudRepository<LoginUser, String>{

	@Query(value="select B.password from BANKUSER B where B.emailid=?1",nativeQuery = true)
	public Object[] getUserDetails(String userName);
}
