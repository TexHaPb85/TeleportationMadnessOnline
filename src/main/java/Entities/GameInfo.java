package Entities;

public class GameInfo {
    private String player1;
    private String player2;
    private int pos1;
    private int pos2;
    private boolean firstPlayerTurn;

    public GameInfo(String player1, String player2, int pos1, int pos2, boolean firstPlayerTurn) {
        this.player1 = player1;
        this.player2 = player2;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.firstPlayerTurn = firstPlayerTurn;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getPos1() {
        return pos1;
    }

    public void setPos1(int pos1) {
        this.pos1 = pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public void setPos2(int pos2) {
        this.pos2 = pos2;
    }

    public boolean isFirstPlayerTurn() {
        return firstPlayerTurn;
    }

    public void setFirstPlayerTurn(boolean firstPlayerTurn) {
        this.firstPlayerTurn = firstPlayerTurn;
    }
}
