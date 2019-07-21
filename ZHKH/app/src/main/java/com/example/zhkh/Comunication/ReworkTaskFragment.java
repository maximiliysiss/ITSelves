package com.example.zhkh.Comunication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReworkTaskFragment extends Fragment {
    private String taskName;
    TextView taskNameTV, taskStatus, taskType, taskDesc;
    Button reloadTask;
    public ReworkTaskFragment() {
        // Required empty public constructor
    }

    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString("TaskName", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            taskName = getArguments().getString("TaskName");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_task, container, false);
        Task myTask = uploadTask(taskName, view);
        taskNameTV = (TextView) view.findViewById(R.id.openTaskName);
        taskNameTV.setText(taskName);
        taskStatus = (TextView) view.findViewById(R.id.openTaskStatus);
        taskType = (TextView) view.findViewById(R.id.openTaskCategory);
        taskDesc = (TextView) view.findViewById(R.id.openTaskDesc);
        reloadTask = (Button) view.findViewById(R.id.reloadTask);
        taskStatus.setText(myTask.getTaskStatus());
        taskType.setText(myTask.getCategory());
        taskDesc.setText(myTask.getDescription());
        reloadTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO копия кода из отправки заявки
            }
        });
        return view;
    }
    private Task uploadTask(String name, final View view){
        ApiWorker awt = new ApiWorker("http://85.143.11.233:8000/");
        final Task[] temp1 = new Task[1];
        IAuthApi taskApi = awt.getLog();
        taskApi.getTask(Singleton.getInstance().getToken()).enqueue(new Callback<List<Task>>()
        {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response)
            {
                if(!response.isSuccessful())
                {
                    System.out.println("We got some troubles. But server is okay");
                    return;
                }
                Singleton.getInstance().setTaskList(response.body());
                ListView lv = (ListView) view.findViewById(R.id.taskList);
                ArrayList<Task> taskList = (ArrayList<Task>) response.body();
                for (int i = 0; i < response.body().size(); i++) {
                    Task temp = taskList.get(i);
                    if (temp.getName().equals(taskName)) {
                        temp1[0] = temp;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Task>> call, Throwable t)
            {
                System.out.println(t.getMessage());
            }
        });
        return temp1[0];
    }
}
