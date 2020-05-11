package com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia;

import com.google.gson.annotations.SerializedName;

public class Sizes{

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("large")
	private Large large;

	@SerializedName("medium")
	private Medium medium;

	@SerializedName("medium_large")
	private MediumLarge mediumLarge;

	@SerializedName("full")
	private Full full;

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setLarge(Large large){
		this.large = large;
	}

	public Large getLarge(){
		return large;
	}

	public void setMedium(Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	public void setMediumLarge(MediumLarge mediumLarge){
		this.mediumLarge = mediumLarge;
	}

	public MediumLarge getMediumLarge(){
		return mediumLarge;
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
			",large = '" + large + '\'' + 
			",medium = '" + medium + '\'' + 
			",medium_large = '" + mediumLarge + '\'' + 
			",full = '" + full + '\'' + 
			"}";
		}
}