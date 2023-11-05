package subjects.java;

import org.postgresql.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Calendar;

public class ConnectDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/my_test_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "111";
    private static final String INSERT_NEW = "INSERT INTO dish VALUES(?, ?, ?, ?, ?, ?)";
    private Connection connection;

    public ConnectDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();
        PreparedStatement preparedStatement = null;
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
//            preparedStatement = connectDB.getConnection().prepareStatement(INSERT_NEW);
//            preparedStatement.setInt(1, 2);
//            preparedStatement.setString(2, "Inserted title");
//            preparedStatement.setString(3, "Inserted desc");
//            preparedStatement.setFloat(4, 0.2f);
//            preparedStatement.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
//            preparedStatement.setBoolean(6, true);
//            preparedStatement.execute();

            preparedStatement = connectDB.getConnection().prepareStatement("SELECT * FROM dish");
            ResultSet res = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void train(String[] args) {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()) {
//            statement.execute("INSERT INTO animal(anim_name, anim_desc) VALUES ('name', 'desc')");
//            int rsl = statement.executeUpdate("UPDATE animal SET anim_name='Rocky' WHERE id=2");
//            ResultSet res = statement.executeQuery("SELECT * FROM animal");
//            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES ('batch1', 'desc')");
//            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES ('batch2', 'desc')");
//            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES ('batch3', 'desc')");
//            statement.executeBatch();
//            statement.clearBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
