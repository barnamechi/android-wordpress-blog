package com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia;

import com.google.gson.annotations.SerializedName;

public class Smush{

	@SerializedName("sizes")
	private Sizes sizes;

	@SerializedName("stats")
	private Stats stats;

	public void setSizes(Sizes sizes){
		this.sizes = sizes;
	}

	public Sizes getSizes(){
		return sizes;
	}

	public void setStats(Stats stats){
		this.stats = stats;
	}

	public Stats getStats(){
		return stats;
	}

	@Override
 	public String toString(){
		return 
			"Smush{" + 
			"sizes = '" + sizes + '\'' + 
			",stats = '" + stats + '\'' + 
			"}";
		}
}