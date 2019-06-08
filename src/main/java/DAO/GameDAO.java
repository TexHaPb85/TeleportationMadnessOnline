package DAO;

import Entities.GameInfo;
import Entities.GameSession;

import java.util.List;

public interface GameDAO {
    void addSession(String player1);

    void addPlayerToSession(int sessionID, String player2);

    void removeSession(int sessionID);

    List<GameSession> getAllSessions();

    void addGame(GameSession gs);

    void updateGame(GameInfo g);

    GameInfo getInfo(String p1);

    void removeGame(String player1);

    boolean shellStart(String player);
}
