package com.example.zhkh.Comunication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zhkh.R;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class CloseTaskFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_list_task;
    private View view;

    public CloseTaskFragment() throws InterruptedException, ExecutionException, JSONException {
    }

    public static CloseTaskFragment getInstance() throws InterruptedException, ExecutionException, JSONException {
        Bundle args = new Bundle();
        CloseTaskFragment fragment = new CloseTaskFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        //TODO получить список заявок
       // ListView lv = (ListView) view.findViewById(R.id.taskList);
//        ArrearsAdapter adapter = new ArrearsAdapter(view.getContext(), R.layout.item_event, list);
//        lv.setAdapter(adapter);
        return view;
    }
}
