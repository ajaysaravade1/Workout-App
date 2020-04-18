package com.example.workoutapp;

public class ExerciseModel {
    int video;
    int name;
    String time;
    int counter;

    public ExerciseModel(int video, int name, double time) {
        this.video = video;
        this.name = name;
        this.counter = (int)(time*60);
        this.time = time+" Min";
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}

