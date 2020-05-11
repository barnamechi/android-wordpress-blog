package com.barnamechi.ayandehsazan.Models.User;

import com.google.gson.annotations.SerializedName;

public class Capabilities{

	@SerializedName("administrator")
	private boolean administrator;

	@SerializedName("um_admin")
	private boolean umAdmin;

	public void setAdministrator(boolean administrator){
		this.administrator = administrator;
	}

	public boolean isAdministrator(){
		return administrator;
	}

	public void setUmAdmin(boolean umAdmin){
		this.umAdmin = umAdmin;
	}

	public boolean isUmAdmin(){
		return umAdmin;
	}

	@Override
 	public String toString(){
		return 
			"Capabilities{" + 
			"administrator = '" + administrator + '\'' + 
			",um_admin = '" + umAdmin + '\'' + 
			"}";
		}
}