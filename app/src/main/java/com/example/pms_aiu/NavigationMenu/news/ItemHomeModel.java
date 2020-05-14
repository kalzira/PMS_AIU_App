package com.example.pms_aiu.NavigationMenu.news;

public class ItemHomeModel {
    private int mHomeImage, mMapImage;
    private String mTime_event,mTitle_event, mLocation_event;

    public ItemHomeModel() {
    }

    public ItemHomeModel(int mHomeImage, int mMapImage, String mTitle_event, String mTime_event,  String mLocation_event) {
        this.mHomeImage = mHomeImage;
        this.mMapImage = mMapImage;

        this.mTime_event = mTime_event;
        this.mTitle_event = mTitle_event;

        this.mLocation_event = mLocation_event;
    }

    public int getmHomeImage() {
        return mHomeImage;
    }

    public void setmHomeImage(int mHomeImage) {
        this.mHomeImage = mHomeImage;
    }

    public int getmMapImage() {
        return mMapImage;
    }

    public void setmMapImage(int mMapImage) {
        this.mMapImage = mMapImage;
    }

    public String getmTime_event() {
        return mTime_event;
    }

    public void setmTime_event(String mTime_event) {
        this.mTime_event = mTime_event;
    }

    public String getmTitle_event() {
        return mTitle_event;
    }

    public void setmTitle_event(String mTitle_event) {
        this.mTitle_event = mTitle_event;
    }


    public String getmLocation_event() {
        return mLocation_event;
    }

    public void setmLocation_event(String mLocation_event) {
        this.mLocation_event = mLocation_event;
    }
}
