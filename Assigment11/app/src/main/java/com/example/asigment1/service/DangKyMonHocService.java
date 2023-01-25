package com.example.asigment1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.asigment1.dao.DangKyMonHocDAO;
import com.example.asigment1.model.MonHoc;

import java.util.ArrayList;

public class DangKyMonHocService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");

        DangKyMonHocDAO dangKyMonHocDAO = new DangKyMonHocDAO(this);
        ArrayList<MonHoc> list = dangKyMonHocDAO.getDSMonHoc(id);

        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putSerializable("list",list);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("DSMonHoc");
        sendBroadcast(intentBR);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
