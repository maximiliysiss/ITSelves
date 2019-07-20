package com.example.zhkh.ApiInteractions;

public class Singleton
{
    private String token;
    private static Singleton singleton = null;
    private Singleton()
    {
    }
    public static Singleton getInstance()
    {
        if(singleton==null)
            singleton = new Singleton();
        return singleton;
    }

    public void setToken(String token)
    {this.token=token;}
    public String getToken()
    {return  token;}
}
