package com.example.zhkh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhkh.ApiInteractions.ApiWorker;

public class LogScreenActivity extends AppCompatActivity {

    private TextView passField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);
        passField = (TextView)findViewById(R.id.passwordField);
    }

    public void onPasswordButClick(View view)
    {
        ApiWorker aw = new ApiWorker("http://85.143.10.92:8001/");
        String user = aw.tryLogin("3a88-e9df-bfd8-26f3");
    }
}
