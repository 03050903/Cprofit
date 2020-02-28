package com.chen.cprofit.operation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chen.cprofit.db.DBHelper;
import com.chen.cprofit.stock.StockBean;
import com.chen.cprofit.stock.StockManager;

public class OperationManager {
    private static final String TABLE_NAME="c_operation";
    private static final String FILED_DATE="c_date";
    private static final String FILED_PRICE="c_price";
    private static final String FILED_COUNT="c_count";
    private static final String FILED_STOCK_ID="stock_id";
    private static final String FILED_TYPE="c_type";
    private static final String FILED_ID="id";
    private static final String FILED_INDEX="c_index";

    private DBHelper mHelper;
    public OperationManager(Context context){
        this.mHelper=DBHelper.getInstance(context);
    }
    public long insert(OrderBean model){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FILED_DATE,model.getDate());
        values.put(FILED_PRICE,model.getPrice());
        values.put(FILED_STOCK_ID,model.getStockId());
        values.put(FILED_COUNT,model.getCount());
        values.put(FILED_TYPE,model.isBuy()?0:1);
        values.put(FILED_INDEX,model.getIndex());
        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert;
    }
    public long update(OrderBean model){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FILED_DATE,model.getDate());
        values.put(FILED_ID,model.getId());
        values.put(FILED_PRICE,model.getPrice());
        values.put(FILED_STOCK_ID,model.getStockId());
        values.put(FILED_COUNT,model.getCount());
        values.put(FILED_TYPE,model.isBuy()?0:1);
        values.put(FILED_INDEX,model.getIndex());
        long update = db.update(TABLE_NAME, values,FILED_ID+"=?",new String[]{model.getId()+""});
        db.close();
        return update;
    }
    public long delete(int id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int delete = db.delete(TABLE_NAME, FILED_ID + "=?", new String[]{id + ""});
        db.close();
        return delete;
    }
    public OperationInfo getDetailInfo(){
        OperationInfo operationInfo=new OperationInfo();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String detailSql="select c_date,c_index,c_type,c_price,c_count,stock_id,c_operation.id as c_id,name,code,c_stock.id as s_id from c_operation left join c_stock on c_operation.stock_id=c_stock.id;";
        Cursor cursor = db.rawQuery(detailSql, null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                DetailBean model=new DetailBean();
                //初始化操作数据
                OrderBean order=new OrderBean();
                int type=cursor.getInt(cursor.getColumnIndex(FILED_TYPE));
                order.setBuy(type==0);
                order.setCount(cursor.getInt(cursor.getColumnIndex(FILED_COUNT)));
                order.setDate(cursor.getString(cursor.getColumnIndex(FILED_DATE)));
                order.setId(cursor.getInt(cursor.getColumnIndex("c_id")));
                order.setPrice(cursor.getDouble(cursor.getColumnIndex(FILED_PRICE)));
                order.setStockId(cursor.getInt(cursor.getColumnIndex(FILED_STOCK_ID)));
                order.setIndex(cursor.getInt(cursor.getColumnIndex(FILED_INDEX)));
                model.setOrder(order);
                //初始化股票数据
                StockBean stock=new StockBean();
                stock.setName(cursor.getString(cursor.getColumnIndex(StockManager.FILED_NAME)));
                stock.setCode(cursor.getString(cursor.getColumnIndex(StockManager.FILED_CODE)));
                stock.setId(cursor.getInt(cursor.getColumnIndex("s_id")));
                model.setStock(stock);

                operationInfo.add(model);
            }
            cursor.close();
        }
        db.close();
        return operationInfo;
    }
}

