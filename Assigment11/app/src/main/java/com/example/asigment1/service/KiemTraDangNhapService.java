package com.example.asigment1.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.asigment1.dao.NguoiDungDAO;

public class KiemTraDangNhapService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");

        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        boolean check = nguoiDungDAO.kiemTraDangNhap(user, pass);

        // gửi dữ liệu cho broadcast
        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putBoolean("check",check);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("kiemTraDangNhap");
        sendBroadcast(intentBR);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
