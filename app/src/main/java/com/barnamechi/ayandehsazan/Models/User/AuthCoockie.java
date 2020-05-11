package com.barnamechi.ayandehsazan.Models.User;

import com.google.gson.annotations.SerializedName;

public class AuthCoockie{

	@SerializedName("cookie")
	private String cookie;

	@SerializedName("cookie_admin")
	private String cookieAdmin;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private String status;

	@SerializedName("cookie_name")
	private String cookieName;

	public void setCookie(String cookie){
		this.cookie = cookie;
	}

	public String getCookie(){
		return cookie;
	}

	public void setCookieAdmin(String cookieAdmin){
		this.cookieAdmin = cookieAdmin;
	}

	public String getCookieAdmin(){
		return cookieAdmin;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setCookieName(String cookieName){
		this.cookieName = cookieName;
	}

	public String getCookieName(){
		return cookieName;
	}

	@Override
 	public String toString(){
		return 
			"AuthCoockie{" + 
			"cookie = '" + cookie + '\'' + 
			",cookie_admin = '" + cookieAdmin + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			",cookie_name = '" + cookieName + '\'' + 
			"}";
		}
}