package com.example.myapplication;

public class ProblemData {
    private String name;
    private String appLink;

    public ProblemData(){

    }
    public ProblemData(String _name,String applink){
        this.name = _name;

        this.appLink = applink;

    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getApplink(){
        return appLink;
    }
    public void setApplink(String applink) {
        this.appLink = applink;
    }

}
