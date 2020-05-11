package com.barnamechi.ayandehsazan.Models.User.SingleUser;

import com.google.gson.annotations.SerializedName;

public class MppAvatar{

	@SerializedName("24")
	private String jsonMember24;

	@SerializedName("300")
	private String jsonMember300;

	@SerializedName("48")
	private String jsonMember48;

	@SerializedName("150")
	private String jsonMember150;

	@SerializedName("96")
	private String jsonMember96;

	@SerializedName("full")
	private String full;

	public void setJsonMember24(String jsonMember24){
		this.jsonMember24 = jsonMember24;
	}

	public String getJsonMember24(){
		return jsonMember24;
	}

	public void setJsonMember300(String jsonMember300){
		this.jsonMember300 = jsonMember300;
	}

	public String getJsonMember300(){
		return jsonMember300;
	}

	public void setJsonMember48(String jsonMember48){
		this.jsonMember48 = jsonMember48;
	}

	public String getJsonMember48(){
		return jsonMember48;
	}

	public void setJsonMember150(String jsonMember150){
		this.jsonMember150 = jsonMember150;
	}

	public String getJsonMember150(){
		return jsonMember150;
	}

	public void setJsonMember96(String jsonMember96){
		this.jsonMember96 = jsonMember96;
	}

	public String getJsonMember96(){
		return jsonMember96;
	}

	public void setFull(String full){
		this.full = full;
	}

	public String getFull(){
		return full;
	}

	@Override
 	public String toString(){
		return 
			"MppAvatar{" + 
			"24 = '" + jsonMember24 + '\'' + 
			",300 = '" + jsonMember300 + '\'' + 
			",48 = '" + jsonMember48 + '\'' + 
			",150 = '" + jsonMember150 + '\'' + 
			",96 = '" + jsonMember96 + '\'' + 
			",full = '" + full + '\'' + 
			"}";
		}
}