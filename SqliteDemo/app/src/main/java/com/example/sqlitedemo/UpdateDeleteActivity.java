package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sqlitedemo.dal.SQLiteHelper;
import com.example.sqlitedemo.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerCategory;
    private EditText inputTitle, inputPrice, inputDate;
    private Button btnUpdate, btnDelete, btnCancel;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        inputDate.setOnClickListener(this);

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        inputTitle.setText(item.getTitle());
        inputDate.setText(item.getDate());
        inputPrice.setText(item.getPrice());

        int p = 0;
        for (int i = 0; i < spinnerCategory.getCount(); ++i) {
            if (spinnerCategory.getItemAtPosition(i).toString().equalsIgnoreCase(item.getCategory())) {
                p = i;
                break;
            }
        }

        spinnerCategory.setSelection(p);
    }

    private void initView() {
        spinnerCategory = findViewById(R.id.spinnerCategory);
        inputTitle = findViewById(R.id.inputTitle);
        inputPrice = findViewById(R.id.inputPrice);
        inputDate = findViewById(R.id.inputDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        spinnerCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));

    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if (view == inputDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    String date = "";
                    if (i1 > 8) {
                        date = i2 + "/" + (i1 + 1) + "/" + i;
                    } else {
                        date = i2 + "/0" + (i1 + 1) + "/" + i;
                    }
                    inputDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if (view == btnCancel) {
            finish();
        }
        if (view == btnUpdate) {
            String title = inputTitle.getText().toString();
            String price = inputPrice.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();
            String date = inputDate.getText().toString();
            if (!title.isEmpty() && price.matches("\\d+")) {
                Item item1 = new Item(item.getId(), title, category, price, date);
                db.updateItem(item1);
                finish();
            }
        }
        if (view == btnDelete) {

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban cp chac muon xoa item " + item.getId() + " khong?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                    db.deleteItem(item.getId());
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}