package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinnerCategory;
    private EditText inputTitle, inputPrice, inputDate;
    private Button btnUpdate, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        inputDate.setOnClickListener(this);
    }

    private void initView() {
        spinnerCategory = findViewById(R.id.spinnerCategory);
        inputTitle = findViewById(R.id.inputTitle);
        inputPrice = findViewById(R.id.inputPrice);
        inputDate = findViewById(R.id.inputDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);

        spinnerCategory.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));

    }

    @Override
    public void onClick(View view) {
        if (view == inputDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
            if(!title.isEmpty() && price.matches("\\d+")){
                Item item = new Item(title, category, price, date);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(item);
                finish();
            }
        }
    }
}