package com.litavadaski.fleamarket.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.Account;
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
	public Response<Boolean> deleteAccount(@RequestParam String email,@RequestParam String password){
		Response<Boolean> response = 
				service.deleteAccount(email, password);
		return response;
	}
	
	@PostMapping
	public Response<Boolean> updatePassword(@RequestParam String email,@RequestParam String password,@RequestParam String newPassword){
		Response<Boolean> response =
		service.updatePassword(email, password, newPassword);
		return response;
	}
	
	@GetMapping(path="/login")
	public Object loggin(String email,String password,String audience){
		return service.Loggin(email, password,audience);
	}
	
	@GetMapping(path="/test")
	public Response<Account> findByEmail(@RequestParam String email){
		return service.findByEmail(email);
	}
}
