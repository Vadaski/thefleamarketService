package com.litavadaski.fleamarket.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//返回统一格式

@JsonIgnoreProperties(ignoreUnknown = true)

public class Response<T> {
	boolean status;
	T value;
	String errormessage;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public T getT() {
		return value;
	}
	public void setT(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	@Override
	public String toString() {
		return "Response [status=" + status + ", value=" + value + ", errormessage=" + errormessage + "]";
	}
	
}
