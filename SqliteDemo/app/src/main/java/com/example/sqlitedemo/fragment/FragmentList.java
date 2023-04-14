package com.example.sqlitedemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.UpdateDeleteActivity;
import com.example.sqlitedemo.adapter.RecyclerViewAdapter;
import com.example.sqlitedemo.dal.SQLiteHelper;
import com.example.sqlitedemo.model.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentList extends Fragment implements RecyclerViewAdapter.ItemListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;
    private TextView total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        total = view.findViewById(R.id.total);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());

//        Date d = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        List<Item> list = db.getByDate(f.format(d));

        List<Item> list = db.getAll();

        adapter.setItemList(list);
        total.setText("Tong tien: " + total(list));

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    private int total(List<Item> list) {
        int t = 0;
        for (Item i : list) {
            t += Integer.parseInt(i.getPrice());
        }
        return t;
    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Date d = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        List<Item> list = db.getByDate(f.format(d));

        List<Item> list = db.getAll();
        adapter.setItemList(list);
        total.setText("Tong tien: " + total(list));

    }
}
