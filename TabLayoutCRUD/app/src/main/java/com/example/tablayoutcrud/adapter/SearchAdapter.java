package com.example.tablayoutcrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutcrud.R;
import com.example.tablayoutcrud.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Cat> catList;

    public SearchAdapter(List<Cat> catList) {
        this.catList = catList;

    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Cat cat = catList.get(position);
        holder.itemImage.setImageResource(cat.getAvatar());
        holder.itemDesc.setText(cat.getDesc());
        holder.itemName.setText(cat.getName());
        holder.itemPrice.setText("" + cat.getPrice());

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView itemName, itemPrice, itemDesc;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemDesc = itemView.findViewById(R.id.itemDesc);
        }
    }
}
