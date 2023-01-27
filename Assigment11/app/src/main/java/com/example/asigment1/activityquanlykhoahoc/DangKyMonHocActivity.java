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
import android.widget.TextView;
import android.widget.Toast;

import com.example.asigment1.R;
import com.example.asigment1.adapter.DangKyMonHocAdapter;
import com.example.asigment1.model.MonHoc;
import com.example.asigment1.service.DangKyMonHocService;

import java.util.ArrayList;

public class DangKyMonHocActivity extends AppCompatActivity {
    RecyclerView recyclerViewDangKyMonHoc;
    int id;
    IntentFilter intentFilter;
    boolean isAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_mon_hoc);

        recyclerViewDangKyMonHoc = findViewById(R.id.recyclerDangKyMonHoc);
        TextView txtTitle = findViewById(R.id.txtTitle);

        intentFilter = new IntentFilter();
        intentFilter.addAction("DSMonHoc");
        intentFilter.addAction("DKMonHoc");

        // id của người dùng đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        id = sharedPreferences.getInt("id", -1);

        // lấy giá trị isAll
        Intent intentisAll = getIntent();
        Bundle bundleisAll = intentisAll.getExtras();
        isAll = bundleisAll.getBoolean("isAll");
        if (isAll){
            txtTitle.setText("Đăng Kí Môn Học");
        }else {
            txtTitle.setText("Đã Đăng Kí Môn Học");
        }
        Intent intent = new Intent(DangKyMonHocActivity.this, DangKyMonHocService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putBoolean("isAll",isAll);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void loadData(ArrayList<MonHoc> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerViewDangKyMonHoc.setLayoutManager(linearLayoutManager);
        DangKyMonHocAdapter adapter = new DangKyMonHocAdapter(this, list, id,isAll);
        recyclerViewDangKyMonHoc.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcast, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcast);
    }

    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case "DSMonHoc":
                case "DKMonHoc":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check", true);

                    if (check) {
                        ArrayList<MonHoc> list = (ArrayList<MonHoc>) bundle.getSerializable("list");
                        loadData(list);
                    } else {
                        Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    break;
            }
        }
    };
}