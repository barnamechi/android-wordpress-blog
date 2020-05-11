package com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia;

import com.google.gson.annotations.SerializedName;

public class Medium{

	@SerializedName("size_before")
	private int sizeBefore;

	@SerializedName("bytes")
	private int bytes;

	@SerializedName("size_after")
	private int sizeAfter;

	@SerializedName("time")
	private double time;

	@SerializedName("percent")
	private double percent;

	@SerializedName("file")
	private String file;

	@SerializedName("mime_type")
	private String mimeType;

	@SerializedName("width")
	private int width;

	@SerializedName("source_url")
	private String sourceUrl;

	@SerializedName("height")
	private int height;

	public void setSizeBefore(int sizeBefore){
		this.sizeBefore = sizeBefore;
	}

	public int getSizeBefore(){
		return sizeBefore;
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

	public void setPercent(double percent){
		this.percent = percent;
	}

	public double getPercent(){
		return percent;
	}

	public void setFile(String file){
		this.file = file;
	}

	public String getFile(){
		return file;
	}

	public void setMimeType(String mimeType){
		this.mimeType = mimeType;
	}

	public String getMimeType(){
		return mimeType;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setSourceUrl(String sourceUrl){
		this.sourceUrl = sourceUrl;
	}

	public String getSourceUrl(){
		return sourceUrl;
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
			"Medium{" + 
			"size_before = '" + sizeBefore + '\'' + 
			",bytes = '" + bytes + '\'' + 
			",size_after = '" + sizeAfter + '\'' + 
			",time = '" + time + '\'' + 
			",percent = '" + percent + '\'' + 
			",file = '" + file + '\'' + 
			",mime_type = '" + mimeType + '\'' + 
			",width = '" + width + '\'' + 
			",source_url = '" + sourceUrl + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}