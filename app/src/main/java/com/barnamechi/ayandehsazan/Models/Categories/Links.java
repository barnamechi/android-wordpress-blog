package com.barnamechi.ayandehsazan.Models.Categories;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("wp:post_type")
	private List<WpPostTypeItem> wpPostType;

	@SerializedName("curies")
	private List<CuriesItem> curies;

	@SerializedName("about")
	private List<AboutItem> about;

	@SerializedName("self")
	private List<SelfItem> self;

	@SerializedName("collection")
	private List<CollectionItem> collection;

	public void setWpPostType(List<WpPostTypeItem> wpPostType){
		this.wpPostType = wpPostType;
	}

	public List<WpPostTypeItem> getWpPostType(){
		return wpPostType;
	}

	public void setCuries(List<CuriesItem> curies){
		this.curies = curies;
	}

	public List<CuriesItem> getCuries(){
		return curies;
	}

	public void setAbout(List<AboutItem> about){
		this.about = about;
	}

	public List<AboutItem> getAbout(){
		return about;
	}

	public void setSelf(List<SelfItem> self){
		this.self = self;
	}

	public List<SelfItem> getSelf(){
		return self;
	}

	public void setCollection(List<CollectionItem> collection){
		this.collection = collection;
	}

	public List<CollectionItem> getCollection(){
		return collection;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"wp:post_type = '" + wpPostType + '\'' + 
			",curies = '" + curies + '\'' + 
			",about = '" + about + '\'' + 
			",self = '" + self + '\'' + 
			",collection = '" + collection + '\'' + 
			"}";
		}
}