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
import com.barnamechi.ayandehsazan.Models.User.SingleRegularUser.SingleRegularUser;
import com.barnamechi.ayandehsazan.Models.User.register.RegisterUser;
import com.barnamechi.ayandehsazan.Models.nonce.Nonce;
import com.barnamechi.ayandehsazan.R;
import com.barnamechi.ayandehsazan.RegisterLoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterFragment extends Fragment {
    private ImageView logoImage;

    private EditText userNameET;
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText emailET;
    private EditText phoneNumberET;
    private EditText passwordET;
    private UserDatabaseHandler userDatabaseHandler;


    private Button registerBTN;

    private TextView doHaveAccountTV;

    private Context context;

    private String nonce;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String authToken;
    private String avatarURL;
    private int userID;

    public RegisterFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserNonce("user", "register");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(rootView);

        return rootView;
    }


    private void initViews(View rootView) {
        logoImage = rootView.findViewById(R.id.register_fragment_logo_iv);
        logoImage.setImageResource(R.mipmap.logo);

        userNameET = rootView.findViewById(R.id.register_fragment_user_name_et);
        firstNameET = rootView.findViewById(R.id.register_fragment_first_name_et);
        lastNameET = rootView.findViewById(R.id.register_fragment_last_name_et);
        emailET = rootView.findViewById(R.id.register_fragment_email_et);
//        phoneNumberET = rootView.findViewById(R.id.register_fragment_phone_number_et);
        passwordET = rootView.findViewById(R.id.register_fragment_password_et);

        registerBTN = rootView.findViewById(R.id.register_fragment_register_btn);
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameET.getText().toString();
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                firstName = firstNameET.getText().toString();
                lastName = lastNameET.getText().toString();
                registerUser("cool", userName, email, nonce, password, firstName, lastName);
            }
        });

        doHaveAccountTV = rootView.findViewById(R.id.register_fragment_do_have_account);
        doHaveAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterLoginActivity) getActivity()).startLoginFragment();
            }
        });
    }

    private void getUserNonce(String controller, String method) {
        Api api = createRetrofitCoreApi();
        Call<Nonce> call = api.getUserNonce(controller, method);
        call.enqueue(new Callback<Nonce>() {
            @Override
            public void onResponse(Call<Nonce> call, Response<Nonce> response) {
                if (response.body() != null) {
                    nonce = response.body().getNonce();
                } else {
                    Toast.makeText(context, R.string.no_response_from_server, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Nonce> call, Throwable t) {
                Toast.makeText(context, R.string.check_internet_connection, Toast.LENGTH_LONG).show();

            }
        });

    }

    private void registerUser(String insecure, final String userName, String email, String nonce, String userPass, final String firstName, String lastName) {
//        SharedPreferences sharedPreferences = getSharedPreferences("ayandesazansp", Activity.MODE_PRIVATE);
        Api api = createRetrofitUserApi();
        Call<RegisterUser> call = api.registerUser(insecure, userName, email, nonce, userPass, firstName, lastName);
        call.enqueue(new Callback<RegisterUser>() {
            @Override
            public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("ok")) {
                        userID = response.body().getUserId();
                        authToken = response.body().getCookie();


                        Toast.makeText(context, R.string.submitted_successfully, Toast.LENGTH_LONG).show();
                        getAvatarURL(userID);
                    }
                } else {
                    Toast.makeText(context, R.string.check_entry_data, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterUser> call, Throwable t) {
                Toast.makeText(context, R.string.check_internet_connection, Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getAvatarURL(final int userID) {
        Api api = createRetrofitApi();
        Call<SingleRegularUser> call = api.getSingleUSer(userID);
        call.enqueue(new Callback<SingleRegularUser>() {
            @Override
            public void onResponse(Call<SingleRegularUser> call, Response<SingleRegularUser> response) {
                if (response.body() != null) {
                    avatarURL = response.body().getAvatarUrls().getJsonMember96();

                }

                storeUserData(authToken, userName, firstName, userID, email, avatarURL);


            }

            @Override
            public void onFailure(Call<SingleRegularUser> call, Throwable t) {
                Toast.makeText(context, R.string.check_internet_connection, Toast.LENGTH_LONG).show();

            }
        });

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

    private void startMainActivity() {
        ((RegisterLoginActivity) getActivity()).startMainActivity(userID);
    }


    private Api createRetrofitCoreApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.CORE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }


}
