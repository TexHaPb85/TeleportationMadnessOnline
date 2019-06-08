package Entities;

public class User {
    private String login;
    private String password;
    private boolean isPlaying;
    private String photoURL;

    public User(String login, String password, boolean isPlaying, String photoURL) {
        this.login = login;
        this.password = password;
        this.isPlaying = isPlaying;
        this.photoURL = photoURL;
    }

    public User(String login, String password, String photoURL) {
        this.login = login;
        this.password = password;
        this.isPlaying = false;
        this.photoURL = photoURL;
    }


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.isPlaying = false;
        this.photoURL ="none";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        return  "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isPlaying=" + isPlaying +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
