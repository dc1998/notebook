package com.example.administrator.notebook.model;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.administrator.notebook.R;
import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class ContentAdapter extends ArrayAdapter<TextContent> {
    private int resourceId;
    public ContentAdapter(Context context,int resourceId, List<TextContent> textContents){
        super(context,resourceId,textContents);
        this.resourceId=resourceId;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        TextContent textContent=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder=new ViewHolder();
            viewHolder.textViewL=(TextView)view.findViewById(R.id.child_textviewL);
            viewHolder.textViewTime=(TextView)view.findViewById(R.id.textview_time);
            viewHolder.childTimeText=(TextView)view.findViewById(R.id.childtime_text);

            view.setTag(viewHolder);
        }else
        {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        long contentLength=textContent.getContent().length();
        viewHolder.childTimeText.setText(textContent.getTime());

        if(contentLength>20)
        viewHolder.textViewL.setText(textContent.getContent().substring(0,20));
        else
            viewHolder.textViewL.setText(textContent.getContent().substring(0,(int)contentLength));
        return view;
    }
    class ViewHolder{
        TextView textViewL;
        TextView textViewTime;
        TextView childTimeText;
    }
}
