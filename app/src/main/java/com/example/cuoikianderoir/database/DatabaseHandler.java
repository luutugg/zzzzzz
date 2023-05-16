package com.example.cuoikianderoir.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cuoikianderoir.HoaDonNgaySinh;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "HOADONNGAYSINh";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "SQL_MADE";

    static final String KEY_ID = "id";
    static final String KEY_NAME = "hoTen";
    static final String KEY_PHONG = "soPhong";
    static final String KEY_TIEN = "tien";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String taoBang = String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_PHONG, KEY_TIEN);

        db.execSQL(taoBang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void themHoaDon(HoaDonNgaySinh hoaDonNgaySinh) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            // để điền các giá trị vào các côt
            ContentValues values = new ContentValues();
            values.put(KEY_ID, hoaDonNgaySinh.getId());
            values.put(KEY_NAME, hoaDonNgaySinh.getHoTen());
            values.put(KEY_PHONG, String.valueOf(hoaDonNgaySinh.getSoPhong()));
            values.put(KEY_TIEN, String.valueOf(hoaDonNgaySinh.getTien()));

            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<HoaDonNgaySinh> getDanhSachHoaDon() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HoaDonNgaySinh> hoaDonNgaySinhArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            HoaDonNgaySinh hoaDonNgaySinh = new HoaDonNgaySinh(
                    cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getDouble(2)
            );
            hoaDonNgaySinhArrayList.add(hoaDonNgaySinh);
            cursor.moveToNext();
        }
        return hoaDonNgaySinhArrayList;
    }
}
