package com.example.asigment1.activityquanlykhoahoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asigment1.R;

public class MonHocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_hoc);

        Button btnDangKyMonHoc = findViewById(R.id.btnDangKyMonHoc);
        Button btnMonHocDaDangKy = findViewById(R.id.btnMonHocDaDangKy);

        btnDangKyMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MonHocActivity.this,DangKyMonHocActivity.class);
               Bundle bundle = new Bundle();
               bundle.putBoolean("isAll",true);
               intent.putExtras(bundle);
               startActivity(intent);
            }
        });

        btnMonHocDaDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonHocActivity.this,DangKyMonHocActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isAll",false);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}