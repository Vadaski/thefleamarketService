package com.litavadaski.fleamarket.repository;

//import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.litavadaski.fleamarket.entity.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer>{
		
		//通过Email查询用户
		public Account findByEmail(String email);
	
}
