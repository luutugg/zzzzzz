package com.example.cuoikianderoir.database;

//import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_ID;

import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_NAME;
import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_PHONG;
import static com.example.cuoikianderoir.database.DatabaseHandler.KEY_TIEN;
import static com.example.cuoikianderoir.database.DatabaseHandler.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
            values.put(KEY_NAME, hoaDonNgaySinh.getHoTen());
            values.put(KEY_PHONG, hoaDonNgaySinh.getSoPhong());
            values.put(KEY_TIEN, hoaDonNgaySinh.getTien());

            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<HoaDonNgaySinh> getDanhSachHoaDonCoDieuKien(String ten) {

        SQLiteDatabase db = databaseHandle.getReadableDatabase();

        ArrayList<HoaDonNgaySinh> list = new ArrayList<>();

        String[] tenCacTruong = {KEY_NAME, KEY_TIEN, KEY_PHONG};

        String tenTruongChuaDieuKien = KEY_NAME + " = ?";

        String[] mangGiaTriTenTruongDieuKien = {ten};

        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = db.query(TABLE_NAME, tenCacTruong, tenTruongChuaDieuKien, mangGiaTriTenTruongDieuKien, groupBy, having, orderBy);

        while (!cursor.isAfterLast()) {
            HoaDonNgaySinh hoaDonNgaySinh = new HoaDonNgaySinh(
                    cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getDouble(2)
            );
            list.add(hoaDonNgaySinh);
            cursor.moveToNext();
        }

        return list;
    }

    public List<HoaDonNgaySinh> getDanhSachHoaDon() {

        SQLiteDatabase db = databaseHandle.getReadableDatabase();

        ArrayList<HoaDonNgaySinh> hoaDonNgaySinhArrayList = new ArrayList<>();

        String[] tenCacTruong = {KEY_NAME, KEY_PHONG, KEY_TIEN};

        String tenTruongChuaDieuKien = null;

        String[] mangGiaTriTenTruongDieuKien = null;

        String groupBy = null;
        String having = null;
        String orderBy = KEY_PHONG + " desc";

        Cursor cursor = db.query(TABLE_NAME, tenCacTruong, tenTruongChuaDieuKien, mangGiaTriTenTruongDieuKien, groupBy, having, orderBy);

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

    public List<HoaDonNgaySinh> getDanhSachHoaDonSoLienLonHon(Double tien) {

        SQLiteDatabase sqLiteDatabase = databaseHandle.getReadableDatabase();

        ArrayList<HoaDonNgaySinh> list = new ArrayList<>();

        String[] tenCacTruong = {KEY_NAME,KEY_PHONG, KEY_TIEN};

//        String tenTruongChuaDieuKien = KEY_TIEN + " > ?";

        String tenTruongChuaDieuKien = null;

        String[] mangGiaTriTenTruongDieuKien = null;

        String groupBy = null;
        String having = null;
        String orderBy = null;

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(TABLE_NAME, tenCacTruong, tenTruongChuaDieuKien, mangGiaTriTenTruongDieuKien, groupBy, having, orderBy);

        if (cursor != null){
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                HoaDonNgaySinh hoaDonNgaySinh = new HoaDonNgaySinh(
                        cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getDouble(2)
                );
                try{
                    boolean soTien =Double.parseDouble(cursor.getString(2))> tien;
                    if (soTien){
                        list.add(hoaDonNgaySinh);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
        }else{
            list.addAll(new ArrayList<>());
        }
        return list;
    }

    public void updateHoaDon(HoaDonNgaySinh hoaDonNgaySinh){
        SQLiteDatabase db = databaseHandle.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, hoaDonNgaySinh.getHoTen());
        contentValues.put(KEY_PHONG, hoaDonNgaySinh.getSoPhong());
        contentValues.put(KEY_TIEN, hoaDonNgaySinh.getTien());

        String dieuKienCot = KEY_NAME + " = ?";

        String[] mangGiaTri = {hoaDonNgaySinh.getHoTen()};

        db.update(TABLE_NAME,contentValues,dieuKienCot,mangGiaTri);
    }

    public void delete(HoaDonNgaySinh hoaDonNgaySinh){
        SQLiteDatabase db = databaseHandle.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, hoaDonNgaySinh.getHoTen());
        contentValues.put(KEY_PHONG, hoaDonNgaySinh.getSoPhong());
        contentValues.put(KEY_TIEN, hoaDonNgaySinh.getTien());

        String dieuKienCot = KEY_NAME + " = ?";

        String[] mangGiaTri = {hoaDonNgaySinh.getHoTen()};

        db.delete(TABLE_NAME,dieuKienCot,mangGiaTri);
    }

}
