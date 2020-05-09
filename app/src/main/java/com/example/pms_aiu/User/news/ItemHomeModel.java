package com.example.pms_aiu.User.news;

public class ItemHomeModel {
    private int mHomeImage, mMapImage;
    private String mDateHome, mTodayHome;
    private String mTime_event,mTitle_event, mOther_event,mSpeaker_event, mLocation_event;

    public ItemHomeModel() {
    }

    public ItemHomeModel(int mHomeImage, int mMapImage, String mDateHome, String mTodayHome, String mTime_event, String mTitle_event, String mOther_event, String mSpeaker_event, String mLocation_event) {
        this.mHomeImage = mHomeImage;
        this.mMapImage = mMapImage;
        this.mDateHome = mDateHome;
        this.mTodayHome = mTodayHome;
        this.mTime_event = mTime_event;
        this.mTitle_event = mTitle_event;
        this.mOther_event = mOther_event;
        this.mSpeaker_event = mSpeaker_event;
        this.mLocation_event = mLocation_event;
    }

    public ItemHomeModel(int mHomeImage, int mMapImage, String mTime_event, String mTitle_event, String mSpeaker_event, String mLocation_event) {
        this.mHomeImage = mHomeImage;
        this.mMapImage = mMapImage;
        this.mTime_event = mTime_event;
        this.mTitle_event = mTitle_event;
        this.mSpeaker_event = mSpeaker_event;
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

    public String getmOther_event() {
        return mOther_event;
    }

    public void setmOther_event(String mOther_event) {
        this.mOther_event = mOther_event;
    }

    public String getmSpeaker_event() {
        return mSpeaker_event;
    }

    public void setmSpeaker_event(String mSpeaker_event) {
        this.mSpeaker_event = mSpeaker_event;
    }

    public String getmLocation_event() {
        return mLocation_event;
    }

    public void setmLocation_event(String mLocation_event) {
        this.mLocation_event = mLocation_event;
    }

    public String getmDateHome() {
        return mDateHome;
    }

    public void setmDateHome(String mDateHome) {
        this.mDateHome = mDateHome;
    }

    public String getmTodayHome() {
        return mTodayHome;
    }

    public void setmTodayHome(String mTodayHome) {
        this.mTodayHome = mTodayHome;
    }
}
