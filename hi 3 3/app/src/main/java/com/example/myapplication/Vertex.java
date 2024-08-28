package com.example.myapplication;

import android.graphics.Bitmap;

public class Vertex {
    double latitude = 0;
    double longitude = 0;
    int id = 0;
    String name = "";
    String tag = "";
    String tag2 = "";
    String tag3 = "";

    public Vertex(double lati, double longi, int id, String name, String tag, String tag2, String tag3) {
        this.latitude = lati;
        this.longitude = longi;
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }

    // 위도와 경도를 기준으로 두 지점 간의 거리측정
    public int calDistance(Vertex p){
        int R = 6378137; // 지구 반지름
        double dLat = Math.toRadians(p.latitude - this.latitude);
        double dLong = Math.toRadians(p.longitude - this.longitude);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(p.latitude)) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return (int)d; // returns the distance in meter
    }
}