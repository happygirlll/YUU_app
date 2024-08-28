package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BuildingDataAdapter extends RecyclerView.Adapter<BuildingDataAdapter.MyViewHolder> implements Filterable {
    private List<BuildingData> mDataset;
    private List<BuildingData> exampleListFull;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView id;
        public TextView major;
        public TextView price;

        public ImageView imageView;
        CardView card_view;
        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_text);
            id = (TextView) view.findViewById(R.id.id_text);
            major = (TextView) view.findViewById(R.id.major_text);
            imageView = (ImageView) view.findViewById(R.id.AppImage);
            card_view=(CardView) view.findViewById(R.id.layout_container);
        }
        public void setItem(BuildingData mapData){
            name.setText(mapData.getName());
            id.setText(mapData.getName());
            major.setText(mapData.getMajor());
        }
    }

    public BuildingDataAdapter(List<BuildingData> myData, Context context){
        this.mDataset = myData;
        mContext = context;
        exampleListFull = new ArrayList<>(myData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recyclerview, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return this.mDataset.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final BuildingDataAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.id.setText(mDataset.get(position).getId());
        holder.major.setText(mDataset.get(position).getMajor());

        Picasso.with(mContext).load(mDataset.get(position).getPicture()).fit().centerInside().into(holder.imageView);
        holder.card_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                int mPostion = holder.getAdapterPosition();
                Context context = view.getContext();

                Intent detailActivity = new Intent(context, DetailActivity.class);
                detailActivity.putExtra("id", mDataset.get(mPostion).getId());
                detailActivity.putExtra("name", mDataset.get(mPostion).getName());
                detailActivity.putExtra("major", mDataset.get(mPostion).getMajor());
                detailActivity.putExtra("url", mDataset.get(mPostion).getUrl());
                detailActivity.putExtra("call", mDataset.get(mPostion).getCall());
                detailActivity.putExtra("info", mDataset.get(mPostion).getInfo());
                detailActivity.putExtra("image",mDataset.get(mPostion).getPicture());
                ((MapListActivity) context).startActivity(detailActivity);
            }
        });
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<BuildingData> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (BuildingData item : exampleListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)
                            || item.getId().toLowerCase().contains(filterPattern)
                            || item.getMajor().toLowerCase().contains(filterPattern)
                            || item.getTag().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataset.clear();
            mDataset.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}


