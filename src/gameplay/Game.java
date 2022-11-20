package gameplay;

import fileio.ActionsInput;
import gameplay.commands.DebugCommand;

import java.util.ArrayList;

public class Game {
    private int playerTurn;
    private ArrayList<Player> players;
    private Board gameBoard;
    private int turnCount = 1;
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
        // unfreeze cards
        players.get(playerTurn).unfreezeCards(gameBoard);
        // change player turn
        playerTurn = playerTurn == 1 ? 2 : 1;
        // set/reset turn counter and change round
        if (turnCount == 1) {
            turnCount++;
        } else {
            turnCount = 1;
            this.nextRound();
        }
    }

    public void nextRound() {
        int amount = Math.min(round, 10);
        for (Player player : players) {
            if (!player.getDeck().isEmpty()) {
                player.takeCard();
            }
            player.increaseManaBy(amount);
        }
        round++;
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
