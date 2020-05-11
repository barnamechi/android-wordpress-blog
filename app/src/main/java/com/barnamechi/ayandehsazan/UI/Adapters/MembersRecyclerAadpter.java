package com.barnamechi.ayandehsazan.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barnamechi.ayandehsazan.MainActivity;
import com.barnamechi.ayandehsazan.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class MembersRecyclerAadpter extends RecyclerView.Adapter<MembersRecyclerAadpter.ViewHolder> {

    private List<String> imageURLs;
    private List<Integer> memberIDs;

    private ImageView photoIV;

    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private RequestManager glide;
    private Context context;

    public MembersRecyclerAadpter(Context context, List<String> imageURLs, List<Integer> memberIDs) {
        this.inflater = LayoutInflater.from(context);
        this.glide = Glide.with(context);
        this.imageURLs = imageURLs;
        this.memberIDs = memberIDs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_main_activity_members, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        glide.load(imageURLs.get(position)).into(photoIV);
        photoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startSinglePostActivity(memberIDs.get(position), imageURLs.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return imageURLs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoIV = itemView.findViewById(R.id.members_recycler_photo_iv);
        }
    }
}
