package Services;

import DAO.GameDAO;
import Entities.GameInfo;
import Entities.GameSession;
import Entities.User;
import Utilities.DB_Util;
import Utilities.RandomUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionService implements GameDAO{
    @Override
    public void addSession(String player1) {
        DB_Util.connectToDB();
        String insertData = "INSERT INTO teleportationmadnessdb.game_sessions VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DB_Util.connection.prepareStatement(insertData);
            preparedStatement.setInt(1, RandomUtil.getRandom(1000,9999));
            preparedStatement.setString(2, player1);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
    }

    @Override
    public void addPlayerToSession(int sessionID, String player2) {
        DB_Util.connectToDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE teleportationmadnessdb.game_sessions SET player2=\'");
        stringBuilder.append(player2);
        stringBuilder.append("\'WHERE id='");
        stringBuilder.append(sessionID+"'");
        System.out.println(stringBuilder.toString());
        try {
            Statement statement = DB_Util.connection.createStatement();
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
    }

    @Override
    public void removeSession(int sessionID) {
        DB_Util.connectToDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DB_Util.connection
                    .prepareStatement("DELETE FROM teleportationmadnessdb.game_sessions" +
                            " WHERE id='"+sessionID+"'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
    }

    @Override
    public List<GameSession> getAllSessions() {
        String query = "select * from teleportationmadnessdb.game_sessions";

        DB_Util.connectToDB();

        Statement statement = null;
        ResultSet resultSet = null;
        List<GameSession> sessionList = new ArrayList<GameSession>();
        try {
            statement = DB_Util.connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                GameSession gameSession = new GameSession(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));


                sessionList.add(gameSession);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
        return sessionList;
    }

    @Override
    public void addGame(GameSession gs) {
        DB_Util.connectToDB();
        String insertData = "INSERT INTO teleportationmadnessdb.games VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DB_Util.connection.prepareStatement(insertData);
            preparedStatement.setString(1,gs.getPlayer1());
            preparedStatement.setString(2, gs.getPlayer2());
            preparedStatement.setInt(3,1);
            preparedStatement.setInt(4,1);
            preparedStatement.setBoolean(5,true);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
    }

    @Override
    public void updateGame(GameInfo g) {
        DB_Util.connectToDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE teleportationmadnessdb.games SET pos1=\'");
        stringBuilder.append(g.getPos1());
        stringBuilder.append("\',pos2=\'");
        stringBuilder.append(g.getPos2());
        stringBuilder.append("\',firstplayerturn=\'");
        stringBuilder.append(g.isFirstPlayerTurn());
        stringBuilder.append("\'WHERE player1='");
        stringBuilder.append(g.getPlayer1()+"'");
        System.out.println(stringBuilder.toString());
        try {
            Statement statement = DB_Util.connection.createStatement();
            statement.executeUpdate(stringBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DB_Util.disconnectFromDB();
    }

    @Override
    public GameInfo getInfo(String p1) {
        String query = "SELECT * FROM teleportationmadnessdb.games WHERE player1='"+p1+"' or player2='"+p1+"'";
        DB_Util.connectToDB();
        Connection connection = DB_Util.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        GameInfo gameInfo = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            gameInfo = new GameInfo(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getBoolean(5)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameInfo;
    }

    @Override
    public void removeGame(String player1) {
        DB_Util.connectToDB();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DB_Util.connection
                    .prepareStatement("DELETE FROM teleportationmadnessdb.games" +
                            " WHERE player1='"+player1+"'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB_Util.disconnectFromDB();
    }

    @Override
    public boolean shellStart(String player) {
        String query = "SELECT * FROM teleportationmadnessdb.game_sessions WHERE player1='"+player+"' or player2='"+player+"'";
        DB_Util.connectToDB();
        Connection connection = DB_Util.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        GameSession gameSession = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            gameSession = new GameSession(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!gameSession.getPlayer2().equals("")){
            return true;
        }
        return false;
    }

    public GameSession getSession(String player1){
        String query = "SELECT * FROM teleportationmadnessdb.game_sessions WHERE player1='"+player1+"' or player2='"+player1+"'";
        DB_Util.connectToDB();
        Connection connection = DB_Util.connection;
        Statement statement = null;
        ResultSet resultSet = null;
        GameSession gameSession = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            gameSession = new GameSession(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameSession;
    }
}
