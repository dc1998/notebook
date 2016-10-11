package com.example.administrator.notebook.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.notebook.activity.MainActivity;
import com.example.administrator.notebook.db.NotebookHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/4.
 */
public class NotebookDB {
   private SQLiteDatabase db;
    public static NotebookDB notebookDB;
    public static int VERSION=1;
    public static String DB_NAME="note_booke";
    private NotebookDB(Context context){
        NotebookHelper notebookHelper=new NotebookHelper(context,DB_NAME,null,VERSION);
        db=notebookHelper.getWritableDatabase();
    }
    public synchronized static NotebookDB getInstance(Context context){
        if(notebookDB==null){
            notebookDB=new NotebookDB(context);
        }
        return notebookDB;
    }

    public void saveNotebook(String time,String string){

        String insert="insert into tableContent(time,textContent)" +
                "values(?,?)";
        db.execSQL(insert,new String[]{time,string});
    }


    public void updataNotebook(String string,String time){
        String update="update tableContent set textContent=? where time=?";
        db.execSQL(update,new Object[]{string,time});
    }

    public void updateContent(Integer position){
        String update="update tableContent set textContent=? where id=?";
        db.execSQL(update,new Object[]{"4r>>.v3",position+1});

    }


    public void deleteContent(String time){
        String delete="delete from tableContent where time = ?";
        db.execSQL(delete,new Object[]{time});
    }

    public List<TextContent> loadNotebook(){
        List<TextContent> textContentList=new ArrayList<TextContent>();
        Cursor cursor=db.rawQuery("select * from tableContent",null);
        if(cursor.moveToFirst()){
        do{
            TextContent textContent=new TextContent();
            textContent.time=cursor.getString(1);
            textContent.content=cursor.getString(2);

            textContentList.add(textContent);


        }while(cursor.moveToNext());}
        cursor.close();
        return textContentList;
    }


    public String loadContent(Integer position){
        Cursor cursor=db.rawQuery("select * tableContent where id = ?",new String[]{position.toString()});
        String content=cursor.getString(2);
        cursor.close();
        return content;
    }
}
