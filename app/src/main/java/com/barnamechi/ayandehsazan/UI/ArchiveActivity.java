package com.barnamechi.ayandehsazan.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barnamechi.ayandehsazan.API.Api;
import com.barnamechi.ayandehsazan.API.EndPoints;
import com.barnamechi.ayandehsazan.Models.MediaModel.singleMedia.RegularSingleMedia;
import com.barnamechi.ayandehsazan.Models.Posts.Posts;
import com.barnamechi.ayandehsazan.R;
import com.barnamechi.ayandehsazan.UI.Adapters.MainContentRecyclerAdapter;
import com.barnamechi.ayandehsazan.UI.Fragments.SinglePostFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArchiveActivity extends AppCompatActivity {
    private RecyclerView mainContentRecycler;

    private List<String> titles;
    private List<String> imageURLS;
    private List<String> descriptions;
    private List<String> mediaIDs;
    private List<Integer> postIDS;
    private int categoryID;
    private int pageNumber;

    private TextView nextBtn;
    private TextView prevBtn;
    private FrameLayout pleaseWaitFL;
    private TextView pleaseWaitTV;

//
//    public ArchiveActivity(int categoryID, int pageNumber) {
//        this.categoryID = categoryID;
//        this.pageNumber = pageNumber;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        getCategoryIdFromIntent();
        initViews();
    }

    private void getCategoryIdFromIntent() {
        Intent intent = ArchiveActivity.this.getIntent();
        pageNumber = intent.getIntExtra("RecievedPageNumber", 1);
        categoryID = intent.getIntExtra("RecievedCategoryID", 1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        onClickListeners();
        getCategoryPosts(categoryID, pageNumber);
    }


    private void initViews() {
        mainContentRecycler = findViewById(R.id.archive_activity_main_recycler_rv);

        titles = new ArrayList<>();
        imageURLS = new ArrayList<>();
        descriptions = new ArrayList<>();
        mediaIDs = new ArrayList<>();
        postIDS = new ArrayList<>();

        nextBtn = findViewById(R.id.archive_activity_next_button_tv);
        prevBtn = findViewById(R.id.archive_activity_previous_tv);

        pleaseWaitFL = findViewById(R.id.archive_activity_please_wait_fl);
        pleaseWaitTV = findViewById(R.id.archive_activity_please_wait_tv);

    }


    @SuppressLint("ResourceAsColor")
    private void onClickListeners() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNumber++;
                getCategoryPosts(categoryID, pageNumber);
            }
        });
        if (pageNumber > 1) {
            prevBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pageNumber--;
                    getCategoryPosts(categoryID, pageNumber);
                }
            });
        } else {
            prevBtn.setTextColor(R.color.grayBackground);
        }
    }

    private void getCategoryPosts(int categoryID, int pageNumber) {
        showPleaseWait();
        Api api = createRetrofitApi();
        Call<List<Posts>> call = api.getPostsWithPage(categoryID, pageNumber);
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        titles.add(response.body().get(i).getTitle().getRendered());
                        descriptions.add(response.body().get(i).getExcerpt().getRendered());
                        mediaIDs.add(String.valueOf(response.body().get(i).getFeaturedMedia()));
                        postIDS.add(response.body().get(i).getId());
                    }
                    getMedias(mediaIDs, imageURLS);

                } else {
                    Toast.makeText(ArchiveActivity.this, R.string.no_other_pages, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });


    }


    private String stringBuilder(List<String> mediaIDs) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < mediaIDs.size(); i++) {
            s.append(mediaIDs.get(i));
            s.append(",");
        }
        return s.toString();
    }

    private void getMedias(final List<String> mediaIDs, final List<String> imageURLs) {
        Api api = createRetrofitApi();
        String includes = stringBuilder(mediaIDs);
        Call<List<RegularSingleMedia>> callMedia = api.getMedias(includes);
        callMedia.enqueue(new Callback<List<RegularSingleMedia>>() {
            @Override
            public void onResponse(Call<List<RegularSingleMedia>> call, Response<List<RegularSingleMedia>> response) {
                if (response.body() != null) {

                    for (int j = 0; j < response.body().size(); j++) {
                        imageURLs.add(response.body().get(j).getGuid().getRendered());
                    }

                    showMainRecyclerView();

                } else {
                }
            }

            @Override
            public void onFailure(Call<List<RegularSingleMedia>> call, Throwable t) {
            }
        });

    }

    private void showMainRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ArchiveActivity.this, RecyclerView.VERTICAL, false);
        MainContentRecyclerAdapter adapter = new MainContentRecyclerAdapter(ArchiveActivity.this, titles, imageURLS, descriptions, postIDS);
        mainContentRecycler.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        mainContentRecycler.setAdapter(adapter);
        hidePleaseWait();

    }

    private void showPleaseWait() {
        pleaseWaitTV.setVisibility(View.VISIBLE);
        pleaseWaitFL.setVisibility(View.VISIBLE);
    }

    public void startSinglePostActivity(int postID, String imageURL) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SinglePostFragment singlePostFragment = new SinglePostFragment(ArchiveActivity.this, postID, imageURL);
        fragmentTransaction.add(R.id.archive_activity_fragment_container, singlePostFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void hidePleaseWait() {
        pleaseWaitTV.setVisibility(View.GONE);
        pleaseWaitFL.setVisibility(View.GONE);
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


}
