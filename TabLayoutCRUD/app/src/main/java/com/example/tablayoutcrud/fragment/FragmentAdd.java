package com.example.tablayoutcrud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutcrud.MainActivity;
import com.example.tablayoutcrud.R;
import com.example.tablayoutcrud.adapter.CatAdapter;
import com.example.tablayoutcrud.adapter.SpinnerAdapter;
import com.example.tablayoutcrud.model.Cat;

import java.util.concurrent.atomic.DoubleAccumulator;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener {
    private Spinner imageSpinner;
    private EditText inputName, inputPrice, inputDesc;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private int currentPosition;
    private int[] imgs = {R.drawable.img3, R.drawable.img4, R.drawable.img3,
            R.drawable.img4, R.drawable.img3};
    private SpinnerAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        catAdapter = new CatAdapter((MainActivity) getActivity());
        catAdapter.setCatItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(catAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = imageSpinner.getSelectedItem().toString();
                int img;
                try {
                    img = Integer.parseInt(i);
                    String name = inputName.getText().toString();
                    String desc = inputDesc.getText().toString();
                    double price = Double.parseDouble(inputPrice.getText().toString());
                    Cat cat = new Cat(img, name, price, desc);
                    catAdapter.add(cat);
                } catch (Exception e) {

                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = imageSpinner.getSelectedItem().toString();
                int img;
                try {
                    img = Integer.parseInt(i);
                    String name = inputName.getText().toString();
                    String desc = inputDesc.getText().toString();
                    double price = Double.parseDouble(inputPrice.getText().toString());
                    Cat cat = new Cat(img, name, price, desc);
                    catAdapter.update(currentPosition, cat);
                    btnUpdate.setVisibility(View.INVISIBLE);
                    btnAdd.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            }
        });
    }

    private void initView(View view) {
        imageSpinner = view.findViewById(R.id.imageSpinner);
        spinnerAdapter = new SpinnerAdapter(getContext(), imgs);
        imageSpinner.setAdapter(spinnerAdapter);

        inputName = view.findViewById(R.id.inputName);
        inputPrice = view.findViewById(R.id.inputPrice);
        inputDesc = view.findViewById(R.id.inputDesc);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setVisibility(View.INVISIBLE);

        recyclerView = view.findViewById(R.id.recyclerView);

    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.VISIBLE);
        currentPosition = position;

        Cat cat = catAdapter.getItem(position);

        int p = 0;
        int img = cat.getAvatar();
        for (int i = 0; i < imgs.length; ++i) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        imageSpinner.setSelection(p);
        inputName.setText(cat.getName());
        inputPrice.setText(""+cat.getPrice());
        inputDesc.setText(cat.getDesc());


    }
}
