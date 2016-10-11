package com.example.administrator.notebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.notebook.R;
import com.example.administrator.notebook.model.ContentAdapter;
import com.example.administrator.notebook.model.NotebookDB;
import com.example.administrator.notebook.model.TextContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/4.
 */
public class MainActivity extends Activity {
    static List<TextContent> contentList=new ArrayList<TextContent>();
    NotebookDB notebookDB;
    static ContentAdapter adapter;
    static ListView listView;
    static TextView childTimeText;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        listView=(ListView)findViewById(R.id.list_View);
        Button newButton=(Button)findViewById(R.id.new_Button);
        notebookDB=NotebookDB.getInstance(MainActivity.this);
        contentList=notebookDB.loadNotebook();



        Log.d("MainActivity",contentList.size()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        if(contentList.size()!=0){
        adapter=new ContentAdapter(MainActivity.this,R.layout.listviewchild_layout,
               contentList );
        listView.setAdapter(adapter);}
        Log.d("MainActivity","onCreat is been excute___________________________________");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view ,
                                    int position,long id){
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("listPosition",position);
                Log.d("MainActivity","onItemClick position is "+position+"++++++++++++++++++++++++++++++++++++");
                startActivity(intent);
                finish();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id){
                ContentAdapter adapter=(ContentAdapter)parent.getAdapter();
                childTimeText=(TextView)view.findViewById(R.id.childtime_text);
                String time=childTimeText.getText().toString();
                notebookDB.deleteContent(time);

                Log.d("MainActivity","long item Click position is "+position+"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLl");
                //if you delete the data,the id in SQL would be changed, now SecondActivity saveId, is the same with the delete one ,but not the Content, id cuo wei le .
                contentList.remove(position);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
               // i++;

                return true;
            }
        });

        newButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("newButton",1);
                startActivity(intent);
                listView.setAdapter(adapter);
                finish();
            }
        });
    }
}
