package com.example.zhkh.Comunication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListTaskAdapter  extends ArrayAdapter<Task> implements AdapterView.OnItemClickListener{
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Task> itemList;
    private FragmentManager fm;
    Map<Integer,String> dictionary = new HashMap<Integer, String>();


    public ListTaskAdapter(@NonNull Context context, int resource, ArrayList<Task> items,
                           FragmentManager fm) {
        super(context, resource, items);
        this.itemList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.fm = fm;
        dictionary.put(0,"В обработке");
        dictionary.put(1, "В прогрессе");
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putString("TaskName", itemList.get(i).getName().toString());
        switch (itemList.get(i).getTaskStatus()){
            case 2:
                //TODO здесь оценка
                break;
            case 3:
                TaskFragment fragment = new TaskFragment();
                fragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_conteiner,
                        fragment).addToBackStack(null).commit();
                break;
            case 4:
                ReworkTaskFragment fragment1 = new ReworkTaskFragment();
                fragment1.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_conteiner,
                        fragment1).addToBackStack(null).commit();
                break;
        }
    }

    private class ViewHolder {
        final TextView status, date, name, shortMessage;
        ViewHolder(View view){
            status = (TextView) view.findViewById(R.id.taskStatus);
            date = (TextView) view.findViewById(R.id.taskDate);
            name = (TextView) view.findViewById(R.id.taskEventName);
            shortMessage = (TextView) view.findViewById(R.id.taskShortMessage);
        }
    }
}
