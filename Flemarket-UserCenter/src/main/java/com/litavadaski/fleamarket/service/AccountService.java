package com.litavadaski.fleamarket.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.entity.UserInfo;
import com.litavadaski.fleamarket.repository.AccountRepository;
import com.litavadaski.fleamarket.repository.UserInfoRepository;
import com.litavadaski.fleamarket.security.AccessToken;

@Service
public class AccountService implements AccountInterface{
	
	@Autowired
	AccountRepository repo;
	@Autowired
	UserInfoRepository Urepo;
	@Autowired
	UserInfoService service;
	@Autowired
	TokenService tokenChecker;
	
	@Value("${client.base64Secret}")
	private String base64Secret;
	
	Logger logger = LoggerFactory.getLogger(Account.class);
	
	
	
	//注册模块
	@Override
	public Response<Account> createAccount(Account account) {
		Response<Account> response = new Response<>();
		if (repo.findByEmail(account.getEmail()).isEmpty()) {
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
		List<Account> accountList = repo.findByEmail(email);
		Response<Boolean> response = new Response<>();
		if(accountList.isEmpty()) {
//			logger.debug(account.get(0).toString());
			System.out.println("-----------------------");
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("未查找到"+email+"账户");
			return response;
		}
		Account account = accountList.get(0);
		if(account.getPassword().equals(password)) {
			repo.deleteById(account.getId());
//			同时删除该用户的个人信息
			service.deleteUserInfoById(account.getId());
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
		List<Account> accountList = repo.findByEmail(email);
		Response<Boolean> response = new Response<>();
		if(accountList.isEmpty()) {
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("未查找到该账户");
			return response;
		}
		Account account = accountList.get(0);
		if(!account.isLogin()) {
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("请先登陆");
			return response;
		}
		if(account.getPassword().equals(password)) {
			account.setPassword(newPassword);
			repo.save(account);
			logger.debug("AccountService："+accountList.get(0).getId()+"密码更改成功");
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
	//登陆
	public Response<AccessToken> Loggin(String email,String password,String audience){	
		Response<AccessToken> response = new Response<>();
		List<Account> accountList = repo.findByEmail(email);
		if(accountList.isEmpty()) {
			logger.debug("输入的账号有误");
			response.setErrormessage("输入的账号有误，请重新输入");
			return response;
		}
		Account account = accountList.get(0);
		if (account.getPassword().equals(password)) {
			logger.debug("验证通过");
			account.setLogin(true);
			response.setStatus(true);
			AccessToken accessToken = tokenChecker.getAccessToken(email, audience);
			account.setToken(accessToken.getAccess_token());
			repo.save(account);
			response.setValue(accessToken);
			return response;
		}
		logger.debug("输入密码错误");
		response.setErrormessage("输入的密码有误，请重新输入");
		return response;
		
	}
	//退出登陆
	public Response<Boolean> Unloggin(String email) {
		Response<Boolean> response = new Response<>();
		List<Account> accountList = repo.findByEmail(email);
		if(accountList.isEmpty()) {
			logger.debug("输入的账号不存在");
			response.setErrormessage("输入的账号有误，请重新输入");
			return response;
		}
		logger.debug("成功退出登陆");
		Account account = accountList.get(0);
		account.setLogin(false);
		account.setToken(null);
		repo.save(account);
		response.setStatus(true);
		response.setValue(true);
		return response;
	}
	//查找用户
	public Response<Account> findByEmail(String email) {
		List<Account> accountList = repo.findByEmail(email);
		Response<Account> response = new Response<>();
		if (accountList.isEmpty()) {
			response.setErrormessage("不存在该账户");
			return response;
		}
		accountList.get(0).setPassword(null);
		response.setValue(accountList.get(0));
		response.setStatus(true);
		return response;
	}
	
}
