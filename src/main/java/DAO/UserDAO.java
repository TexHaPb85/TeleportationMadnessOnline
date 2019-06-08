package DAO;

import Entities.User;

public interface UserDAO {
    boolean isRegistered(User user);

    //boolean isRegistered(String login, String password);

    boolean isPlaying(User user);

    //boolean isPlaying(String login, String password);

    void add(User user);

    void update(User user);

    User getByLogin(String login);

}
