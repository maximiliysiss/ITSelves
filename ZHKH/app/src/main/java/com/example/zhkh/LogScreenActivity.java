package com.example.zhkh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Token;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogScreenActivity extends AppCompatActivity {

    private TextView passField;
    private String tok;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);
        passField = (TextView)findViewById(R.id.passwordField);
    }

    public void onPasswordButClick(View view) throws InterruptedException {

        ApiWorker aw = new ApiWorker("http://85.143.10.92:8001/");
        IAuthApi login = aw.getLog();

        Key key = new Key();
        key.setKey("3a88-e9df-bfd8-26f3");

        login.getAuth(key).enqueue(new Callback<Token>()
        {

            @Override
            public void onResponse(Call<Token> call, Response<Token> response)
            {
                if(!response.isSuccessful())
                {
                    System.out.println("We got some troubles. But server is okay");
                    return;
                }
                tok = response.body().getToken();
                System.out.println("Success! "+tok);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {
                System.out.println(t.getMessage());
            }

        });
    }

}
