package dao.jdbc;

import dao.ToDoListDao;
import model.ToDoList;

import javax.sql.DataSource;
import java.sql.*;

public class ToDoListDaoJDBC implements ToDoListDao {

    private final DataSource dataSource;

    public ToDoListDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ToDoList toDoList) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO tourist_attractions (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, toDoList.getName());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            toDoList.setId(resultSet.getInt(1));

        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new ToDo List");
        }
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public ToDoList get(Integer id) {
        return null;
    }
}
