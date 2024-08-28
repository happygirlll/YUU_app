package com.example.myapplication;

import com.google.android.gms.maps.model.LatLng;

public class StoreData {
    private String id;
    private String name;
    private String tag;
    private double latitude;
    private double longitude;

    public StoreData() {
    }

    public StoreData(String tag, String id, String name,double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
