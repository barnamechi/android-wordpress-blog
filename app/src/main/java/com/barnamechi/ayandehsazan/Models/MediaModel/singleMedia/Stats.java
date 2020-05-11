package com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia;

import com.google.gson.annotations.SerializedName;

public class Stats{

	@SerializedName("lossy")
	private boolean lossy;

	@SerializedName("size_before")
	private int sizeBefore;

	@SerializedName("keep_exif")
	private int keepExif;

	@SerializedName("bytes")
	private int bytes;

	@SerializedName("size_after")
	private int sizeAfter;

	@SerializedName("time")
	private double time;

	@SerializedName("api_version")
	private String apiVersion;

	@SerializedName("percent")
	private double percent;

	public void setLossy(boolean lossy){
		this.lossy = lossy;
	}

	public boolean isLossy(){
		return lossy;
	}

	public void setSizeBefore(int sizeBefore){
		this.sizeBefore = sizeBefore;
	}

	public int getSizeBefore(){
		return sizeBefore;
	}

	public void setKeepExif(int keepExif){
		this.keepExif = keepExif;
	}

	public int getKeepExif(){
		return keepExif;
	}

	public void setBytes(int bytes){
		this.bytes = bytes;
	}

	public int getBytes(){
		return bytes;
	}

	public void setSizeAfter(int sizeAfter){
		this.sizeAfter = sizeAfter;
	}

	public int getSizeAfter(){
		return sizeAfter;
	}

	public void setTime(double time){
		this.time = time;
	}

	public double getTime(){
		return time;
	}

	public void setApiVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}

	public String getApiVersion(){
		return apiVersion;
	}

	public void setPercent(double percent){
		this.percent = percent;
	}

	public double getPercent(){
		return percent;
	}

	@Override
 	public String toString(){
		return 
			"Stats{" + 
			"lossy = '" + lossy + '\'' + 
			",size_before = '" + sizeBefore + '\'' + 
			",keep_exif = '" + keepExif + '\'' + 
			",bytes = '" + bytes + '\'' + 
			",size_after = '" + sizeAfter + '\'' + 
			",time = '" + time + '\'' + 
			",api_version = '" + apiVersion + '\'' + 
			",percent = '" + percent + '\'' + 
			"}";
		}
}