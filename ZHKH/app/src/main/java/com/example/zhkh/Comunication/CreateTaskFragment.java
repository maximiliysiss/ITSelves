package com.example.zhkh.Comunication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zhkh.R;

public class CreateTaskFragment extends Fragment {


    private Button pushTask;
    private EditText taskDesc;
    private Spinner categories;
    private ImageButton photo1, photo2, photo3;
    private TextView feedbackEmail, eventName;
    public CreateTaskFragment() {
    }


    public static CreateTaskFragment newInstance(String param1, String param2) {
        CreateTaskFragment fragment = new CreateTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_task, container, false);
        pushTask = (Button) view.findViewById(R.id.sendTuskButton);
        taskDesc = (EditText) view.findViewById(R.id.editTextTask);
        categories = (Spinner) view.findViewById(R.id.category);
        photo1 = (ImageButton) view.findViewById(R.id.imageButPrioriry);
        photo2 = (ImageButton) view.findViewById(R.id.imageButSecondPriority1);
        photo3 = (ImageButton) view.findViewById(R.id.imageButSecondPriority2);
        feedbackEmail = (TextView) view.findViewById(R.id.editTextFeedbackEmail);
        eventName = (TextView) view.findViewById(R.id.eventName);
        pushTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = categories.getSelectedItem().toString();
            }
        });
        return view;
    }

}
