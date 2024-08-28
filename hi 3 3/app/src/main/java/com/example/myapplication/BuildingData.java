package com.example.myapplication;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class BuildingData implements ClusterItem {
    private String id;
    private String name;
    private String major;
    private String picture;
    private String tag;
    private String url;
    private String call;
    private String info;
    private double latitude;
    private double longitude;

    public BuildingData() {
    }

    public BuildingData(String tag, String id, String name, String info, String major,String url,String call, String picture, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.picture = picture;
        this.call = call;
        this.url = url;
        this.tag = tag;
        this.info= info;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Override
    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }
    @Override
    public String getTitle() {
        return id;
    }

    @Override
    public String getSnippet() {
        return null;
    }
    // Getters and setters for the remaining fields

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCall() {
        return call;
    }
    public void setCall(String call) { this.call = call; }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) { this.info = info;}
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}