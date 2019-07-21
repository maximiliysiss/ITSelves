package com.example.zhkh.ApiInteractions.pojoes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RawTask {

    @SerializedName("Desc")
    @Expose
    private String desc;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Type")
    @Expose
    private int type;
    @SerializedName("Mail")
    @Expose
    private String mail;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}