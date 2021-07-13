package com.virtusa.university.view.university.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.virtusa.university.R;
import com.virtusa.university.model.UniversityJson;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

public class UniversityListAdapter extends RecyclerView.Adapter<UniversityListAdapter.ViewHolder> {

    private static ClickListener mListener;
    private UniversityJson mData;
    private LayoutInflater mInflater;
    private Context mContext;


    //Constructor
    public UniversityListAdapter(Context context, UniversityJson universityJson){
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mData = universityJson;

    }

    // Listener
    public void setOnItemClickListener(ClickListener clickListener) {
        UniversityListAdapter.mListener = clickListener;

    }

    // This is to set up the image to the view
    private void setUpThumpNail(ImageView icon, URL url) {

        RequestOptions options = new RequestOptions()
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(mContext)
                .load(url)
                .apply(options)
                .into(icon);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        // create a new view
        View itemLayoutView = mInflater.inflate(R.layout.university_list_adapter,parent, false);
        return new ViewHolder(itemLayoutView);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int position) {

        viewHolder.header.setText(mData.res.get(position).name);
        try {
            setUpThumpNail(viewHolder.imageView,new URL(mData.res.get(position).imageUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        viewHolder.subHeader.setText(mData.res.get(position).webPages.get(0));



    }

    @Override
    public int getItemCount() {
        return mData.res.size();
    }

    //Listener to handle the click on cards
    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    //View holder to hold all the views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView header;
        public TextView subHeader;
        public ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageview);
            header = itemView.findViewById(R.id.header);
            subHeader = itemView.findViewById(R.id.sub_header);

        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition(),v);
        }



    }
}
