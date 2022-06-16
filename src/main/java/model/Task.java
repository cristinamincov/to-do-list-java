package model;

import java.sql.Time;
import java.util.Date;

public class Task {
    private Integer id;
    private String name;
    private String limit_date;
    private String duration;
    private ToDoList toDoList;

    public Task(String name, String limit_date, String duration) {
        this.id = id;
        this.name = name;
        this.limit_date = limit_date;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLimit_date() {
        return limit_date;
    }

    public void setLimit_date(String limit_date) {
        this.limit_date = limit_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                ", name='" + name + '\'' +
                ", limit_date=" + limit_date +
                '}';
    }
}

