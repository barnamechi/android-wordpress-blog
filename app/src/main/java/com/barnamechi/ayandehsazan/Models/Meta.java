package com.barnamechi.ayandehsazan.Models;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("amp_status")
	private String ampStatus;

	public void setAmpStatus(String ampStatus){
		this.ampStatus = ampStatus;
	}

	public String getAmpStatus(){
		return ampStatus;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"amp_status = '" + ampStatus + '\'' + 
			"}";
		}
}