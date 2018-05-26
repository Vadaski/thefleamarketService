package com.litavadaski.fleamarket.service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.UserInfo;

public interface UserInfoInterface {

	//创建一张用户个人信息表
	public Response<UserInfo> createUserInfo(int id);
	//删除用户个人信息表
	public void deleteUserInfoById(int id);
	//更改昵称
	public Response<Boolean> updateName(String name,int id,String token);
	//更改全部信息
	public Response<Boolean> updateAll(UserInfo userInfo,String token);
	//更改头像
	public void updateProfile(String profile);
	//通过用户id获取用户信息
	public Response<UserInfo> getUserById(int id);
	//检查余额
	public Response<Integer> checkBalance(int id,String token);
}
