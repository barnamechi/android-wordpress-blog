package com.barnamechi.ayandehsazan.PluginModels;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("ignore_sticky_posts")
	private boolean ignoreStickyPosts;

	public void setIgnoreStickyPosts(boolean ignoreStickyPosts){
		this.ignoreStickyPosts = ignoreStickyPosts;
	}

	public boolean isIgnoreStickyPosts(){
		return ignoreStickyPosts;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"ignore_sticky_posts = '" + ignoreStickyPosts + '\'' + 
			"}";
		}
}