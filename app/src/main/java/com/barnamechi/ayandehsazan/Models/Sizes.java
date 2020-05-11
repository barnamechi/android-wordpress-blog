package com.barnamechi.ayandehsazan.Models;

import com.google.gson.annotations.SerializedName;

public class Sizes{

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("shop_thumbnail")
	private ShopThumbnail shopThumbnail;

	@SerializedName("large")
	private Large large;

	@SerializedName("profile_300")
	private Profile300 profile300;

	@SerializedName("woocommerce_gallery_thumbnail")
	private WoocommerceGalleryThumbnail woocommerceGalleryThumbnail;

	@SerializedName("woocommerce_thumbnail")
	private WoocommerceThumbnail woocommerceThumbnail;

	@SerializedName("jannah-image-large")
	private JannahImageLarge jannahImageLarge;

	@SerializedName("jannah-image-grid")
	private JannahImageGrid jannahImageGrid;

	@SerializedName("medium")
	private Medium medium;

	@SerializedName("profile_150")
	private Profile150 profile150;

	@SerializedName("jannah-image-post")
	private JannahImagePost jannahImagePost;

	@SerializedName("profile_96")
	private Profile96 profile96;

	@SerializedName("woocommerce_single")
	private WoocommerceSingle woocommerceSingle;

	@SerializedName("shop_single")
	private ShopSingle shopSingle;

	@SerializedName("medium_large")
	private MediumLarge mediumLarge;

	@SerializedName("jannah-image-full")
	private JannahImageFull jannahImageFull;

	@SerializedName("shop_catalog")
	private ShopCatalog shopCatalog;

	@SerializedName("profile_48")
	private Profile48 profile48;

	@SerializedName("jannah-image-small")
	private JannahImageSmall jannahImageSmall;

	@SerializedName("profile_24")
	private Profile24 profile24;

	@SerializedName("full")
	private Full full;

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setShopThumbnail(ShopThumbnail shopThumbnail){
		this.shopThumbnail = shopThumbnail;
	}

	public ShopThumbnail getShopThumbnail(){
		return shopThumbnail;
	}

	public void setLarge(Large large){
		this.large = large;
	}

	public Large getLarge(){
		return large;
	}

	public void setProfile300(Profile300 profile300){
		this.profile300 = profile300;
	}

	public Profile300 getProfile300(){
		return profile300;
	}

	public void setWoocommerceGalleryThumbnail(WoocommerceGalleryThumbnail woocommerceGalleryThumbnail){
		this.woocommerceGalleryThumbnail = woocommerceGalleryThumbnail;
	}

	public WoocommerceGalleryThumbnail getWoocommerceGalleryThumbnail(){
		return woocommerceGalleryThumbnail;
	}

	public void setWoocommerceThumbnail(WoocommerceThumbnail woocommerceThumbnail){
		this.woocommerceThumbnail = woocommerceThumbnail;
	}

	public WoocommerceThumbnail getWoocommerceThumbnail(){
		return woocommerceThumbnail;
	}

	public void setJannahImageLarge(JannahImageLarge jannahImageLarge){
		this.jannahImageLarge = jannahImageLarge;
	}

	public JannahImageLarge getJannahImageLarge(){
		return jannahImageLarge;
	}

	public void setJannahImageGrid(JannahImageGrid jannahImageGrid){
		this.jannahImageGrid = jannahImageGrid;
	}

	public JannahImageGrid getJannahImageGrid(){
		return jannahImageGrid;
	}

	public void setMedium(Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	public void setProfile150(Profile150 profile150){
		this.profile150 = profile150;
	}

	public Profile150 getProfile150(){
		return profile150;
	}

	public void setJannahImagePost(JannahImagePost jannahImagePost){
		this.jannahImagePost = jannahImagePost;
	}

	public JannahImagePost getJannahImagePost(){
		return jannahImagePost;
	}

	public void setProfile96(Profile96 profile96){
		this.profile96 = profile96;
	}

	public Profile96 getProfile96(){
		return profile96;
	}

	public void setWoocommerceSingle(WoocommerceSingle woocommerceSingle){
		this.woocommerceSingle = woocommerceSingle;
	}

	public WoocommerceSingle getWoocommerceSingle(){
		return woocommerceSingle;
	}

	public void setShopSingle(ShopSingle shopSingle){
		this.shopSingle = shopSingle;
	}

	public ShopSingle getShopSingle(){
		return shopSingle;
	}

	public void setMediumLarge(MediumLarge mediumLarge){
		this.mediumLarge = mediumLarge;
	}

	public MediumLarge getMediumLarge(){
		return mediumLarge;
	}

	public void setJannahImageFull(JannahImageFull jannahImageFull){
		this.jannahImageFull = jannahImageFull;
	}

	public JannahImageFull getJannahImageFull(){
		return jannahImageFull;
	}

	public void setShopCatalog(ShopCatalog shopCatalog){
		this.shopCatalog = shopCatalog;
	}

	public ShopCatalog getShopCatalog(){
		return shopCatalog;
	}

	public void setProfile48(Profile48 profile48){
		this.profile48 = profile48;
	}

	public Profile48 getProfile48(){
		return profile48;
	}

	public void setJannahImageSmall(JannahImageSmall jannahImageSmall){
		this.jannahImageSmall = jannahImageSmall;
	}

	public JannahImageSmall getJannahImageSmall(){
		return jannahImageSmall;
	}

	public void setProfile24(Profile24 profile24){
		this.profile24 = profile24;
	}

	public Profile24 getProfile24(){
		return profile24;
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
			",shop_thumbnail = '" + shopThumbnail + '\'' + 
			",large = '" + large + '\'' + 
			",profile_300 = '" + profile300 + '\'' + 
			",woocommerce_gallery_thumbnail = '" + woocommerceGalleryThumbnail + '\'' + 
			",woocommerce_thumbnail = '" + woocommerceThumbnail + '\'' + 
			",jannah-image-large = '" + jannahImageLarge + '\'' + 
			",jannah-image-grid = '" + jannahImageGrid + '\'' + 
			",medium = '" + medium + '\'' + 
			",profile_150 = '" + profile150 + '\'' + 
			",jannah-image-post = '" + jannahImagePost + '\'' + 
			",profile_96 = '" + profile96 + '\'' + 
			",woocommerce_single = '" + woocommerceSingle + '\'' + 
			",shop_single = '" + shopSingle + '\'' + 
			",medium_large = '" + mediumLarge + '\'' + 
			",jannah-image-full = '" + jannahImageFull + '\'' + 
			",shop_catalog = '" + shopCatalog + '\'' + 
			",profile_48 = '" + profile48 + '\'' + 
			",jannah-image-small = '" + jannahImageSmall + '\'' + 
			",profile_24 = '" + profile24 + '\'' + 
			",full = '" + full + '\'' + 
			"}";
		}
}