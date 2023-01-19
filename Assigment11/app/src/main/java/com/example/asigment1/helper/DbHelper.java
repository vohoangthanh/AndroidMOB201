package com.example.asigment1.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public  DbHelper(Context context){
        super(context,"DANGKYMONHOC",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(id integer primary key autoincrement,username text,password text,name text)";
        sqLiteDatabase.execSQL(dbNguoiDung);

        String dbMonHoc = "CREATE TABLE MONHOC(code text primary key, name text, teacher text)";
        sqLiteDatabase.execSQL(dbMonHoc);

        String dbThongTin = "CREATE TABLE THONGTIN(id integer primary key autoincrement,code text,date text, address text)";
        sqLiteDatabase.execSQL(dbThongTin);

        String dbDangKi = "CREATE TABLE DANGKI(id integer ,code text)";
        sqLiteDatabase.execSQL(dbDangKi);


        // insert
        String insNguoiDung = "INSERT INTO NGUOIDUNG VALUES(1,'thanh','123456','Hoàng Thanh'),(2,'tridinh','123456abc','Hoàng Thanh')";
        sqLiteDatabase.execSQL(insNguoiDung);

        String insMonHoc = "INSERT INTO MONHOC VALUES('MOB201','Androi Nang Cao','Võ Hoàng Thanh'),('MOB401','Lập Trình Game','Mang Tuấn Vĩ'),('MOB2044','Dự Án Mẫu','Võ Hoàng Thanh')";
        sqLiteDatabase.execSQL(insMonHoc);

        String insThongTin = "INSERT INTO THONGTIN VALUES(1,'MOB201','Ca 2 - 19/09/2022','T1011'),(2,'MOB201','Ca 2 - 21/09/2022','T1011'),(3,'MOB201','Ca 2 - 23/09/2022','T1011'),(4,'MOB306','Ca 5 - 20/09/2022','F201'),(5,'MOB2041','Ca 1 - 20/09/2022','Online - https://mêt.google.com/rku-beuk')";
        sqLiteDatabase.execSQL(insThongTin);

        String insDangKy = "INSERT INTO DANGKI VALUES(1,'MOB201'),(1,'MOB306'),(2,'MOB306')";
        sqLiteDatabase.execSQL(insDangKy);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MONHOC");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THONGTIN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANGKI");
            onCreate(sqLiteDatabase);
        }
    }
}
