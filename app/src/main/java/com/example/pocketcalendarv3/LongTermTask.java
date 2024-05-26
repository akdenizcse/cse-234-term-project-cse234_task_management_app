package com.example.pocketcalendarv3;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LongTermTask {


    private String title;

    private String description;

    private String startDate;

    private String endDate;

    private List<String> toDoList;

    private String color;
    LongTermTask(String title, String description, String startDate, String endDate, ArrayList<String> toDoList , String Color) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.toDoList = toDoList;
        this.color = Color;
    }

    public String getTitle() {
        return title;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public List<String> getToDoList() {
        return toDoList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setToDoList(List<String> toDoList) {
        this.toDoList = toDoList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
