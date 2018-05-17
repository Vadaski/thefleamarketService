package com.litavadaski.fleamarket.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.UserInfo;
import com.litavadaski.fleamarket.repository.UserInfoRepository;



@Service
public class UserInfoService implements UserInfoInterface{
	@Autowired
	UserInfoRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);
	
	//自动创建表
	@Override
	public Response<UserInfo> createUserInfo(int id) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		logger.debug("用户信息表创建成功");
		return new Response<UserInfo>();
	}
	
	//删除用户信息
	@Override
	public void deleteUserInfoById(int id) {
		repo.deleteById(id);
		logger.debug("成功删除id为"+id+"的用户");
	}
	
	//更改昵称
	@Override
	public Response<Boolean> updateName(String name,int id) {
	    Response<Boolean> response = new Response<>();
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
	public void updateAll(UserInfo userInfo){
		repo.save(userInfo);
		logger.info("成功更新用户信息");
	}
	
	//用户名模糊查找
	@Override
	public Response<List<UserInfo>> findUserInfoByName(String name) {
		Response<List<UserInfo>> response = new Response<>();
		Page<UserInfo> page = repo.findAllByName(name);
		if (!page.hasContent()) {
			logger.info("未查询到任何昵称可能为"+name+"的用户");
			response.setErrormessage("未查询到任何昵称可能为"+name+"的用户");
			response.setStatus(false);
		}
		logger.debug("查询成功");
		response.setValue(page.getContent());
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
	public Response<Integer> checkBalance(int id) {
		Response<Integer> response = new Response<>();
		response.setValue(repo.findById(id).get().getBalance());
		logger.debug("id:"+id+"余额查询成功");
		return response;
	}
	

}
