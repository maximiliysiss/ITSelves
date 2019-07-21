package com.example.zhkh.ApiInteractions;

import com.example.zhkh.ApiInteractions.pojoes.Task;

import java.util.List;

public class Singleton
{
    private String token;
    private List<Task> taskList;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

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
