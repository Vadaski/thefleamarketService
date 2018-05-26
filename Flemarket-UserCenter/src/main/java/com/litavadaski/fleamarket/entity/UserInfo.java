package com.litavadaski.fleamarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserInfo {
	@Id
	private int id;
	private String name;
	private String phone;
	//收货地址
	private String address;
	private int balance;
	//评价，初始为80，满分100
	private int eva;
	//个性签名
	private String signature;
	//会话列表
	private int sessionList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
//	public Bson getProfile() {
//		return profile;
//	}
//	public void setProfile(Bson profile) {
//		this.profile = profile;
//	}
	public int getEva() {
		return eva;
	}
	public void setEva(int eva) {
		this.eva = eva;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getSessionList() {
		return sessionList;
	}
	public void setSessionList(int sessionList) {
		this.sessionList = sessionList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", balance="
				+ balance + ", eva=" + eva + ", signature=" + signature + "]";
	}	
}
