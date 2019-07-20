package com.example.zhkh.ApiInteractions;

import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }

    public String tryLogin(String token)
    {
        final Token[] user = new Token[1];
        Key key = new Key();
        key.setKey(token);
        String res = "000";
        IAuthApi log = rf.create(IAuthApi.class);
        log.getAuth(key).enqueue(new Callback <Token>()
        {

        @Override
        public void onResponse(Call<Token> call, Response<Token> response)
        {
            user[0] =response.body();
        }

        @Override
        public void onFailure(Call<Token> call, Throwable t)
        {
            user[0] = new Token();
        }

    });

        res = user[0].getToken();
        return res;
    }
}
