package com.example.zhkh.Comunication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OpenTaskFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_list_task;
    private View view;

    public OpenTaskFragment() throws InterruptedException, ExecutionException, JSONException {
    }

    public static OpenTaskFragment getInstance() throws InterruptedException, ExecutionException, JSONException {
        Bundle args = new Bundle();
        OpenTaskFragment fragment = new OpenTaskFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        try {
            ListView lv = (ListView) view.findViewById(R.id.taskList);
            ArrayList<Task> taskList = (ArrayList<Task>) Singleton.getInstance().getTaskList();
            for (int i = 0; i < taskList.size(); i++) {
                Task temp = taskList.get(i);
                if (temp.getTaskStatus() != 0 || temp.getTaskStatus() != 1) {
                    taskList.remove(i);
                }
            }
            ListTaskAdapter adapter = new ListTaskAdapter(view.getContext(), R.layout.item_event, taskList);
            lv.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
