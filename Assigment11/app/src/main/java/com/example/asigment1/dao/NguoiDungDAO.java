package com.example.asigment1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asigment1.helper.DbHelper;

public class NguoiDungDAO {
    DbHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);

    }

    // kiểm tra thông tin đăng nhập
    public boolean kiemTraDangNhap(String user , String pass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE username = ? AND password = ?",new String[]{user,pass});
        if (cursor.getCount() != 0){
            return true;
        }
        return false;
    }
}
