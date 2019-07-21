package com.example.zhkh;

import android.support.v7.app.AppCompatActivity;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.ApiInteractions.pojoes.Token;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogScreenActivity extends AppCompatActivity {

    private TextView passField;
    private Button passBut;
    private String tok;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "key";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);
        passField = (TextView)findViewById(R.id.passwordField);
        passBut = (Button)findViewById(R.id.passwordButton);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String keyst = "";
        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            keyst = mSettings.getString(APP_PREFERENCES_COUNTER, "key");
            // Выводим на экран данные из настроек
            passField.setText(keyst);
            passBut.performClick();
            }

    }

    public void onPasswordButClick(View view) throws InterruptedException {

TimeUnit.SECONDS.sleep(10);
        final SharedPreferences.Editor editor = mSettings.edit();

        ApiWorker aw = new ApiWorker("http://85.143.10.92:8001/");
        IAuthApi login = aw.getLog();

        Key key = new Key();
        key.setKey(passField.getText().toString());
        editor.putString(APP_PREFERENCES_COUNTER, key.getKey());

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
                Singleton.getInstance().setToken(tok);
                System.out.println("Success! "+tok);

                editor.apply();

                Intent intent = new Intent(LogScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {
                System.out.println(t.getMessage());
            }

        });


    }

}
