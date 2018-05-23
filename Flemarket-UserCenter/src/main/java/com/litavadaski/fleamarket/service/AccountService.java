package com.litavadaski.fleamarket.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mockito.internal.stubbing.BaseStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.entity.UserInfo;
import com.litavadaski.fleamarket.repository.AccountRepository;
import com.litavadaski.fleamarket.repository.UserInfoRepository;

@Service
public class AccountService implements AccountInterface{
	
	@Autowired
	AccountRepository repo;
	@Autowired
	UserInfoRepository Urepo;
	@Autowired
	UserInfoService service;
	
	Logger logger = LoggerFactory.getLogger(Account.class);
	
//	String generateToken(Map<String, Object> claims) {
//		return Jwts.builder()
//	            .setClaims(claims)
//	            .setExpiration(generateExpirationDate())
//	            .signWith(SignatureAlgorithm.HS512, secret)
//	            .compact();
//
//	}
	
	//注册模块
	@Override
	public Response<Account> createAccount(Account account) {
		Response<Account> response = new Response<>();
		if (repo.findByEmail(account.getEmail()).size()<=0) {
			account.setPermission(1);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			account.setDate(date.format(new Date()));
			repo.save(account);
			response.setStatus(true);
			response.setValue(account);
			//自动创建用户信息表
			UserInfo userInfo = new UserInfo();
			userInfo.setId(account.getId());
			userInfo.setBalance(0);
			Urepo.save(userInfo);
			logger.debug(account.getId()+"号用户注册成功");
			return response;		
		}
		logger.debug("注册失败");
		response.setStatus(false);
		response.setErrormessage("当前邮箱已被注册，请更换邮箱");
		return response;
	}
	
	//注销账号
	@Override
	public Response<Boolean> deleteAccount(String email,String password){
		List<Account> account = repo.findByEmail(email);
		Response<Boolean> response = new Response<>();
		
		if(account.size()<=0) {
//			logger.debug(account.get(0).toString());
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("未查找到"+email+"账户");
			return response;
		}
		if(account.get(0).getPassword().equals(password)) {
			repo.deleteById(account.get(0).getId());
//			同时删除该用户的个人信息
			service.deleteUserInfoById(account.get(0).getId());
			logger.debug("AccountService："+account.get(0).getId()+"注销成功");
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
		List<Account> account = repo.findByEmail(email);
		Response<Boolean> response = new Response<>();
		
		if(account.size()<=0) {
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("未查找到该账户");
			return response;
		}
//		if(!account.get(0).isLogin()) {
//			response.setStatus(false);
//			response.setValue(false);
//			response.setErrormessage("请先登陆");
//			return response;
//		}
		if(account.get(0).getPassword().equals(password)) {
			account.get(0).setPassword(newPassword);
			repo.save(account.get(0));
			logger.debug("AccountService："+account.get(0).getId()+"密码更改成功");
			response.setValue(true);
			response.setStatus(true);
			return response;
		}
		logger.debug("密码更改失败");
		response.setValue(false);
		response.setStatus(false);
		response.setErrormessage("输入的密码有误，请重新输入");
		return response;
	}
	public Response<Boolean> Loggin(String email,String password){
		Response<Boolean> response = new Response<>();
		List<Account> account = repo.findByEmail(email);
		if(account.size()<=0) {
			response.setErrormessage("输入的账号有误，请重新输入");
			return response;
		}
		if (account.get(0).getPassword().equals(password)) {
			account.get(0).setLogin(true);
			response.setStatus(true);
			response.setValue(true);
		}
		response.setErrormessage("输入的密码有误，请重新输入");
		return response;
	}
}
