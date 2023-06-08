package com.dba.iplugform.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE user(user_code INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,user_name TEXT, user_key TEXT, state TEXT);";
        db.execSQL(query);
        db.execSQL("INSERT INTO user (user_name, user_key, state) values('admin','admin global','A'), ('global','','A');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "CREATE TABLE user(user_code INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,user_name TEXT, user_key TEXT, state TEXT);";
        db.execSQL(query);
        db.execSQL("INSERT INTO user (user_name, user_key, state) values('admin','admin global','A'), ('global','','A');");
    }
}
