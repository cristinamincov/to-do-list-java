package dao;

import model.Task;
import model.ToDoList;

import java.util.List;

public interface TaskDao {
    void add(Task task);
    void remove(Integer id);
    Task get(Integer id);
    List<Task> getAllByToDoList(Integer id);
    List<Task> getAll();

}
