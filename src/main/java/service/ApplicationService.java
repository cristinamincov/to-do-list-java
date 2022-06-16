package service;

import config.ToDoListDBManager;
import dao.*;
import dao.jdbc.*;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApplicationService {

    public ToDoListDao toDoListDao;
    public TaskDao taskDao;
    public TaskDaoJDBC TaskDaoJDBC;

    ToDoListDBManager toDoListDBManager;
    DataSource dataSource;
    private static ApplicationService instance = null;

    public void setupDAOs() {

    }

    private ApplicationService() {
        toDoListDBManager = new ToDoListDBManager();

        try {
            dataSource = toDoListDBManager.run();
            setupDAOs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }

        return instance;
    }
}
