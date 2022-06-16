package dao.jdbc;

import dao.TaskDao;
import dao.ToDoListDao;
import model.Task;
import model.ToDoList;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoJDBC implements TaskDao{

    ToDoListDao toDoListDao;
    private final DataSource dataSource;

    public TaskDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Task task) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO tasks (id, name, time_limit, estimated_duration) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, String.valueOf(task.getId()));
            statement.setString(2, task.getName());
            statement.setString(3, task.getLimit_date());
            statement.setString(4, String.valueOf(task.getDuration()));

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            task.setId(resultSet.getInt(1));

        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Task");
        }

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Task get(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, time_limit, estimated_duration FROM tasks WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) return null;

            String name = resultSet.getString(2);
            String limit_date = resultSet.getString(3);
            String duration = resultSet.getString(4);

            Task task = new Task(name, limit_date, duration);
            task.setId(id);

            return task;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Tasks.");
        }
    }

    public void connectTask(Integer taskId, Integer toDoListId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO ta_to_seq (task_id, todo_list_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, taskId);
            statement.setInt(2, toDoListId);

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getAllByToDoList(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM tasks\n" +
                    "INNER JOIN todo_list to on tasks.id = to.id\n" +
                    "WHERE to.id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            List<Task> result = new ArrayList<>();
            while(resultSet.next()) {
                ToDoList toDo = toDoListDao.get(getId(resultSet.getInt(1)));

                String name = resultSet.getString(2);
                String time_limit = resultSet.getString(3);
                String duration = resultSet.getString(4);


                Task task1 = new Task(name, time_limit, duration);
                task1.setId(resultSet.getInt(1));

                result.add(task1);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Task");
        }
    }

    private Integer getId(int anInt) { return null;}


    @Override
    public List<Task> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, time_limit, duration FROM tasks;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Task> result = new ArrayList<>();
            while(resultSet.next()) {
                String name = resultSet.getString(2);
                String limit_date = resultSet.getString(3);
                String duration = resultSet.getString(4);


                Task task = new Task(name, limit_date, duration);
                task.setId(resultSet.getInt(1));

                result.add(task);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Task.");
        }
    }
}
