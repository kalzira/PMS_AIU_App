package com.example.pms_aiu.Models;
import com.google.firebase.database.Exclude;

public class Notifications {
    private String TitlePush;
    private String Message;
    private String Description;

    private String mKey;


    public Notifications() {
    }

    public Notifications(String titlePush, String message, String description) {
        TitlePush = titlePush;
        Message = message;
        Description = description;
    }

    public String getTitlePush() {
        return TitlePush;
    }

    public void setTitlePush(String titlePush) {
        TitlePush = titlePush;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
