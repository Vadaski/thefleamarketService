package com.litavadaski.fleamarket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.UserInfo;
import com.litavadaski.fleamarket.service.UserInfoService;

@RestController
@RequestMapping(path = "/UserInfo")
public class UserInfoController {
	@Autowired
	UserInfoService service;
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
    
	//更新昵称
	@PutMapping(path= "/name")
	public @ResponseBody Response<Boolean> updateName(String name,int id) {
		return service.updateName(name, id);
	}
	
	//更新全部个人信息
	@PutMapping
	public boolean update(@RequestBody UserInfo userInfo) {
		service.updateAll(userInfo);
		return true;
	}
	
	//用户名模糊查找
	@GetMapping(path="/name")
	public @ResponseBody Response<List<UserInfo>> findByName(String name) {
		return service.findUserInfoByName(name);
	}
	
	//通过用户id获取用户信息
	@GetMapping(path="/id")
	public @ResponseBody Response<UserInfo> findById(int id) {
		return service.getUserById(id);
	}
	
	//检查余额
	@GetMapping
	public @ResponseBody Response<Integer> checkBalance(int id) {
		return service.checkBalance(id);
	}
	
	//TODO:传输头像模块还未完成
}
