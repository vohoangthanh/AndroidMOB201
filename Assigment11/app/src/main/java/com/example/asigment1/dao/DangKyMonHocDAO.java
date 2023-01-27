package com.example.asigment1.dao;

import android.content.ContentValues;
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
    public ArrayList<MonHoc> getDSMonHoc(int id,boolean isAll){
        ArrayList<MonHoc> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor ;
        if (isAll){
           cursor = sqLiteDatabase.rawQuery("SELECT mh.code, mh.name, mh.teacher, dk.id FROM MONHOC mh LEFT JOIN DANGKI dk ON mh.code = dk.code AND dk.id = ?",new String[]{String.valueOf(id)});
        }else {
            cursor = sqLiteDatabase.rawQuery("SELECT mh.code, mh.name, mh.teacher, dk.id FROM MONHOC mh INNER JOIN DANGKI dk ON mh.code = dk.code WHERE dk.id = ?",new String[]{String.valueOf(id)});
        }

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new MonHoc(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    // Đăng kí môn học
    public boolean dangKyMonHoc(int id , String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("code",code);
        long check = sqLiteDatabase.insert("DANGKI",null,contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean huydangkyMonHoc(int id , String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("DANGKI","id = ? and code = ?",new String[]{String.valueOf(id),code});
        if(check == -1 )
            return false;
        return true;
    }
}
