package model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private Integer id;

    private String name;
    List<Task> tasksList;

    public ToDoList(Integer id, List<Task> tasksList) {
        this.id = id;
        this.tasksList = tasksList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void addTask(Task task) {
        this.tasksList.add(task);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
