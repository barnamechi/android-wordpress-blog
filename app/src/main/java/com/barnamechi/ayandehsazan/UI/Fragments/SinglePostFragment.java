package com.barnamechi.ayandehsazan.UI.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.barnamechi.ayandehsazan.API.Api;
import com.barnamechi.ayandehsazan.API.EndPoints;
import com.barnamechi.ayandehsazan.Models.Posts.SinglePost.SinglePost;
import com.barnamechi.ayandehsazan.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SinglePostFragment extends Fragment {
    private ImageView headerImage;

    private TextView titleTV;
    private TextView authorTV;
    private TextView dateTV;
    private TextView mainContentTV;
    private WebView mainContentWV;
    private FrameLayout pleaseWaitFL;
    private TextView pleaseWaitTV;

    private Context context;
    private int postID;
    private String imageURL;
    private String title;
    private String authorName;
    private String date;
    private String mainContent;
    private String postLink;

    private int author;
    private RequestManager glide;


    public SinglePostFragment(Context context, int postID, String imageURl) {
        this.postID = postID;
        this.context = context;
        this.imageURL = imageURl;
        this.glide = Glide.with(context);
    }

//    public static SinglePostFragment newInstance(String param1, String param2) {
//        SinglePostFragment fragment = new SinglePostFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainContentWV.destroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_single_post, container, false);

        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        headerImage = rootView.findViewById(R.id.single_post_fragment_header_image);

//        titleTV = rootView.findViewById(R.id.single_post_fragment_text_title_tv);
//        authorTV = rootView.findViewById(R.id.single_post_fragment_author_tv);
//        dateTV = rootView.findViewById(R.id.single_post_framgent_date_tv);
//        mainContentTV = rootView.findViewById(R.id.single_post_fragment_main_content_tv);
        mainContentWV = rootView.findViewById(R.id.single_post_fragment_web_view);

        pleaseWaitFL = rootView.findViewById(R.id.single_post_please_wait_fl);
        pleaseWaitTV = rootView.findViewById(R.id.single_post_please_wait_tv);
        showPhoto(imageURL);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPost(postID);

    }

    private void showPhoto(String imageURL) {
        glide.load(imageURL).into(headerImage);
        headerImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void getPost(int postID) {
        showPleaseWait();
        Api api = createRetrofitApi();
        Call<SinglePost> call = api.getSinglePost(postID);
        call.enqueue(new Callback<SinglePost>() {
            @Override
            public void onResponse(Call<SinglePost> call, Response<SinglePost> response) {
                if (response.body() != null) {
//                    title = response.body().getTitle().getRendered();
//                    author = response.body().getAuthor();
//                    date = response.body().getDate();
//                    mainContent = response.body().getContent().getRendered();
                    postLink = response.body().getLink();
//                    getAuthor(author);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        showPost();
                    }


                } else {
                    showCheckInternetToast();
                }
            }

            @Override
            public void onFailure(Call<SinglePost> call, Throwable t) {
                showNoResponseToast();
            }
        });

    }

    private void showPleaseWait() {
        pleaseWaitTV.setVisibility(View.VISIBLE);
        pleaseWaitFL.setVisibility(View.VISIBLE);
    }


    private void hidePleaseWait() {
        pleaseWaitTV.setVisibility(View.GONE);
        pleaseWaitFL.setVisibility(View.GONE);
    }

//    private void getAuthor(final int author) {
//        Api api = createRetrofitApi();
//        Call<SingleUser> call = api.getSingleUSer(author);
//        call.enqueue(new Callback<SingleUser>() {
//            @Override
//            public void onResponse(Call<SingleUser> call, Response<SingleUser> response) {
//                if (response.body() != null) {
//                    authorName = response.body().getName();
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        showPost();
//                    }
//                } else {
//                    showCheckInternetToast();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SingleUser> call, Throwable t) {
//                showNoResponseToast();
//            }
//        });
//    }

    private void showNoResponseToast() {
        Toast.makeText(context, R.string.no_response_from_server, Toast.LENGTH_LONG).show();
    }

    private void showCheckInternetToast() {
        Toast.makeText(context, R.string.check_internet_connection, Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showPost() {
//        titleTV.setText(title);
//        authorTV.setText(authorName);
//        dateTV.setText(date);
        mainContentWV.loadUrl(postLink);
        hidePleaseWait();
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

//    private Html.ImageGetter imgGetter = new Html.ImageGetter() {
//        @Override
//        public Drawable getDrawable(String source) {
//            Drawable drawable = null;
//            drawable = getResources().getDrawable(R.raw)
//            return drawable;
//        }
//    }

}
