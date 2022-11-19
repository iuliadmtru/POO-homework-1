package gameplay;

import java.util.ArrayList;

public class Actions {
    private ArrayList<Player> players;
    private Game game;

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Card> getPlayerDeck(int playerIdx) {
        return players.get(playerIdx - 1).getDeck();
    }

    public Card getPlayerHero(int playerIdx) {
        return players.get(playerIdx - 1).getHero();
    }

    public int getPlayerTurn() {
        return game.getPlayerTurn();
    }
}
