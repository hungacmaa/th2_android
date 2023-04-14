package com.example.implicitintentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView web, sms, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.web);
        sms = findViewById(R.id.sms);
        phone = findViewById(R.id.phone);
        web.setOnClickListener(this);
        sms.setOnClickListener(this);
        phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.web:
                Intent w = new Intent(Intent.ACTION_VIEW);
                w.setData(Uri.parse("https://www.youtube.com/watch?v=biDx5wNFkws&list=RDMMbiDx5wNFkws&start_radio=1"));
                startActivity(w);
                break;
            case R.id.sms:
                Intent s = new Intent(Intent.ACTION_VIEW);
                s.setData(Uri.parse("sms:0981814426"));
                s.putExtra("sms_body", "");
                startActivity(s);
                break;
            case R.id.phone:
                Intent p = new Intent(Intent.ACTION_DIAL);
                p.setData(Uri.parse("tel:0981814426"));
                startActivity(p);
                break;
        }
    }
}