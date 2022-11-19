package gameplay;

import fileio.ActionsInput;
import gameplay.commands.DebugCommand;

import java.util.ArrayList;

public class Game {
    private int playerTurn;
    private ArrayList<Player> players;
    private Board gameBoard;
    private int round = 1;

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void nextTurn() {
        playerTurn = playerTurn == 1 ? 2 : 1;
    }

    public void nextRound() {
        for (Player player : players) {
            player.takeCard();
            player.increaseManaBy(round);
        }
        if (round < 10) {
            round++;
        }
    }

    public void run(ActionsInput input) {
        switch (input.getCommand()) {
            // debug commands
            case "getCardsInHand":
            case "getPlayerDeck":
            case "getCardsOnBoard":
            case "getPlayerTurn":
            case "getPlayerHero":
            case "getCardAtPosition":
            case "getPlayerMana":
            case "getEnvironmentCardsInHand":
            case "getFrozenCardsOnBoard":
                (new DebugCommand(input)).runIn(this);
                break;
        }
    }
}
