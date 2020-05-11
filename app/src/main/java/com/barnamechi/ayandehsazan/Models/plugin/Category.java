package com.barnamechi.ayandehsazan.Models.plugin;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Category{

	@SerializedName("count")
	private int count;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("status")
	private String status;

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"count = '" + count + '\'' + 
			",categories = '" + categories + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}