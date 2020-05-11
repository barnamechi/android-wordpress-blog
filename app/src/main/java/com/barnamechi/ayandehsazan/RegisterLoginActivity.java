package com.barnamechi.ayandehsazan;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.barnamechi.ayandehsazan.API.Api;
import com.barnamechi.ayandehsazan.API.EndPoints;
import com.barnamechi.ayandehsazan.Models.Categories.Categories;
import com.barnamechi.ayandehsazan.UI.Fragments.LoginFragment;
import com.barnamechi.ayandehsazan.UI.Fragments.RegisterFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterLoginActivity extends AppCompatActivity {

    private static final String TAG = "RegisterLoginActivity";

    private ArrayList<String> categoryNames;
    private ArrayList<Integer> categoryIDs;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        init();
        getDataFromIntent();
        getDataFromSharedPref();
    }

    private void getDataFromIntent() {
        Intent intent = RegisterLoginActivity.this.getIntent();
        categoryNames = intent.getStringArrayListExtra("categoryNames");
        categoryIDs = intent.getIntegerArrayListExtra("categoryIDs");
        if (categoryNames == null | categoryIDs == null) {
            getCategories();
        }
    }

    private void init() {
        categoryNames = new ArrayList<>();
        categoryIDs = new ArrayList<>();
    }

    private void getDataFromSharedPref() {
        sharedPreferences = getSharedPreferences("ayandesazansp", Activity.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("user_id", 0);
        String authToken = sharedPreferences.getString("auth_token", null);

        if (userID != 0 & authToken != null) {

            startMainActivity(userID);
        } else {

            startLoginFragment();
        }
    }

    public void startLoginFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment(RegisterLoginActivity.this);
        fragmentTransaction.add(R.id.login_register_activity_fragment_container, loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void startRegisterFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        RegisterFragment registerFragment = new RegisterFragment(RegisterLoginActivity.this);
        fragmentTransaction.add(R.id.login_register_activity_fragment_container, registerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void startMainActivity(int userID) {
        Intent intent = new Intent(RegisterLoginActivity.this, MainActivity.class);
        intent.putExtra("RecievedUserID", userID);
        intent.putStringArrayListExtra("categoryNames", categoryNames);
        intent.putIntegerArrayListExtra("categoryIDs", categoryIDs);
        RegisterLoginActivity.this.startActivity(intent);
    }

    public void storeUserDataWithSharedPreferences(String authToken, int userID) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("auth_token", authToken);
        editor.putInt("user_id", userID);
        editor.commit();
    }

    private void getCategories() {
        categoryIDs = new ArrayList<>();
        categoryNames = new ArrayList<>();
        Api api = createRetrofitApi();
        Call<List<Categories>> call = api.getCategories(100);
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
//                categoryIDs.clear();
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        categoryIDs.add(response.body().get(i).getId());
                        categoryNames.add(response.body().get(i).getName());
                    }
                    getDataFromSharedPref();
                } else {
                    Toast.makeText(RegisterLoginActivity.this, R.string.no_response_from_server, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Toast.makeText(RegisterLoginActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
            }
        });
//
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


    //2Ta fragment loginfragment va registerfragment baraye in hastan
}
