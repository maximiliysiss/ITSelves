package com.example.zhkh.ApiInteractions;

import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.RawTask;
import com.example.zhkh.ApiInteractions.pojoes.Schedule;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.ApiInteractions.pojoes.Token;
import com.example.zhkh.ApiInteractions.pojoes.User;

import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IAuthApi
{
    @POST("login/")
    Call<Token> getAuth(@Body Key key);

    @POST("auth/")
    Call<User> getUser(@Body Token token);

    @GET("tasks")
    Call<List<Task>> getTask(@Header("token")String token);

    @GET("schedules")
    Call<List<Schedule>> getSchedule(@Header("token")String token);

    @POST("tasks/sendTask")
    Call<Token> sendTask(@Body RawTask rawTask);
}
