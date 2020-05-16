package com.example.pms_aiu.Models;

import com.google.firebase.database.Exclude;

public class News {
    private String Title;
    private String Image;
    private String Time;
    private String Location;
    private String Description;


    private String mKey;




    public News() {
    }

    public News(String title, String image, String time, String location, String description) {
        Title = title;
        Image = image;
        Time = time;
        Location = location;
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }
}
