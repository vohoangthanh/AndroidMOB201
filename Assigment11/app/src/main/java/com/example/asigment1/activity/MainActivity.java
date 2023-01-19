package com.example.asigment1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asigment1.R;
import com.example.asigment1.activityquanlykhoahoc.MonHocActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btncourse = findViewById(R.id.btncourse);
        Button btnMap = findViewById(R.id.btnMap);
        Button btnSocial = findViewById(R.id.btnSocial);
        Button btnNew = findViewById(R.id.btnNew);

        btncourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MonHocActivity.class));
            }
        });

    }
}