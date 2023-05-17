package com.example.cuoikianderoir.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "HOADONNGAYSINh";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "SQL_MADE";

    static final String KEY_NAME = "hoTen";
    static final String KEY_PHONG = "soPhong";
    static final String KEY_TIEN = "tien";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String taoBang = String.format(
                "CREATE TABLE %s(%s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_NAME, KEY_PHONG, KEY_TIEN);

        db.execSQL(taoBang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }
}
