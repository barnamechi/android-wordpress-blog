package com.barnamechi.ayandehsazan.UI.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barnamechi.ayandehsazan.R;
import com.barnamechi.ayandehsazan.UI.ArchiveActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class MainContentRecyclerAdapter extends RecyclerView.Adapter<MainContentRecyclerAdapter.ViewHolder> {
    private List<String> titles;
    private List<String> imageURLs;
    private List<String> descriptions;
    private List<String> numbers;
    private List<Integer> postIDs;
    private Context context;
    private RequestManager glide;

    private TextView titleTV;
    private TextView descriptionTV;
    private ImageView imageIV;


    private LayoutInflater inflater;

    public MainContentRecyclerAdapter(Context context, List<String> titles, List<String> imageURLs, List<String> descriptions, List<Integer> postIDs) {
        this.inflater = LayoutInflater.from(context);
        this.titles = titles;
        this.imageURLs = imageURLs;
        this.descriptions = descriptions;
        this.postIDs = postIDs;
//        this.numbers = numbers;
        this.context = context;
        this.glide = Glide.with(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_full_directory_fragment_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        glide.load(imageURLs.get(position)).into(imageIV);

        titleTV.setText(titles.get(position));
        descriptionTV.setText(Html.fromHtml(descriptions.get(position)));

        imageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ArchiveActivity) context).startSinglePostActivity(postIDs.get(position), imageURLs.get(position));
            }
        });


    }

    private void startSinglePostFragment(int postID) {


    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.full_directory_recycler_title_tv);
            descriptionTV = itemView.findViewById(R.id.full_directory_recycler_description_tv);
            imageIV = itemView.findViewById(R.id.full_directory_recycler_image_iv);
        }


    }
}
