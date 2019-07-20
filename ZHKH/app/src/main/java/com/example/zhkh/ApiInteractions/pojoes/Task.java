package com.example.zhkh.ApiInteractions.pojoes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Task {

    @SerializedName("house")
    @Expose
    private Integer house;
    @SerializedName("historyTasks")
    @Expose
    private List<Object> historyTasks = null;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("photos")
    @Expose
    private List<Object> photos = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("taskStatus")
    @Expose
    private Integer taskStatus;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("houseJSON")
    @Expose
    private HouseJSON houseJSON;
    @SerializedName("worker")
    @Expose
    private Integer worker;

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public List<Object> getHistoryTasks() {
        return historyTasks;
    }

    public void setHistoryTasks(List<Object> historyTasks) {
        this.historyTasks = historyTasks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Object> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Object> photos) {
        this.photos = photos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HouseJSON getHouseJSON() {
        return houseJSON;
    }

    public void setHouseJSON(HouseJSON houseJSON) {
        this.houseJSON = houseJSON;
    }

    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

}
