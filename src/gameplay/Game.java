package gameplay;

public class Game {
    private int playerTurn;

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void nextTurn() {
        playerTurn = playerTurn == 1 ? 2 : 1;
    }
}
