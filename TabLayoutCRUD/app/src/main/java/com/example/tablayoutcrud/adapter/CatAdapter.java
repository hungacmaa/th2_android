package com.example.tablayoutcrud.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutcrud.MainActivity;
import com.example.tablayoutcrud.R;
import com.example.tablayoutcrud.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private MainActivity mainActivity;
    private List<Cat> catList;
    private CatItemListener catItemListener;

    public CatAdapter(MainActivity mainActivity) {
        this.catList = new ArrayList<>();
        mainActivity.list = this.catList;
        this.mainActivity = mainActivity;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    public Cat getItem(int positon) {
        return catList.get(positon);
    }

    public List<Cat> getCatList() {
        return catList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cat cat = catList.get(position);

        holder.itemImage.setImageResource(cat.getAvatar());
        holder.itemName.setText(cat.getName());
        holder.itemPrice.setText(cat.getPrice()+"");
        holder.itemDesc.setText(cat.getDesc());
        holder.itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac muon xoa khong");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        catList.remove(position);
                        notifyDataSetChanged();
                        mainActivity.list = catList;
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImage;
        private TextView itemName, itemPrice, itemDesc;
        private Button itemButton;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemDesc = itemView.findViewById(R.id.itemDesc);
            itemButton = itemView.findViewById(R.id.itemButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(catItemListener != null){
                catItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }

    public void add(Cat cat){
        catList.add(cat);
        notifyDataSetChanged();
        mainActivity.list = catList;

    }

    public void update(int position, Cat cat){
        catList.set(position, cat);
        notifyDataSetChanged();
        mainActivity.list = catList;

    }
}
