package com.litavadaski.fleamarket.service;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.entity.Response;
import com.litavadaski.fleamarket.repository.AccountRepository;



@Service
public class AccountService implements AccountInterface{
	
	@Autowired
	AccountRepository repo;
	
	Logger logger = LoggerFactory.getLogger(Account.class);
	
	
	//注册模块
	@Override
	public Response<Account> createAccount(Account account) {
		
		Response<Account> response = new Response<>();
		if (repo.findByEmail(account.getEmail())==null) {
			account.setEmail(account.getEmail());
			account.setPassword(account.getPassword());
			account.setPermission(1);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			account.setDate(date.format(new Date()));
			repo.save(account);
			response.setStatus(true);
			response.setValue(account);
			logger.debug("AccountService："+account.getId()+"号用户注册成功");
			return response;		
		}
		logger.debug("AccountService：注册失败");
		response.setStatus(false);
		response.setErrormessage("当前邮箱已被注册，请更换邮箱");
		return response;
	}
	
	//注销账号
	@Override
	public Response<Boolean> deleteAccount(String email,String password){
		Account account = new Account();
		Response<Boolean> response = new Response<>();
		account = repo.findByEmail(email);
		if(account==null) {
			logger.debug(account.toString());
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("未查找到"+email+"账户");
			return response;
		}
		if(account.getPassword().equals(password)) {
			repo.deleteById(account.getId());
			logger.debug("AccountService："+account.getId()+"注销成功");
			response.setValue(true);
			response.setStatus(true);
			return response;
		}
		logger.debug("注销失败");
		response.setValue(false);
		response.setStatus(false);
		response.setErrormessage("输入的密码"+password+"有误，请重新输入");
		return response;
	}
	
	//更改密码
	@Override
	public Response<Boolean> updatePassword(String email,String password,String newPassword) {
		Account account = new Account();
		Response<Boolean> response = new Response<>();
		account = repo.findByEmail(email);
		if(account==null) {
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("未查找到该账户");
			return response;
		}
		if(account.getPassword().equals(password)) {
			account.setPassword(newPassword);
			repo.save(account);
			logger.debug("AccountService："+account.getId()+"密码更改成功");
			response.setValue(true);
			response.setStatus(true);
			return response;
		}
		logger.debug("AccountService：密码更改失败");
		response.setValue(false);
		response.setStatus(false);
		response.setErrormessage("输入的密码有误，请重新输入");
		return response;
	}
	
}
