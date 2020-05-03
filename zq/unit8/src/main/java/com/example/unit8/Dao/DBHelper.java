package com.example.unit8.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.unit8.Bean.DataBean;
import com.example.unit8.Bean.FlagDataBean;
import com.example.unit8.Bean.OutDataBean;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String DATABASE_NAME = "db_all_account";

    //inaccount表
    private static final String TABLE_NAME_INACCOUNT = "tb_inaccount";
    private static final String[] INACCOUNT_COLUMNS = {"_id", "money", "time", "type", "handler", "mark"};
    private static final String CREATE_TABLE_INACCOUNT = "create table tb_inaccount (_id integer primary key autoincrement, money text,time text,type text,handler text,mark text)";

    //expent表
    private static final String TABLE_NAME_EXPENT = "tb_expent";
    private static final String[] EXPENT_COLUMNS = {"_id", "money", "time", "type", "handler", "mark"};
    private static final String CREATE_TABLE_EXPENT = "create table tb_expent (_id integer primary key autoincrement, money text,time text,type text,handler text,mark text)";

    //flag表
    private static final String TABLE_NAME_FLAG = "tb_flag";
    private static final String[] FLAG_COLUMNS = {"_id","content"};
    private static final String CREATE_TABLE_FLAG = "create table tb_flag (_id integer primary key autoincrement,content text)";

    private DBOpenHelper helper;
    private SQLiteDatabase db;

    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_INACCOUNT);
            sqLiteDatabase.execSQL(CREATE_TABLE_EXPENT);
            sqLiteDatabase.execSQL(CREATE_TABLE_FLAG);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME_INACCOUNT);
            sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME_EXPENT);
            sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME_FLAG);

            onCreate(sqLiteDatabase);


        }
    }

    public DBHelper(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    //inaccount表插入操作
    public void insert(DataBean data) {
        ContentValues values = new ContentValues();
        //将databean里的值保存到表中
        values.put(INACCOUNT_COLUMNS[1], data.getStrMoney());
        values.put(INACCOUNT_COLUMNS[2], data.getStrTime());
        values.put(INACCOUNT_COLUMNS[3], data.getStrType());
        values.put(INACCOUNT_COLUMNS[4], data.getStrHandle());
        values.put(INACCOUNT_COLUMNS[5], data.getStrMark());

        //insert into tb_inaccount values(null,'mr','123',……);
        //第2个参数根据第3个参数设置，第3个参数不为空，第2个参数设为null
        db.insert(TABLE_NAME_INACCOUNT, null, values);
    }

    //expent表插入操作
    public void expentinsert(OutDataBean data) {
        ContentValues values = new ContentValues();
        //将outdatabean里的值保存到表中
        values.put(EXPENT_COLUMNS[1], data.getStrMoney());
        values.put(EXPENT_COLUMNS[2], data.getStrTime());
        values.put(EXPENT_COLUMNS[3], data.getStrType());
        values.put(EXPENT_COLUMNS[4], data.getStrHandle());
        values.put(EXPENT_COLUMNS[5], data.getStrMark());

        //insert into tb_inaccount values(null,'mr','123',……);
        //第2个参数根据第3个参数设置，第3个参数不为空，第2个参数设为null
        db.insert(TABLE_NAME_EXPENT, null, values);
    }

    //inaccount表查询操作
    public List<String> queryAll() {
        List<String> result = new ArrayList<String>();
        String incomeItem = "";
        // select * from tb_inaccount;
        // 第2个参数，指定要查询的列，null是返回所有列
        // 第3个参数，where子句，占位符
        // 第4个参数，占位符的值
        Cursor cursor = db.query(TABLE_NAME_INACCOUNT, INACCOUNT_COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()) {
            // 根据列名获取索引号cursor.getColumnIndex(INACCOUNT_COLUMNS[0])
            // {"_id", "money", "time", "type", "handler", "mark"};
            incomeItem = cursor.getInt(0) + " | " + cursor.getString(3) + "：" +
                    cursor.getString(1) + "元，" + " " + cursor.getString(2)
                    + "，付款方："+cursor.getString(4) + "，备注："+cursor.getString(5);
            result.add(incomeItem);
            Log.e("345", incomeItem);
        }
        return result;
    }

    //expent表查询操作
    public List<String> expentqueryAll() {
        List<String> result = new ArrayList<String>();
        String incomeItem = "";
        // select * from tb_expent;
        // 第2个参数，指定要查询的列，null是返回所有列
        // 第3个参数，where子句，占位符
        // 第4个参数，占位符的值
        Cursor cursor = db.query(TABLE_NAME_EXPENT, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            // 根据列名获取索引号cursor.getColumnIndex(EXPENT_COLUMNS[0])
            // {"_id", "money", "time", "type", "handler", "mark"};
            incomeItem = cursor.getInt(0) + " | " + cursor.getString(3) + "：" +
                    cursor.getString(1) + "元，" + " " + cursor.getString(2)
                    + "，收款方："+cursor.getString(4) + "，备注："+cursor.getString(5);
            result.add(incomeItem);
            Log.e("345", incomeItem);
        }
        return result;
    }

    //inaccount表删除操作
    public void deleteAll() {
        //delete from tb_inaccount;
        db.delete(TABLE_NAME_INACCOUNT, null, null);
    }

    //expent表删除操作
    public void expentdeleteAll() {
        //delete from tb_expent;
        db.delete(TABLE_NAME_EXPENT, null, null);
    }

    //inaccount表删除一条
    public void deleteOne(String strID) {
        //delete from tb_inaccount where _id=?;
        db.delete(TABLE_NAME_INACCOUNT, "_id=?", new String[]{strID});
    }

    //expent表删除一条
    public void expentdeleteOne(String strID) {
        //delete from tb_expent where _id=?;
        db.delete(TABLE_NAME_EXPENT, "_id=?", new String[]{strID});
    }

    //inaccount数据结构操作
    public DataBean queryOne(String strID) {
        // select * from tb_inaccount where _id=?;
        Cursor cursor = db.query(TABLE_NAME_INACCOUNT, null, "_id=?", new String[]{strID}, null, null, null);

        DataBean data = new DataBean();
        while (cursor.moveToNext()) {
            data.set_id(cursor.getInt(0));
            data.setStrMoney(cursor.getString(1));
            data.setStrTime(cursor.getString(2));
            data.setStrType(cursor.getString(3));
            data.setStrHandle(cursor.getString(4));
            data.setStrMark(cursor.getString(5));
        }
        return data;
    }

    //expent数据结构操作
    public OutDataBean expentqueryOne(String strID) {
        // select * from tb_expent where _id=?;
        Cursor cursor = db.query(TABLE_NAME_EXPENT, null, "_id=?", new String[]{strID}, null, null, null);

        OutDataBean data = new OutDataBean();
        while (cursor.moveToNext()) {
            data.set_id(cursor.getInt(0));
            data.setStrMoney(cursor.getString(1));
            data.setStrTime(cursor.getString(2));
            data.setStrType(cursor.getString(3));
            data.setStrHandle(cursor.getString(4));
            data.setStrMark(cursor.getString(5));
        }
        return data;
    }

    //inaccount表更新
    public void UpdateOne(String strID, DataBean data) {
        ContentValues values = new ContentValues();
        values.put(INACCOUNT_COLUMNS[1], data.getStrMoney());
        values.put(INACCOUNT_COLUMNS[2], data.getStrTime());
        values.put(INACCOUNT_COLUMNS[3], data.getStrType());
        values.put(INACCOUNT_COLUMNS[4], data.getStrHandle());
        values.put(INACCOUNT_COLUMNS[5], data.getStrMark());
        // update tb_inaccount set _id=*, money=*, time=*, type=*, handler=*, mark=* where id=?;
        // values要更新的字段及对应的字段的值
        db.update(TABLE_NAME_INACCOUNT, values, "_id=?", new String[]{strID});
    }

    //expent表更新
    public void expentUpdateOne(String strID, OutDataBean data) {
        ContentValues values = new ContentValues();
        values.put(EXPENT_COLUMNS[1], data.getStrMoney());
        values.put(EXPENT_COLUMNS[2], data.getStrTime());
        values.put(EXPENT_COLUMNS[3], data.getStrType());
        values.put(EXPENT_COLUMNS[4], data.getStrHandle());
        values.put(EXPENT_COLUMNS[5], data.getStrMark());
        // update tb_expent set _id=*, money=*, time=*, type=*, handler=*, mark=* where id=?;
        // values要更新的字段及对应的字段的值
        db.update(TABLE_NAME_EXPENT, values, "_id=?", new String[]{strID});
    }

    //flag表添加
    public void flagadd(FlagDataBean data){
        ContentValues values = new ContentValues();
        values.put(FLAG_COLUMNS[1], data.getContent());

        //insert into tb_flag values;
        //第2个参数根据第3个参数设置，第3个参数不为空，第2个参数设为null
        db.insert(TABLE_NAME_FLAG, null, values);
    }

    //flag表删除
    public void flagdel(String strID){
        db.delete(TABLE_NAME_FLAG, "_id=?", new String[]{strID});

    }

    //flag表查询
    public List<String> flagquery(){
        List<String> result = new ArrayList<String>();
        String incomeItem = "";
        Cursor cursor = db.query(TABLE_NAME_FLAG, null, null, null, null, null, null);
        while (cursor.moveToNext()) {

            incomeItem = cursor.getInt(0) + " | " + cursor.getString(1);
            result.add(incomeItem);
            Log.e("345", incomeItem);
        }
        return result;
    }

    //flagdatabean操作
    public FlagDataBean flagqueryone(String strID){
        Cursor cursor = db.query(TABLE_NAME_FLAG, null, "_id=?", new String[]{strID}, null, null, null);

        FlagDataBean flagDataBean = new FlagDataBean();
        while (cursor.moveToNext()) {
            flagDataBean.set_id(cursor.getInt(0));
            flagDataBean.setContent(cursor.getString(1));
        }
        return flagDataBean;
    }

    //flag表更新
    public void FlagUpdateOne(String strID,FlagDataBean flagDataBean){
        ContentValues values=new ContentValues();
        values.put(FLAG_COLUMNS[1],flagDataBean.getContent());
        db.update(TABLE_NAME_FLAG,values,"_id=?",new String[]{strID});
    }


}


