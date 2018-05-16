package com.litavadaski.fleamarket.service;

import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.entity.Response;

public interface AccountInterface {
	
	//创建一个账户
	public Response<Account> createAccount(Account account);
	
	//通过输入账户及密码删除一个账户
	public Response<Boolean> deleteAccount(String email,String password);

	//通过输入原来的密码账户更改密码
	public Response<Boolean> updatePassword(String email,String password,String newPassword);
	
}
