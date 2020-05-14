package com.example.pms_aiu.Models;

public class News {
    private String Title, Image, Time, Location;


    public News() {
    }

    public News(String title, String image, String time, String location) {
        Title = title;
        Image = image;
        Time = time;
        Location = location;
    }

    public News(String title, String image, String time) {
        Title = title;
        Image = image;
        Time = time;
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
}
