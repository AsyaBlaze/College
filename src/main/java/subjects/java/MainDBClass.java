package subjects.java;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDBClass {

    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();

        String query = "Select * from users";
        try {
            Statement statement = connectDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
