package com.example.asigment1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asigment1.helper.DbHelper;
import com.example.asigment1.model.MonHoc;

import java.util.ArrayList;

public class DangKyMonHocDAO {
    private DbHelper dbHelper;

    public  DangKyMonHocDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    // Lấy danh sách môn học
    public ArrayList<MonHoc> getDSMonHoc(){
        ArrayList<MonHoc> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MONHOC",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new MonHoc(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
