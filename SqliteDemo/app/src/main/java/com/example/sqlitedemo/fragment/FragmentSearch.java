package com.example.sqlitedemo.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.AddActivity;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.UpdateDeleteActivity;
import com.example.sqlitedemo.adapter.RecyclerViewAdapter;
import com.example.sqlitedemo.dal.SQLiteHelper;
import com.example.sqlitedemo.model.Item;

import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener, RecyclerViewAdapter.ItemListener {
    private SearchView search;
    private EditText inputFrom, inputTo;
    private Spinner spinnerCategory;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SQLiteHelper db;
    private TextView total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setItemList(list);
        adapter.setItemListener(this);
        total.setText("Tong tien: " + total(list));

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Item> list1 = db.searchByTitle(newText);
                total.setText("Tong tien: " + total(list1));
                adapter.setItemList(list1);
                return true;
            }
        });

        inputFrom.setOnClickListener(this);
        inputTo.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String category = spinnerCategory.getItemAtPosition(i).toString();
                List<Item> list2;
                if (!category.equalsIgnoreCase("all")) {
                    list2 = db.searchByCategory(category);
                } else {
                    list2 = db.getAll();
                }
                adapter.setItemList(list2);
                total.setText("Tong tien: " + total(list2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView(View view) {
        search = view.findViewById(R.id.search);
        inputFrom = view.findViewById(R.id.inputFrom);
        inputTo = view.findViewById(R.id.inputTo);
        btnSearch = view.findViewById(R.id.btnSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        total = view.findViewById(R.id.total);

        String[] arr = getResources().getStringArray(R.array.category);
        String[] arr1 = new String[arr.length + 1];
        arr1[0] = "all";
        for (int i = 0; i < arr.length; ++i) {
            arr1[i + 1] = arr[i];
        }
        spinnerCategory.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    @Override
    public void onClick(View view) {
        if (view == inputFrom) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    String date = "";
                    if (i1 > 8) {
                        date = i2 + "/" + (i1 + 1) + "/" + i;
                    } else {
                        date = i2 + "/0" + (i1 + 1) + "/" + i;
                    }
                    inputFrom.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }

        if (view == inputTo) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    String date = "";
                    if (i1 > 8) {
                        date = i2 + "/" + (i1 + 1) + "/" + i;
                    } else {
                        date = i2 + "/0" + (i1 + 1) + "/" + i;
                    }
                    inputTo.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }

        if (view == btnSearch) {
            String from = inputFrom.getText().toString();
            String to = inputTo.getText().toString();
            if (!from.isEmpty() && !to.isEmpty()) {
                List<Item> list3 = db.getByDateFromTo(from, to);
                adapter.setItemList(list3);
                total.setText("Tong tien: " + total(list3));
            }
        }
    }

    private int total(List<Item> list) {
        int t = 0;
        for (Item i : list) {
            t += Integer.parseInt(i.getPrice());
        }
        return t;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Date d = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        List<Item> list = db.getByDate(f.format(d));

        List<Item> list4 = db.getAll();
        adapter.setItemList(list4);
        total.setText("Tong tien: " + total(list4));

    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}
