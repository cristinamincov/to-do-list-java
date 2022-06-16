package dao;

import model.ToDoList;

import java.util.List;

public interface ToDoListDao {
    void add(ToDoList toDoList);
    void remove(Integer id);
    ToDoList get(Integer id);
}
