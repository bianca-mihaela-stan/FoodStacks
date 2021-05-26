package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import Classes.*;

public class SetUpDataUsingStatemnt {
    public void createTable()   {
        String createTableSql = "CREATE TABLE IF NOT EXISTS person" +
            "(id int PRIMARY KEY AUTO_INCREMENT, name varchar(40), age double)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insert(Client client) {
        String insertPersonSql = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getUsername());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5,client.getPassword());
            preparedStatement.setString(5,client.getPhoneNumber());
            preparedStatement.setInt(5,client.getCart().id);

            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }


}
