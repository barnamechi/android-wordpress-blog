package com.barnamechi.ayandehsazan.Models.Posts.SinglePost;

import com.google.gson.annotations.SerializedName;

public class VersionHistoryItem{

	@SerializedName("count")
	private int count;

	@SerializedName("href")
	private String href;

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"VersionHistoryItem{" + 
			"count = '" + count + '\'' + 
			",href = '" + href + '\'' + 
			"}";
		}
}