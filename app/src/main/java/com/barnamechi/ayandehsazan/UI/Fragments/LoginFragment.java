package com.barnamechi.ayandehsazan.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.barnamechi.ayandehsazan.API.Api;
import com.barnamechi.ayandehsazan.API.EndPoints;
import com.barnamechi.ayandehsazan.Helper.UserDatabaseHandler;
import com.barnamechi.ayandehsazan.Helper.UserDatabaseModel;
import com.barnamechi.ayandehsazan.Models.User.AuthCoockie;
import com.barnamechi.ayandehsazan.R;
import com.barnamechi.ayandehsazan.RegisterLoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    private ImageView logoImage;

    private EditText userNameET;
    private EditText passwordET;

    private Button loginBTN;

    private TextView dontHaveaccTV;

    private String userName;
    private String password;
    private String authToken;
    private String profileUserName;
    private String profileName;
    private String avatarURL;
    private int userID;
    private String profileEmail;

    private UserDatabaseHandler userDatabaseHandler;

    private Context context;

    public LoginFragment(Context context) {
        this.context = context;
        // Required empty public constructor
    }

//
//    public static LoginFragment newInstance(String param1, String param2) {
//        LoginFragment fragment = new LoginFragment();
//        Bundle args = new Bundle();
////        args.putString(ARG_PARAM1, param1);
////        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView) {
        logoImage = rootView.findViewById(R.id.login_fragment_logo_iv);

        userNameET = rootView.findViewById(R.id.login_fragment_user_name_et);
        passwordET = rootView.findViewById(R.id.login_fragment_password_et);

        loginBTN = rootView.findViewById(R.id.login_fragment_login_btn);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameET.getText().toString();
                password = passwordET.getText().toString();

                checkUserEntry(userName, password);
            }
        });

        dontHaveaccTV = rootView.findViewById(R.id.login_fragment_dont_have_account);
        dontHaveaccTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterFragment();
            }
        });

    }

    private void checkUserEntry(final String username, String password) {
        Api api = createRetrofitApi();

        Call<AuthCoockie> call = api.generateAuthCoockie("cool", username, password);
        call.enqueue(new Callback<AuthCoockie>() {
            @Override
            public void onResponse(Call<AuthCoockie> call, Response<AuthCoockie> response) {
                if (response.body() != null) {
                    authToken = response.body().getCookie();
                    profileUserName = response.body().getUser().getUsername();
                    profileName = response.body().getUser().getDisplayname();
                    userID = response.body().getUser().getId();
                    profileEmail = response.body().getUser().getEmail();
                    avatarURL = response.body().getUser().getAvatar();

                    Toast.makeText(context, R.string.logged_in_successfully, Toast.LENGTH_SHORT).show();
                    storeUserData(authToken, profileUserName, profileName, userID, profileEmail, avatarURL);

                } else {
                    Toast.makeText(context, R.string.no_response_from_server, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthCoockie> call, Throwable t) {
                Toast.makeText(context, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startRegisterFragment() {
        ((RegisterLoginActivity) getActivity()).startRegisterFragment();
    }

    private void startMainActivity() {
        ((RegisterLoginActivity) getActivity()).startMainActivity(userID);
    }

    private void storeUserData(String authToken, String profileUserName, String profileName, int userID, String profileEmail, String avatarURL) {

//        SharedPreference here
        ((RegisterLoginActivity) getActivity()).storeUserDataWithSharedPreferences(authToken, userID);

//        Database here
        userDatabaseHandler = new UserDatabaseHandler(context);
        UserDatabaseModel userDatabaseModel = new UserDatabaseModel(userID, profileUserName, profileName, profileEmail, avatarURL);
        userDatabaseHandler.deleteAll();
        userDatabaseHandler.addUserDatabase(userDatabaseModel);

        Toast.makeText(context, R.string.stores_successfully, Toast.LENGTH_SHORT).show();

        startMainActivity();
    }

    private Api createRetrofitApi() {
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

}
