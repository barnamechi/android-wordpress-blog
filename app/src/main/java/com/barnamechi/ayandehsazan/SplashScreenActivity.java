package com.barnamechi.ayandehsazan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.barnamechi.ayandehsazan.API.Api;
import com.barnamechi.ayandehsazan.API.EndPoints;
import com.barnamechi.ayandehsazan.Models.Categories.Categories;
import com.barnamechi.ayandehsazan.Models.User.SingleRegularUser.SingleRegularUser;
import com.barnamechi.ayandehsazan.Models.User.SingleUser.SingleUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView logoImage;
    private int userID;

    private ArrayList<Integer> categoryIDs;
    private ArrayList<String> categoryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCategories();
    }

    private void deleteDataFromSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("ayandesazansp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        startLoginActivity();
    }

    private void getDataFromSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("ayandesazansp", Activity.MODE_PRIVATE);
        userID = sharedPreferences.getInt("user_id", 0);
        checkUserAvailability(userID);
    }

    private void getCategories() {
        Api api = createRetrofitApi();
        Call<List<Categories>> call = api.getCategories(100);
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                categoryIDs.clear();
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        categoryIDs.add(response.body().get(i).getId());
                        categoryNames.add(response.body().get(i).getName());
                    }
                    getDataFromSharedPref();
                } else {
                    Toast.makeText(SplashScreenActivity.this, R.string.no_response_from_server, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Toast.makeText(SplashScreenActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
            }
        });
//
    }

    private void checkUserAvailability(int userID) {
        Api api = createRetrofitUserApi();
        Call<SingleRegularUser> call = api.getSingleUSer(userID);
        call.enqueue(new Callback<SingleRegularUser>() {
            @Override
            public void onResponse(Call<SingleRegularUser> call, Response<SingleRegularUser> response) {
                if (response.body() != null) {
                    startLoginActivity();
                } else {
                    deleteDataFromSharedPref();
                }
            }

            @Override
            public void onFailure(Call<SingleRegularUser> call, Throwable t) {
                deleteDataFromSharedPref();
            }
        });
//        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(SplashScreenActivity.this);
//        UserDatabaseModel userDatabaseModel = userDatabaseHandler.getUserFromDatabase(userID);
////         = userDatabaseModel.getUserAvatarURL();


    }


    private void initViews() {
        logoImage = findViewById(R.id.splash_screen_logo_iv);
        categoryIDs = new ArrayList<>();
        categoryNames = new ArrayList<>();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, RegisterLoginActivity.class);
        intent.putStringArrayListExtra("categoryNames", categoryNames);
        intent.putIntegerArrayListExtra("categoryIDs", categoryIDs);
        SplashScreenActivity.this.startActivity(intent);
    }

    private Api createRetrofitUserApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.USER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    private Api createRetrofitApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    private void checkInternetConnection() {
        startLoginActivity();
    }
}
