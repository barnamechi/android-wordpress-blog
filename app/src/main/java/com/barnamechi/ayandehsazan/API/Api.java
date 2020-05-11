package com.barnamechi.ayandehsazan.API;

import com.barnamechi.ayandehsazan.Models.Categories.Categories;
import com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia.RegularSingleMedia;
import com.barnamechi.ayandehsazan.Models.Posts.Posts;
import com.barnamechi.ayandehsazan.Models.Posts.SinglePost.SinglePost;
import com.barnamechi.ayandehsazan.Models.User.AuthCoockie;
import com.barnamechi.ayandehsazan.Models.User.SingleRegularUser.SingleRegularUser;
import com.barnamechi.ayandehsazan.Models.User.register.RegisterUser;
import com.barnamechi.ayandehsazan.Models.nonce.Nonce;
import com.barnamechi.ayandehsazan.PluginModels.PluginPost;
import com.barnamechi.ayandehsazan.PluginModels.categories.PluginCategoryPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("posts?")
    Call<List<Posts>> getPosts(@Query("categories") Integer categories);

    @GET("posts?")
    Call<List<Posts>> getPostsWithPage(
            @Query("categories") Integer categories,
            @Query("page") Integer page);

    @GET("posts/{id}")
    Call<SinglePost> getSinglePost(@Path("id") Integer id);

    @GET("users/{id}")
    Call<SingleRegularUser> getSingleUSer(@Path("id") int id);

    @GET("categories?")
    Call<List<Categories>> getCategories(@Query("per_page") Integer per_page);

    @GET("categories?")
    Call<Categories> getCategory(@Query("per_page") Integer per_page);

    @GET("media?")
    Call<List<RegularSingleMedia>> getMedias(@Query("include") String include);

    @POST("generate_auth_cookie?")
    Call<AuthCoockie> generateAuthCoockie(
            @Query("insecure") String insecure,
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("get_nonce?")
    Call<Nonce> getUserNonce(
            @Query("controller") String controller,
            @Query("method") String method
    );

    @POST("register")
    Call<RegisterUser> registerUser(
            @Query("insecure") String insecure,
            @Query("username") String username,
            @Query("email") String email,
            @Query("nonce") String nonce,
            @Query("user_pass") String user_pass,
            @Query("first_name") String first_name,
            @Query("last_name") String last_name
    );

    @GET("get_category_posts?")
    Call<List<PluginCategoryPosts>> getPluginPosts(@Query("id") Integer id);

//    @GET("media")
//    Call<List<Media>> getMedias();
//
//    @GET("media?")
//    Call<Media> getMedias(@Query("include") String include);
}
