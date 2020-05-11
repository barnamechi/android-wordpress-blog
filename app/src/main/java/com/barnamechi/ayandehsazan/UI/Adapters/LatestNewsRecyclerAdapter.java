package com.barnamechi.ayandehsazan.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barnamechi.ayandehsazan.MainActivity;
import com.barnamechi.ayandehsazan.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class LatestNewsRecyclerAdapter extends RecyclerView.Adapter<LatestNewsRecyclerAdapter.ViewHolder> {

    private List<String> titlesList;
    private List<String> imageURLs;
    private List<Integer> postIDs;
    private TextView titleTV;
    private ImageView photoIV;
    private LayoutInflater inflater;
    private Context context;
    private RequestManager glide;

    public LatestNewsRecyclerAdapter(final Context context, List<String> titlesList, List<String> imageURLs, List<Integer> postIDs) {
        this.inflater = LayoutInflater.from(context);
        this.titlesList = titlesList;
        this.imageURLs = imageURLs;
        this.postIDs = postIDs;
        glide = Glide.with(context);
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_main_activity_latest_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        glide.load(imageURLs.get(position)).into(photoIV);
        titleTV.setText(titlesList.get(position));
        titleTV.bringToFront();
        titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startSinglePostActivity(postIDs.get(position),imageURLs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return titlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photoIV = itemView.findViewById(R.id.latest_news_recycler_photo_iv);
            titleTV = itemView.findViewById(R.id.latest_news_recycler_title_tv);


        }
    }
}
