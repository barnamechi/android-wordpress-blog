package com.barnamechi.ayandehsazan.PluginModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CustomFields{

	@SerializedName("apt_pro_thumbnail")
	private List<String> aptProThumbnail;

	public void setAptProThumbnail(List<String> aptProThumbnail){
		this.aptProThumbnail = aptProThumbnail;
	}

	public List<String> getAptProThumbnail(){
		return aptProThumbnail;
	}

	@Override
 	public String toString(){
		return 
			"CustomFields{" + 
			"apt_pro_thumbnail = '" + aptProThumbnail + '\'' + 
			"}";
		}
}