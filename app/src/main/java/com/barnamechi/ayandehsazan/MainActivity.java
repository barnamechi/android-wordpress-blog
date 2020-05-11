package com.barnamechi.ayandehsazan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barnamechi.ayandehsazan.API.Api;
import com.barnamechi.ayandehsazan.API.EndPoints;
import com.barnamechi.ayandehsazan.Helper.UserDatabaseHandler;
import com.barnamechi.ayandehsazan.Helper.UserDatabaseModel;
import com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia.RegularSingleMedia;
import com.barnamechi.ayandehsazan.Models.Posts.Posts;
import com.barnamechi.ayandehsazan.PluginModels.PluginPost;
import com.barnamechi.ayandehsazan.PluginModels.categories.PluginCategoryPosts;
import com.barnamechi.ayandehsazan.UI.Adapters.LatestNewsRecyclerAdapter;
import com.barnamechi.ayandehsazan.UI.Adapters.MembersRecyclerAadpter;
import com.barnamechi.ayandehsazan.UI.Adapters.SocialMediaRecyclerAdapter;
import com.barnamechi.ayandehsazan.UI.ArchiveActivity;
import com.barnamechi.ayandehsazan.UI.Fragments.ProfileFragment;
import com.barnamechi.ayandehsazan.UI.Fragments.SinglePostFragment;
import com.barnamechi.ayandehsazan.UI.Fragments.WebViewFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Menu navMenu;
    private MenuItem navMenuItem;

    private ImageView profileImage;
    private ImageView logoImage;
    private ImageView navLogoImage;
    private ImageView navProfileImage;

    private TextView latestNewsTitle;
    private TextView socislMediasTitle;
    private TextView introduceMembersTitle;
    private TextView designedByBarnamechiTitle;
    private TextView latestNewsShowMore;
    private TextView membersShowMore;
    private TextView latestPleaseWaitTV;
    private TextView socialPleaseWaitTV;
    private TextView membersPleaseWaitTV;

    private Button newsletterBtn;

    private RecyclerView latestNewsReycler;
    private RecyclerView membersRecycler;
    private RecyclerView socialMediaRecycler;
    private RecyclerView mainRecyclerView;


    private List<Integer> categoryIDs;
    private List<Integer> postIDs;
    private List<Integer> socialIDs;
    private List<Integer> memberIDs;

    private List<String> latestNewsMediaIDs;
    private List<String> socialMediadMediaIDs;
    private List<String> categoryNames;
    private List<String> latestNewsTitles;
    private List<String> latestNewsImageURLs;
    private List<String> socialMediasTitles;
    private List<String> socialMediasDescriptions;
    private List<String> socialMediasImageUrls;
    private List<String> membersImageUrls;
    private List<String> memberMediaIDs;

    private int userID;
    private String avatarUrl;
    private RequestManager glide;
    private View headerView;
    private Boolean IS_ACTIVITY_STARTED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IS_ACTIVITY_STARTED = false;
        initViews();
        getUserIdFromIntent();
        setProfilePicture(avatarUrl);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hideSocialPleaseWait();
        hideLatestNewsPleaseWait();
        hideMembersPleaseWait();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!IS_ACTIVITY_STARTED) {
            getCategories();
            IS_ACTIVITY_STARTED = true;
        }
    }

    @SuppressLint("ResourceType")
    private void initViews() {
        categoryIDs = new ArrayList<>();
        categoryNames = new ArrayList<>();
        latestNewsTitles = new ArrayList<>();
        latestNewsImageURLs = new ArrayList<>();
        latestNewsMediaIDs = new ArrayList<>();
        socialMediasTitles = new ArrayList<>();
        socialMediasImageUrls = new ArrayList<>();
        socialMediasDescriptions = new ArrayList<>();
        socialMediadMediaIDs = new ArrayList<>();
        membersImageUrls = new ArrayList<>();
        memberMediaIDs = new ArrayList<>();
        postIDs = new ArrayList<>();
        socialIDs = new ArrayList<>();
        memberIDs = new ArrayList<>();


        drawerLayout = findViewById(R.id.main_activity_fragment_container);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.main_activity_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_menu_account:
                        openProfileFragment();
                        break;
                    case R.id.nav_menu_podcast:
                        openPodcastFragment();
                        break;
                    case R.id.nav_menu_library:
                        openLibraryFragment();
                        break;
                    case R.id.nav_menu_about:
                        openAboutFragment();
                        break;
//                    case R.id.nav_menu_contact:
//                        openContactFragment();
//                        break;
                    case R.id.nav_menu_logout:
                        logoutUser();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        navMenu = navigationView.getMenu();
        navMenuItem = navMenu.findItem(R.id.nav_menu_account);
//        navMenuItem.setIcon(R.mipmap.ic_profile);
//        navMenuItem.setIcon(R.drawable.ic_profile);
        navMenuItem.setVisible(true);


        profileImage = findViewById(R.id.main_activity_profile_picture);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        navProfileImage = findViewById(R.id.navigation_view_logo_image_iv);

        logoImage = findViewById(R.id.main_activity_logo);

        latestNewsTitle = findViewById(R.id.main_activity_latest_news_title_tv);
        latestNewsTitle.setTextColor(R.color.colorPrimary);
        socislMediasTitle = findViewById(R.id.main_activity_social_medias_tv);
        socislMediasTitle.setTextColor(R.color.colorPrimary);
        introduceMembersTitle = findViewById(R.id.main_activity_introduce_members_title);
        introduceMembersTitle.setTextColor(R.color.colorPrimary);
        designedByBarnamechiTitle = findViewById(R.id.designed_by_barnamechi_tv);
        latestPleaseWaitTV = findViewById(R.id.main_activity_latest_please_wait_tv);
        membersPleaseWaitTV = findViewById(R.id.main_activity_members_please_wait_tv);
        socialPleaseWaitTV = findViewById(R.id.main_activity_social_please_wait_tv);

        designedByBarnamechiTitle.setTextColor(R.color.grayBackground);
        designedByBarnamechiTitle.setVisibility(View.GONE);
        latestNewsShowMore = findViewById(R.id.main_activity_latest_news_show_more);
        membersShowMore = findViewById(R.id.main_activity_introduce_members_show_more);


        newsletterBtn = findViewById(R.id.main_activity_newsletter_btn);

        latestNewsReycler = findViewById(R.id.main_activity_latest_news_recycler);
        membersRecycler = findViewById(R.id.main_activity_introduce_members_recycler);
        socialMediaRecycler = findViewById(R.id.main_activity_social_medias_recycler);
//        mainRecyclerView = findViewById(R.id.full_directory_fragment_content_recycler_view);

        headerView = navigationView.getHeaderView(0);
        navProfileImage = headerView.findViewById(R.id.navigation_view_profile_photo_iv);
        navLogoImage = headerView.findViewById(R.id.navigation_view_logo_image_iv);

        glide = Glide.with(MainActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void getCategories() {
        showPleaseWait();
        getNews(categoryIDs, categoryNames);
        getSocials();
        getMembers();
//        getPluginPosts(categoryIDs,categoryNames);

    }

//    private void getPluginPosts(List<Integer> categoryIDs, List<String> categoryNames) {
//        Api api =createRetrofitCoreApi();
//        Call<List<PluginCategoryPosts>> call = api.getPluginPosts(categoryIDs.get(categoryNames.indexOf("اخبار")));
//        call.enqueue(new Callback<List<PluginCategoryPosts>>() {
//            @Override
//            public void onResponse(Call<List<PluginCategoryPosts>> call, Response<List<PluginCategoryPosts>> response) {
//                if (response.body() != null){
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<PluginCategoryPosts>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void getNews(final List<Integer> categoryIDs, final List<String> categoryNames) {
        postIDs.clear();
        Api api = createRetrofitApi();
        Call<List<Posts>> call = api.getPosts(categoryIDs.get(categoryNames.indexOf("اخبار")));
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
//                         if (response.body().get(i).getCategories().toString().equals(categoryIDs.get(categoryNames.indexOf()))){
                        latestNewsTitles.add(response.body().get(i).getTitle().getRendered());
                        latestNewsMediaIDs.add(String.valueOf(response.body().get(i).getFeaturedMedia()));
                        postIDs.add(response.body().get(i).getId());
//                         }
                    }
                    getMedias(latestNewsMediaIDs, latestNewsImageURLs, "NEWS");
                    latestNewsShowMore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startArchiveActivity(categoryIDs.get(categoryNames.indexOf("اخبار")), 1);
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, R.string.no_response_from_server, Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getMedias(final List<String> mediaIDs, final List<String> imageUrls, final String recycler) {
        Api api = createRetrofitApi();
        String includes = stringBuilder(mediaIDs);
        Call<List<RegularSingleMedia>> callMedia = api.getMedias(includes);
        callMedia.enqueue(new Callback<List<RegularSingleMedia>>() {
            @Override
            public void onResponse(Call<List<RegularSingleMedia>> call, Response<List<RegularSingleMedia>> response) {
                if (response.body() != null) {

                    for (int j = 0; j < response.body().size(); j++) {
                        imageUrls.add(response.body().get(j).getGuid().getRendered());
                    }
                    switch (recycler) {
                        case "SOCIAL":
                            showSocialMediaRecyclerView();
                            break;
                        case "NEWS":
                            showLatestNewsRecyclerView();
                            break;
                        case "MEMBERS":
                            showMembersRecyclerView();
                            break;
                    }
                } else {
                    Toast.makeText(MainActivity.this, R.string.no_response_from_server, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RegularSingleMedia>> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getMembers() {
        Api api = createRetrofitApi();
        Call<List<Posts>> call = api.getPosts(categoryIDs.get(categoryNames.indexOf("لیدر")));
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.body() != null) {


                    for (int i = 0; i < response.body().size(); i++) {
                        memberMediaIDs.add(String.valueOf(response.body().get(i).getFeaturedMedia()));
                        memberIDs.add(response.body().get(i).getId());
                    }
                    getMedias(memberMediaIDs, membersImageUrls, "MEMBERS");
                    membersShowMore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startArchiveActivity(categoryIDs.get(categoryNames.indexOf("لیدر")), 1);
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });

    }

    private void getSocials() {
        Api api = createRetrofitApi();
        Call<List<Posts>> call = api.getPosts(categoryIDs.get(categoryNames.indexOf("شبکه اجتماعی")));
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.body() != null) {

                    for (int i = 0; i < response.body().size(); i++) {
                        socialMediasTitles.add(response.body().get(i).getTitle().getRendered());
                        socialMediasDescriptions.add(response.body().get(i).getExcerpt().getRendered());
                        socialMediadMediaIDs.add(String.valueOf(response.body().get(i).getFeaturedMedia()));
                        socialIDs.add(response.body().get(i).getId());
                    }
                    getMedias(socialMediadMediaIDs, socialMediasImageUrls, "SOCIAL");
                } else {
                    Toast.makeText(MainActivity.this, R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });

    }

    private void showLatestNewsRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        latestNewsReycler.setLayoutManager(layoutManager);
        LatestNewsRecyclerAdapter adapter = new LatestNewsRecyclerAdapter(this, latestNewsTitles, latestNewsImageURLs, postIDs);
        hideLatestNewsPleaseWait();
        latestNewsReycler.setAdapter(adapter);
    }

    private void showMembersRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        membersRecycler.setLayoutManager(gridLayoutManager);
        MembersRecyclerAadpter adapter = new MembersRecyclerAadpter(this, membersImageUrls, memberIDs);
        hideMembersPleaseWait();
        membersRecycler.setAdapter(adapter);
    }

    private void showSocialMediaRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        socialMediaRecycler.setLayoutManager(layoutManager);
        SocialMediaRecyclerAdapter adapter = new SocialMediaRecyclerAdapter(this, socialMediasTitles, socialMediasImageUrls, socialMediasDescriptions, socialIDs);
        hideSocialPleaseWait();
        socialMediaRecycler.setAdapter(adapter);
    }

    private void getUserIdFromIntent() {
        Intent intent = MainActivity.this.getIntent();
        userID = intent.getIntExtra("RecievedUserID", 0);
        categoryNames = intent.getStringArrayListExtra("categoryNames");
        categoryIDs = intent.getIntegerArrayListExtra("categoryIDs");
        getUserFromDatabase(userID);
    }

    private void getUserFromDatabase(int userID) {
        UserDatabaseHandler userDatabaseHandler = new UserDatabaseHandler(MainActivity.this);
        UserDatabaseModel userDatabaseModel = userDatabaseHandler.getUserFromDatabase(userID);
        avatarUrl = userDatabaseModel.getUserAvatarURL();
    }

    private void setProfilePicture(String avatarUrl) {
        if (avatarUrl == null) {
            profileImage.setImageResource(R.mipmap.anonymous_profile);
            navProfileImage.setImageResource(R.mipmap.anonymous_profile);
        } else {
            glide.load(avatarUrl).into(profileImage);
            glide.load(avatarUrl).into(navProfileImage);
        }
        navLogoImage.setImageResource(R.mipmap.logo);
        logoImage.setImageResource(R.mipmap.logo);

    }

    public void startSinglePostActivity(int postID, String imageURL) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SinglePostFragment singlePostFragment = new SinglePostFragment(MainActivity.this, postID, imageURL);
        fragmentTransaction.add(R.id.main_activity_fragment_container, singlePostFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void startArchiveActivity(int categoryID, int pageNumber) {
        Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
        intent.putExtra("RecievedCategoryID", categoryID);
        intent.putExtra("RecievedPageNumber", pageNumber);
        MainActivity.this.startActivity(intent);

    }

    private void startRegisterLoginActivity() {
        Intent intent = new Intent(MainActivity.this, RegisterLoginActivity.class);
        MainActivity.this.startActivity(intent);
    }

    private void openContactFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WebViewFragment webViewFragment = new WebViewFragment(MainActivity.this, EndPoints.CONTACT_US);
        fragmentTransaction.add(R.id.main_activity_fragment_container, webViewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openAboutFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WebViewFragment webViewFragment = new WebViewFragment(MainActivity.this, EndPoints.ABOUT_US);
        fragmentTransaction.add(R.id.main_activity_fragment_container, webViewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openLibraryFragment() {
        startArchiveActivity(categoryIDs.get(categoryNames.indexOf("کتابخانه")), 1);
    }

    private void openPodcastFragment() {
        startArchiveActivity(categoryIDs.get(categoryNames.indexOf("پادکست")), 1);
    }

    private void openProfileFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfileFragment profileFragment = new ProfileFragment(MainActivity.this, userID);
        fragmentTransaction.replace(R.id.main_activity_fragment_container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        drawerLayout.closeDrawer(Gravity.LEFT);
        fragmentTransaction.commit();
    }

    public void logoutUser() {
        deleteDataFromSharedPref();
        startRegisterLoginActivity();
    }

    public void deleteDataFromSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("ayandesazansp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void showPleaseWait() {
        socialPleaseWaitTV.setVisibility(View.VISIBLE);
        membersPleaseWaitTV.setVisibility(View.VISIBLE);
        latestPleaseWaitTV.setVisibility(View.VISIBLE);
        latestNewsShowMore.setVisibility(View.GONE);
//        membersShowMore.setVisibility(View.GONE);
//        membersRecycler.setVisibility(View.GONE);


    }

    private void hideLatestNewsPleaseWait() {
        latestNewsShowMore.setVisibility(View.VISIBLE);
        latestPleaseWaitTV.setVisibility(View.GONE);
    }

    private void hideMembersPleaseWait() {
        membersShowMore.setVisibility(View.VISIBLE);
        membersPleaseWaitTV.setVisibility(View.GONE);
    }

    private void hideSocialPleaseWait() {
        socialPleaseWaitTV.setVisibility(View.GONE);
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


    private String stringBuilder(List<String> mediaIDs) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < mediaIDs.size(); i++) {
            s.append(mediaIDs.get(i));
            s.append(",");
        }
        return s.toString();
    }

}
