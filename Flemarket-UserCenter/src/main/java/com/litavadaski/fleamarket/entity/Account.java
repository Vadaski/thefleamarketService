package com.litavadaski.fleamarket.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Cascade;
import org.springframework.lang.NonNull;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
//	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
//	@OneToOne(cascade = CascadeType.ALL)
//	private UserInfo info;
	@Email
	@NonNull
	private String email;
	@NonNull
	private String password;
	//创建时自动生成
	private String Date;
	//普通用户权限为1
	private int permission;
	private boolean isLogin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", password=" + password + ", Date=" + Date
				+ ", permission=" + permission + ", isLogin=" + isLogin + "]";
	}
	
}