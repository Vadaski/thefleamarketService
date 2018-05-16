package com.litavadaski.fleamarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.litavadaski.fleamarket.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
		
		//通过Email查询用户
		public Account findByEmail(String email);
	
}
