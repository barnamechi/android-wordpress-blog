package com.barnamechi.ayandehsazan.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.barnamechi.ayandehsazan.Helper.UserDatabaseHandler;
import com.barnamechi.ayandehsazan.Helper.UserDatabaseModel;
import com.barnamechi.ayandehsazan.MainActivity;
import com.barnamechi.ayandehsazan.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public class ProfileFragment extends Fragment {

    private TextView firstNameET;
    private TextView userFirstNameET;
    private TextView lastNameET;
    private TextView userLastNameET;
    private TextView emailET;
    private TextView userEmailET;
    private TextView userNameTV;

    private ImageView profilePhoto;

    private Button editProfileBTN;
    private Button logOutBTN;

    private Context context;

    private int userId;
    private String avatarUrl;
    private String userEmail;
    private String userDisplayName;
    private String userName;

    private RequestManager glide;


    public ProfileFragment(Context context, int userId) {
        this.context = context;
        this.userId = userId;
        glide = Glide.with(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserFromDatabase(userId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        initViews(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void initViews(View rootView) {
        firstNameET = rootView.findViewById(R.id.profile_fragment_first_name_tv);
        userFirstNameET = rootView.findViewById(R.id.profile_fragment_user_first_name_tv);
//        lastNameET = rootView.findViewById(R.id.profile_fragment_last_name_tv);
//        userLastNameET = rootView.findViewById(R.id.profile_fragment_user_last_name_tv);
        emailET = rootView.findViewById(R.id.profile_fragment_email_tv);
        userEmailET = rootView.findViewById(R.id.profile_fragment_user_email_tv);
        userNameTV = rootView.findViewById(R.id.profile_fragment_username_tv);

        profilePhoto = rootView.findViewById(R.id.profile_fragment_profile_photo_iv);

        editProfileBTN = rootView.findViewById(R.id.profile_fragment_profile_edit_btn);

        logOutBTN = rootView.findViewById(R.id.profile_fragment_sign_out_btn);
        logOutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).logoutUser();
            }
        });
    }

    private void getUserFromDatabase(int userID) {
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(context);
        UserDatabaseModel userDatabaseModel = userDatabaseHandler.getUserFromDatabase(userID);
        avatarUrl = userDatabaseModel.getUserAvatarURL();
        userEmail = userDatabaseModel.getUseremail();
        userDisplayName = userDatabaseModel.getDisplayname();
        userName = userDatabaseModel.getUsername();
    }

    private void showData() {
        if (avatarUrl == null) {
            profilePhoto.setImageResource(R.mipmap.anonymous_profile);
        } else {
            glide.load(avatarUrl).into(profilePhoto);
        }
        userFirstNameET.setText(userDisplayName);
        userEmailET.setText(userEmail);
        userNameTV.setText(userName);

    }
}

