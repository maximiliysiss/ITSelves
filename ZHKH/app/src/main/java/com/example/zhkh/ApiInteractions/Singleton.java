package com.example.zhkh.ApiInteractions;

import com.example.zhkh.ApiInteractions.pojoes.Address;
import com.example.zhkh.ApiInteractions.pojoes.Schedule;
import com.example.zhkh.ApiInteractions.pojoes.Task;

import java.util.List;

public class Singleton
{
    private String token;
    private List<Task> taskList;
    private List<Schedule> scheduleList;

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    private String fio;
    private Address adress;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

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
