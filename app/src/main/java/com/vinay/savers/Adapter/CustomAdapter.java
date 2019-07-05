package com.vinay.savers.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.vinay.savers.Model.DataModel;
import com.vinay.savers.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataset;
    public CardView mCardView;

    public CustomAdapter(ArrayList<DataModel> dataset)
    {
        this.dataset= dataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView tvName=holder.tvTitle;
        TextView tvVersion=holder.tvVersion;
        CardView card_view = holder.itemView.findViewById(R.id.cardview);
        tvName.setText(dataset.get(position).getTitle().toString());
        tvVersion.setText(dataset.get(position).getVersion().toString());
        if (position==1)
            card_view.setCardBackgroundColor(Color.parseColor("#9ccc65"));
        if (position==2)
            card_view.setCardBackgroundColor(Color.parseColor("#7e57c2"));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvVersion;
        ImageView imgIcon;
        public MyViewHolder(View item) {
            super(item);
            //Initialize the view
            this.tvTitle= item.findViewById(R.id.tvTitle);
            this.tvVersion= item.findViewById(R.id.tvVersion);

        }
    }
}

