package com.barnamechi.ayandehsazan.Models.User.register;

import com.google.gson.annotations.SerializedName;

public class RegisterUser{

	@SerializedName("cookie")
	private String cookie;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("cookie_admin")
	private String cookieAdmin;

	@SerializedName("status")
	private String status;

	@SerializedName("cookie_name")
	private String cookieName;

	@SerializedName("username")
	private String username;

	public void setCookie(String cookie){
		this.cookie = cookie;
	}

	public String getCookie(){
		return cookie;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCookieAdmin(String cookieAdmin){
		this.cookieAdmin = cookieAdmin;
	}

	public String getCookieAdmin(){
		return cookieAdmin;
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"RegisterUser{" + 
			"cookie = '" + cookie + '\'' + 
			",user_id = '" + userId + '\'' + 
			",cookie_admin = '" + cookieAdmin + '\'' + 
			",status = '" + status + '\'' + 
			",cookie_name = '" + cookieName + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}