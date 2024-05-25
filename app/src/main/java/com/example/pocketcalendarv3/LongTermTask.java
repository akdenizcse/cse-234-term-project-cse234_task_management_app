package com.example.pocketcalendarv3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LongTermTask {


    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private String[] toDoList;


    LongTermTask(String title, String description, Date startDate, Date endDate, String[] toDoList) {
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

    public String[] getToDoList() {
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

    public void setToDoList(String[] toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return "LongTermTask{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", toDoList=" + Arrays.toString(toDoList) +
                '}';
    }
}
