package com.barnamechi.ayandehsazan.Models.MediaModel;

import com.google.gson.annotations.SerializedName;

public class Sizes{

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("medium")
	private Medium medium;

	@SerializedName("full")
	private Full full;

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setMedium(Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	public void setFull(Full full){
		this.full = full;
	}

	public Full getFull(){
		return full;
	}

	@Override
 	public String toString(){
		return 
			"Sizes{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",medium = '" + medium + '\'' + 
			",full = '" + full + '\'' + 
			"}";
		}
}