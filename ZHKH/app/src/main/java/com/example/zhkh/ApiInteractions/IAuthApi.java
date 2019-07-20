package com.example.zhkh.ApiInteractions;

import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Token;

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
}
