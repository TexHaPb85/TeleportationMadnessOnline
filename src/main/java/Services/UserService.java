package Services;

import DAO.UserDAO;
import Entities.User;
import Utilities.DB_Util;

import java.sql.*;

public class UserService implements UserDAO {

    @Override
    public  User getByLogin(String login) {
        String query = "SELECT * FROM teleportationmadnessdb.users WHERE login='"+login+"'";
        DB_Util.connectToDB();
        Connection connection = DB_Util.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getBoolean(3),
                    resultSet.getString(4)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isRegistered(User user) {
        String query = "SELECT * FROM teleportationmadnessdb.users WHERE " +
                "login='"+ user.getLogin()
                + "'and password='"+user.getPassword()+"'";

        DB_Util.connectToDB();
        Connection connection = DB_Util.connection;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            DB_Util.disconnectFromDB();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            DB_Util.disconnectFromDB();
            return false;
        }
    }

    @Override
    public boolean isPlaying(User user) {
        User result = getByLogin(user.getLogin());
        return result.isPlaying();
    }

    @Override
    public void add(User user) {
        DB_Util.connectToDB();
        String insertData = "INSERT INTO teleportationmadnessdb.users VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DB_Util.connection.prepareStatement(insertData);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isPlaying());
            preparedStatement.setString(4,user.getPhotoURL());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
    }

    @Override
    public void update(User user) {
        DB_Util.connectToDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE teleportationmadnessdb.users SET login=\'");
        stringBuilder.append(user.getLogin());
        stringBuilder.append("\',password=\'");
        stringBuilder.append(user.getPassword());
        stringBuilder.append("\',isplaying=\'");
        stringBuilder.append(user.isPlaying());
        stringBuilder.append("\'WHERE login='");
        stringBuilder.append(user.getLogin()+"'");
        System.out.println(stringBuilder.toString());
        try {
            Statement statement = DB_Util.connection.createStatement();
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DB_Util.disconnectFromDB();
    }
}