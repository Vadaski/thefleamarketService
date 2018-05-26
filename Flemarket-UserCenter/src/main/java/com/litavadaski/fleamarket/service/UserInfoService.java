package com.litavadaski.fleamarket.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.UserInfo;
import com.litavadaski.fleamarket.repository.UserInfoRepository;



@Service
public class UserInfoService implements UserInfoInterface{
	@Autowired
	UserInfoRepository repo;
	@Autowired
	TokenService tokenChecker;
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);
	
	//自动创建表
	@Override
	public Response<UserInfo> createUserInfo(int id) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		logger.debug("用户信息表创建成功");
		return new Response<UserInfo>();
	}
	
	//自动删除用户信息
	@Override
	public void deleteUserInfoById(int id) {
		repo.deleteById(id);
		logger.debug("成功删除id为"+id+"的用户");
	}
	
	//更改昵称
	@Override
	public Response<Boolean> updateName(String name,int id,String token) {
		Response<Boolean> response = new Response<>();
		if(!repo.existsById(id)) {
			response.setValue(false);
			response.setErrormessage("更改失败，不存在该账户");
		    return response;	
		}
		if(!tokenChecker.checkAccessToken(token).getValue()) {
			response.setValue(false);
			response.setErrormessage("验证失败，请重新登陆");
		}
		Optional<UserInfo> userInfo = repo.findById(id);
		userInfo.get().setName(name);
		repo.save(userInfo.get());
		logger.debug("昵称更改成功");
		response.setStatus(true);
	    response.setValue(true);
	    return response;
	    
	}
	
	//更改全部信息
	@Override
	public Response<Boolean> updateAll(UserInfo userInfo,String token){
		Response<Boolean> response = new Response<>();
		Response<Boolean> tokenResponse = tokenChecker.checkAccessToken(token);	
		if (!tokenResponse.getValue()) {
			response.setErrormessage(tokenResponse.getErrormessage());
			response.setStatus(false);
			return response;
		}
		userInfo.setBalance(repo.findById(userInfo.getId()).get().getBalance());
		repo.save(userInfo);
		logger.debug("成功更新用户信息");
		response.setStatus(true);
		return response;
	}
	
	//更改头像
	@Override
	public void updateProfile(String profile) {
		//TODO finish updateProfile
	}
	
	//通过用户id获取用户信息
	@Override
	public Response<UserInfo> getUserById(int id) {
		Response<UserInfo> response = new Response<>();
		response.setValue(repo.findById(id).get());
		if (response.getValue()==null) {
			response.setStatus(false);
			response.setErrormessage("不存在id为"+id+"的用户");
			logger.info("获取id为："+id+"的用户信息失败");
			return response;
		}
		logger.debug("获取id为："+id+"的用户信息成功");
		response.setStatus(true);
		return response;
	}
	//检查余额
	@Override
	public Response<Integer> checkBalance(int id,String token) {
		Response<Integer> response = new Response<>();
		Response<Boolean> tokenResponse = tokenChecker.checkAccessToken(token);
		if (!tokenResponse.getValue()) {
			response.setErrormessage(tokenResponse.getErrormessage());
			response.setStatus(false);
			return response;
		}
		response.setValue(repo.findById(id).get().getBalance());
		response.setStatus(true);
		logger.debug("id:"+id+"余额查询成功");
		return response;
	}
	
}
