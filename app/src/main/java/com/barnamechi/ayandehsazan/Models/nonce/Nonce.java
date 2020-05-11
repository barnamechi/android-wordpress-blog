package com.barnamechi.ayandehsazan.Models.nonce;

import com.google.gson.annotations.SerializedName;

public class Nonce{

	@SerializedName("controller")
	private String controller;

	@SerializedName("method")
	private String method;

	@SerializedName("nonce")
	private String nonce;

	@SerializedName("status")
	private String status;

	public void setController(String controller){
		this.controller = controller;
	}

	public String getController(){
		return controller;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setNonce(String nonce){
		this.nonce = nonce;
	}

	public String getNonce(){
		return nonce;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Nonce{" + 
			"controller = '" + controller + '\'' + 
			",method = '" + method + '\'' + 
			",nonce = '" + nonce + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}