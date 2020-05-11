package com.barnamechi.ayandehsazan.PluginModels.categories;

import com.google.gson.annotations.SerializedName;

public class Images{

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("post-thumbnail")
	private PostThumbnail postThumbnail;

	@SerializedName("2048x2048")
	private JsonMember2048x2048 jsonMember2048x2048;

	@SerializedName("1536x1536")
	private JsonMember1536x1536 jsonMember1536x1536;

	@SerializedName("medium")
	private Medium medium;

	@SerializedName("twentytwenty-fullscreen")
	private TwentytwentyFullscreen twentytwentyFullscreen;

	@SerializedName("full")
	private Full full;

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setPostThumbnail(PostThumbnail postThumbnail){
		this.postThumbnail = postThumbnail;
	}

	public PostThumbnail getPostThumbnail(){
		return postThumbnail;
	}

	public void setJsonMember2048x2048(JsonMember2048x2048 jsonMember2048x2048){
		this.jsonMember2048x2048 = jsonMember2048x2048;
	}

	public JsonMember2048x2048 getJsonMember2048x2048(){
		return jsonMember2048x2048;
	}

	public void setJsonMember1536x1536(JsonMember1536x1536 jsonMember1536x1536){
		this.jsonMember1536x1536 = jsonMember1536x1536;
	}

	public JsonMember1536x1536 getJsonMember1536x1536(){
		return jsonMember1536x1536;
	}

	public void setMedium(Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	public void setTwentytwentyFullscreen(TwentytwentyFullscreen twentytwentyFullscreen){
		this.twentytwentyFullscreen = twentytwentyFullscreen;
	}

	public TwentytwentyFullscreen getTwentytwentyFullscreen(){
		return twentytwentyFullscreen;
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
			"Images{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",post-thumbnail = '" + postThumbnail + '\'' + 
			",2048x2048 = '" + jsonMember2048x2048 + '\'' + 
			",1536x1536 = '" + jsonMember1536x1536 + '\'' + 
			",medium = '" + medium + '\'' + 
			",twentytwenty-fullscreen = '" + twentytwentyFullscreen + '\'' + 
			",full = '" + full + '\'' + 
			"}";
		}
}