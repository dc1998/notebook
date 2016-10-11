package com.example.administrator.notebook.model;

/**
 * Created by Administrator on 2016/10/4.
 */
public class TextContent {
    private int id;
     String time;
     String content;

    public void setId(int id){
        this.id=id;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setContent(String content){
        this.content=content;
    }
    public int getId(){
        return id;
    }
    public String getTime(){
        return time;
    }
    public String getContent(){
        return content;
    }
}
