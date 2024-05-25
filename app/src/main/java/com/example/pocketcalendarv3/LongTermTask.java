package com.example.pocketcalendarv3;

import java.util.Date;
import java.util.List;

public class LongTermTask {


    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private List<String> toDoList;


    LongTermTask(String title, String description, Date startDate, Date endDate, List<String> toDoList) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.toDoList = toDoList;
    }

    public String getTitle() {
        return title;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
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

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setToDoList(List<String> toDoList) {
        this.toDoList = toDoList;
    }
}
