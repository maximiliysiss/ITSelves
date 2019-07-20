package com.example.zhkh.ApiInteractions;

import android.content.Context;
import android.widget.Toast;

import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.ApiInteractions.pojoes.Token;
import com.example.zhkh.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWorker
{
    private Retrofit rf;
    private Gson gson;
    private String baseUrl;
    private IAuthApi log;

    public IAuthApi getLog() {
        return log;
    }

    public ApiWorker(String baseUrl) //-"https://your.api.url/v2/"
    {
        this.baseUrl = baseUrl;

        gson = new GsonBuilder()
            .setLenient()
            .create();

        rf =  new Retrofit.Builder()
            .baseUrl(this.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        log = rf.create(IAuthApi.class);
    }


    public void GettingTasks()
    {
        log.getTask().enqueue(new Callback<List<Task>>()
        {

            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response)
            {
                if(!response.isSuccessful())
                {
                    System.out.println("We got some troubles. But server is okay");
                    return;
                }

            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t)
            {
                System.out.println(t.getMessage());
            }

        });
    }

}
