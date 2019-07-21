package com.example.zhkh.Comunication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListTaskAdapter  extends ArrayAdapter<Task> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Task> itemList;
    Map<Integer,String> dictionary = new HashMap<Integer, String>();


    public ListTaskAdapter(@NonNull Context context, int resource, ArrayList<Task> items) {
        super(context, resource, items);
        this.itemList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        dictionary.put(0,"В прогрессе");
        dictionary.put(1, "В обработке");
        dictionary.put(2, "Ждет вашей оценки");
        dictionary.put(3, "Завершена");
        dictionary.put(4, "Отклонена");
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Task item = itemList.get(position);

        viewHolder.status.setText(dictionary.get(item.getTaskStatus()));
        viewHolder.date.setText(item.getDateTime()+" ");
        viewHolder.name.setText(item.getName()+" ");
        viewHolder.shortMessage.setText(item.getShortName()+" ");
        return convertView;
    }

    private class ViewHolder {
        final TextView status, date, name, shortMessage;
        final ImageView taskPhoto;
        ViewHolder(View view){
            status = (TextView) view.findViewById(R.id.taskStatus);
            date = (TextView) view.findViewById(R.id.taskDate);
            name = (TextView) view.findViewById(R.id.taskEventName);
            shortMessage = (TextView) view.findViewById(R.id.taskShortMessage);
            taskPhoto = (ImageView) view.findViewById(R.id.taskPhoto);
        }
    }
}
