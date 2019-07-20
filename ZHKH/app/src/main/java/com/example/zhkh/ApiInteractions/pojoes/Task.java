package com.example.zhkh.ApiInteractions.pojoes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("worker")
    @Expose
    private Integer worker;
    @SerializedName("house")
    @Expose
    private Integer house;
    @SerializedName("historyTasks")
    @Expose
    private List<Object> historyTasks = null;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("photos")
    @Expose
    private List<Object> photos = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("taskStatus")
    @Expose
    private Integer taskStatus;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("houseJSON")
    @Expose
    private HouseJSON houseJSON;
    @SerializedName("workerJSON")
    @Expose
    private WorkerJSON workerJSON;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("comment")
    @Expose
    private Object comment;

    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
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

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public WorkerJSON getWorkerJSON() {
        return workerJSON;
    }

    public void setWorkerJSON(WorkerJSON workerJSON) {
        this.workerJSON = workerJSON;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

}
