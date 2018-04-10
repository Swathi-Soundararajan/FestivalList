package com.example.user.festivallist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by user on 08-04-2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        private List<FestivalList> festivalLists;
        private Context context;
        public RecyclerAdapter(){

        }

        public RecyclerAdapter (Context context,List<FestivalList> festivalLists){
            this.festivalLists = festivalLists;
            this.context = context;
        }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.festival_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.i("adapter:","binding data");
            final FestivalList festivalList = festivalLists.get(position);
            holder.ename.setText(festivalList.getEventname());
            holder.location.setText(festivalList.getLocation());
            holder.fees.setText(festivalList.getFees());
            holder.date.setText(festivalList.getEventDate());
            holder.email.setText(festivalList.getEmail());
        if (festivalList.getThumb().isEmpty()) {
            holder.img.setImageResource(R.drawable.display);//setting default image  when no image is available.
        } else {
            //glide library for fetching users images
            Glide.with(context).load(festivalList.getThumb()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.img) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.img.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
            //return 1;
        return festivalLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ename,location,fees,date,email;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(view.getContext(),DetailsActivity.class));
                }
            });
            ename = itemView.findViewById(R.id.evname);
            location = itemView.findViewById(R.id.location);
            fees = itemView.findViewById(R.id.fees);
            date= itemView.findViewById(R.id.date);
            email= itemView.findViewById(R.id.email);
            img = itemView.findViewById(R.id.image);
        }

    }
}
