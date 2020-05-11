package com.barnamechi.ayandehsazan.PluginModels.categories;

import com.google.gson.annotations.SerializedName;

public class JsonMember1536x1536{

	@SerializedName("width")
	private int width;

	@SerializedName("url")
	private String url;

	@SerializedName("height")
	private int height;

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"JsonMember1536x1536{" + 
			"width = '" + width + '\'' + 
			",url = '" + url + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}