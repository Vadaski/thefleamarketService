package com.litavadaski.fleamarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.entity.Response;
import com.litavadaski.fleamarket.service.AccountService;

@RestController
@RequestMapping(path = "/Account")
public class AccountController {
	@Autowired 
	AccountService service;
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@PutMapping
	public Response<Account> createAccount(@RequestBody Account account){
		logger.debug("正在创建账户");
		return service.createAccount(account);
	}
	
	@DeleteMapping
	public @ResponseBody Response<Boolean> deleteAccount(String email,String password){
		Response<Boolean> response = 
				service.deleteAccount(email, password);
		return response;
	}
	
	@PostMapping
	public @ResponseBody Response<Boolean> updatePassword(String email,String password,String newPassword){
		Response<Boolean> response =
		service.updatePassword(email, password, newPassword);
		return response;
	}
}
