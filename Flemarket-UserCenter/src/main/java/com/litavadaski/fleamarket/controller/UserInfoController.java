package com.litavadaski.fleamarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Response<Boolean> updateName(@RequestParam String name,@RequestParam int id,@RequestParam String token) {
		return service.updateName(name, id, token);
	}
	
	//更新全部个人信息
	@PutMapping
	public boolean update(@RequestBody UserInfo userInfo,@RequestParam String token) {
		service.updateAll(userInfo,token);
		return true;
	}
	
	//通过用户id获取用户信息
	@GetMapping(path="/info")
	public Response<UserInfo> findById(@RequestParam int id) {
		return service.getUserById(id);
	}
	
	//检查余额
	@GetMapping
	public Response<Integer> checkBalance(@RequestParam int id,@RequestParam String token) {
		return service.checkBalance(id,token);
	}
	
	//TODO:传输头像模块还未完成

}
