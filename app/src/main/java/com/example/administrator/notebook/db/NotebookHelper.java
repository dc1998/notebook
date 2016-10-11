package com.example.administrator.notebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/4.
 */
public class NotebookHelper extends SQLiteOpenHelper {
    public static final String CREAT_TABLE="create table tableContent(" +
            "id integer primary key autoincrement," +
            "time text," +
            "textContent text)";

    public NotebookHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREAT_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

}