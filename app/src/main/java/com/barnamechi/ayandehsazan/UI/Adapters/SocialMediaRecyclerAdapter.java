package com.barnamechi.ayandehsazan.UI.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barnamechi.ayandehsazan.MainActivity;
import com.barnamechi.ayandehsazan.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class SocialMediaRecyclerAdapter extends RecyclerView.Adapter<SocialMediaRecyclerAdapter.ViewHolder> {
    private List<String> titlesList;
    private List<String> imageURLs;
    private List<String> descriptions;
    private List<Integer> colors;
    private List<Integer> socialIDs;
    private TextView titleTV;
    private TextView descriptionTV;
    private ImageView photoIV;
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private RequestManager glide;
    private FrameLayout noticationFL;
    private Context context;

    public SocialMediaRecyclerAdapter(Context context, List<String> titlesList, List<String> imageURLs, List<String> descriptions, List<Integer> socialIDs) {
        this.inflater = LayoutInflater.from(context);
        glide = Glide.with(context);
        this.titlesList = titlesList;
        this.imageURLs = imageURLs;
        this.descriptions = descriptions;
        this.context = context;
        this.socialIDs = socialIDs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_main_activity_social_medias, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        glide.load(imageURLs.get(position)).into(photoIV);
        titleTV.setText(Html.fromHtml(titlesList.get(position)));
        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startSinglePostActivity(socialIDs.get(position), imageURLs.get(position));

            }
        });
        descriptionTV.setText(Html.fromHtml(descriptions.get(position)));
//        noticationFL.setBackgroundColor(colors.get(position));

    }

    @Override
    public int getItemCount() {
        return titlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.social_medias_recycler_title_tv);
            photoIV = itemView.findViewById(R.id.social_medias_recycler_photo_iv);
            descriptionTV = itemView.findViewById(R.id.social_medias_recycler_description_tv);
            noticationFL = itemView.findViewById(R.id.social_medias_recycler_notification_color_fl);
            colors = new ArrayList<>();
            colors.add(R.color.greenActive);
            colors.add(R.color.orangeActive);
            colors.add(R.color.blueActive);

//
//            titlesList = new ArrayList<>();
//            descriptions = new ArrayList<>();
//            imageURLs = new ArrayList<>();
//            titlesList.add("واتس اپ");
//            titlesList.add("ایمیل");
//            titlesList.add("تلگرام");
//            descriptions.add("پیوستن به گروه های واتس اپ");
//            descriptions.add("ارسال پیام از طریق ایمیل");
//            descriptions.add("عضو شدن در کانال های تلگرام");
//            imageURLs.add("https://cdn2.iconfinder.com/data/icons/2018-social-media-app-logos/1000/2018_social_media_popular_app_logo-whatsapp-512.png");
//            imageURLs.add("https://cdn2.mageplaza.com/media/shopify_appicons//eb8d6fd691c55e91db1e37d0d3d23d69.png");
//            imageURLs.add("https://projects.aldomann.com/assets/img/telegram-systray-icons.png");

        }
    }
}
