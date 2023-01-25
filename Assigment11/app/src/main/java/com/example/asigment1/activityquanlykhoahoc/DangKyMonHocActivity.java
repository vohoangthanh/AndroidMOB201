package com.example.asigment1.activityquanlykhoahoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.asigment1.R;
import com.example.asigment1.adapter.DangKyMonHocAdapter;
import com.example.asigment1.model.MonHoc;
import com.example.asigment1.service.DangKyMonHocService;

import java.util.ArrayList;

public class DangKyMonHocActivity extends AppCompatActivity {
    RecyclerView recyclerViewDangKyMonHoc;
    int id;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_mon_hoc);

        recyclerViewDangKyMonHoc = findViewById(R.id.recyclerDangKyMonHoc);

        intentFilter = new IntentFilter();
        intentFilter.addAction("DSMonHoc");
        // id của người dùng đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",-1);

        Intent intent = new Intent(DangKyMonHocActivity.this, DangKyMonHocService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void loadData(ArrayList<MonHoc> list){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerViewDangKyMonHoc.setLayoutManager(linearLayoutManager);
        DangKyMonHocAdapter adapter = new DangKyMonHocAdapter(this,list,id);
        recyclerViewDangKyMonHoc.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcast);
    }

    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "DSMonHoc":
                    Bundle bundle = intent.getExtras();
                    ArrayList<MonHoc> list = (ArrayList<MonHoc>) bundle.getSerializable("list");
                    loadData(list);
                    break;
            }
        }
    };
}