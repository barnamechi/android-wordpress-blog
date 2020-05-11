package com.barnamechi.ayandehsazan.Models.Categories;

import com.google.gson.annotations.SerializedName;

public class WpPostTypeItem{

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"WpPostTypeItem{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}