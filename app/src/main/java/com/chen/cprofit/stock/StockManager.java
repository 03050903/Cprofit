package com.chen.cprofit.stock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chen.cprofit.db.DBHelper;

import java.util.ArrayList;

public class StockManager {
    private static final String TABLE_NAME="c_stock";
    public static final String FILED_NAME="name";
    public static final String FILED_CODE="code";
    private static final String FILED_ID="id";
    private DBHelper mHelper;
    public StockManager(Context context){
        mHelper=DBHelper.getInstance(context);
    }
    public long insert(StockBean model){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FILED_CODE,model.getCode());
        values.put(FILED_NAME,model.getName());
        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert;
    }
    public int update(StockBean model){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FILED_CODE,model.getCode());
        values.put(FILED_NAME,model.getName());
        values.put(FILED_ID,model.getId());
        int update = db.update(TABLE_NAME, values, FILED_ID + "=?", new String[]{model.getId() + ""});
        db.close();
        return update;
    }
    public int delete(int id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int delete = db.delete(TABLE_NAME, FILED_ID + "=?", new String[]{id + ""});
        db.close();
        return delete;
    }
    public ArrayList<StockBean> getAllStock()
    {
        ArrayList<StockBean> list=new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                String name=cursor.getString(cursor.getColumnIndex(FILED_NAME));
                String code=cursor.getString(cursor.getColumnIndex(FILED_CODE));
                int id=cursor.getInt(cursor.getColumnIndex(FILED_ID));
                StockBean model=new StockBean();
                model.setCode(code);
                model.setName(name);
                model.setId(id);
                list.add(model);
            }
            cursor.close();
        }
        db.close();
        return list;
    }
}
