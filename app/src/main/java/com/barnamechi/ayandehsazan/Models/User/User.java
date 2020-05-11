package com.barnamechi.ayandehsazan.Models.User;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("firstname")
	private String firstname;

	@SerializedName("capabilities")
	private Capabilities capabilities;

	@SerializedName("registered")
	private String registered;

	@SerializedName("description")
	private String description;

	@SerializedName("nicename")
	private String nicename;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("url")
	private String url;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("displayname")
	private String displayname;

	@SerializedName("nickname")
	private String nickname;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setCapabilities(Capabilities capabilities){
		this.capabilities = capabilities;
	}

	public Capabilities getCapabilities(){
		return capabilities;
	}

	public void setRegistered(String registered){
		this.registered = registered;
	}

	public String getRegistered(){
		return registered;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setNicename(String nicename){
		this.nicename = nicename;
	}

	public String getNicename(){
		return nicename;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setDisplayname(String displayname){
		this.displayname = displayname;
	}

	public String getDisplayname(){
		return displayname;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return nickname;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
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
			"User{" + 
			"firstname = '" + firstname + '\'' + 
			",capabilities = '" + capabilities + '\'' + 
			",registered = '" + registered + '\'' + 
			",description = '" + description + '\'' + 
			",nicename = '" + nicename + '\'' + 
			",avatar = '" + avatar + '\'' + 
			",url = '" + url + '\'' + 
			",lastname = '" + lastname + '\'' + 
			",displayname = '" + displayname + '\'' + 
			",nickname = '" + nickname + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}