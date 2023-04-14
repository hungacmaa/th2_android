package com.example.tablayoutcrud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutcrud.MainActivity;
import com.example.tablayoutcrud.R;
import com.example.tablayoutcrud.adapter.SearchAdapter;
import com.example.tablayoutcrud.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView1;
    private SearchAdapter adapter;
    private List<Cat> catList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        catList = ((MainActivity)getActivity()).list;
        searchView = view.findViewById(R.id.searchView);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        adapter = new SearchAdapter(catList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView1.setLayoutManager(manager);
        recyclerView1.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }

            private void filter(String s) {
                List<Cat> filterList = new ArrayList<>();
                for (Cat c : catList) {
                    if (c.getName().toLowerCase().contains(s.toLowerCase())) {
                        filterList.add(c);
                    }
                }
                adapter.setCatList(filterList);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        catList = ((MainActivity)getActivity()).list;
    }
}
