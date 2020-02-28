package com.chen.cprofit.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "cc.db", null, 1);
    }
    private static DBHelper sHelper=null;
    public static DBHelper getInstance(Context context){
        if (sHelper==null){
            sHelper=new DBHelper(context.getApplicationContext());
        }
        return sHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String stockSql="CREATE TABLE IF NOT EXISTS c_stock(id integer PRIMARY KEY autoincrement,name text(10) UNIQUE,code text(10) UNIQUE);";
        String operationSql="create table if not EXISTS c_operation(id integer PRIMARY KEY AUTOINCREMENT,c_date text(15),c_count integer,c_price double,stock_id integer,c_type integer,c_index integer);";
        db.execSQL(stockSql);
        db.execSQL(operationSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
