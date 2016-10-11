package com.example.administrator.notebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.notebook.R;
import com.example.administrator.notebook.model.NotebookDB;
import com.example.administrator.notebook.model.TextContent;

/**
 * Created by Administrator on 2016/10/4.
 */
public class SecondActivity extends Activity implements View.OnClickListener {

    TextContent textContent;
    TextView timeText;
    Button returnButton;
    Button done;
    String position;
    NotebookDB notebookDB;
    EditText editText;
    Integer positionInteger;
    int positionInt;
    int newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);
        Intent intent=getIntent();
        positionInt=intent.getIntExtra("listPosition",0);
        newButton=intent.getIntExtra("newButton",0);

        positionInteger = new Integer(positionInt);
        position=String.valueOf(positionInt);
        editText=(EditText)findViewById(R.id.edit_text);
        timeText=(TextView)findViewById(R.id.time_text);
        returnButton=(Button)findViewById(R.id.return_button);
        done=(Button)findViewById(R.id.done_button);
        notebookDB=NotebookDB.getInstance(this);
        returnButton.setOnClickListener(this);

        Log.d("SecondActivity","positionInteger on SecondActivity is"+positionInteger.toString()+"--------------------------------------");
        if(MainActivity.contentList.size()!=0&newButton!=1){
            textContent=MainActivity.contentList.get(positionInt);
            String string=textContent.getContent();
            editText.setText(string);
            MainActivity.adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.return_button:

                if(newButton==1){
                    if(!TextUtils.isEmpty(editText.getText().toString())) {
                        //if you havn't type anything after press newBUtton, won't save any DATA to SQL
                        notebookDB.saveNotebook(String.valueOf(System.currentTimeMillis()),editText.getText().toString());
                        Log.d("SecondActivity","savdData position is "+position+"sssssssssssssssssssssssssssssssssss");
                    }
                    Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                    if(MainActivity.contentList.size()!=0){
                        MainActivity.adapter.notifyDataSetChanged();
                    }
                    newButton=0;
                    startActivity(intent);

                    finish();
                }
                else{
                    Log.d("SecondActivity","updateNotebook position is "+positionInteger+"_____________________________");
                    notebookDB.updataNotebook(editText.getText().toString(),timeText.getText().toString());
                    MainActivity.adapter.notifyDataSetChanged();
                    Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(intent);


                    finish();


                }
                break;
        }
    }
}
