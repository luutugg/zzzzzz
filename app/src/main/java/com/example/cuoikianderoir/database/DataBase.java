package com.example.cuoikianderoir.database;

//import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_ID;
import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_NAME;
import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_PHONG;
import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_TIEN;
import static com.example.cuoikianderoir.database.DatabaseHandler.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuoikianderoir.HoaDonNgaySinh;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public DataBase(DatabaseHandler databaseHandle) {
        this.databaseHandle = databaseHandle;
    }

    private DatabaseHandler databaseHandle;

    public void themHoaDon(HoaDonNgaySinh hoaDonNgaySinh) {
        try {
            SQLiteDatabase db = databaseHandle.getWritableDatabase();

            // để điền các giá trị vào các côt
            ContentValues values = new ContentValues();
            //values.put(KEY_ID, hoaDonNgaySinh.getId());
            values.put(KEY_NAME, hoaDonNgaySinh.getHoTen());
            values.put(KEY_PHONG, hoaDonNgaySinh.getSoPhong());
            values.put(KEY_TIEN, hoaDonNgaySinh.getTien());

            db.insert(TABLE_NAME,null,values);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<HoaDonNgaySinh> getDanhSachHoaDon(){

        SQLiteDatabase db = databaseHandle.getReadableDatabase();

        ArrayList<HoaDonNgaySinh> hoaDonNgaySinhArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
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
