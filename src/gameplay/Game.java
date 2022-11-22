package gameplay;

import java.util.ArrayList;

public class Game {
    private int playerTurn;
    private ArrayList<Player> players;
    private Board gameBoard;
    private int turnCount = 1;
    private int round = 1;

    /**
     * Returns the index of the current player (1 or 2).
     *
     * @return player index
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Sets the current player index (1 or 2).
     *
     * @param playerTurn player index
     */
    public void setPlayerTurn(final int playerTurn) {
        this.playerTurn = playerTurn;
    }

    /**
     * Returns the game players.
     *
     * @return active players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the game players.
     *
     * @param players active players
     */
    public void setPlayers(final ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Returns the game board.
     *
     * @return game board
     */
    public Board getGameBoard() {
        return gameBoard;
    }

    /**
     * Sets the game board.
     *
     * @param gameBoard game board
     */
    public void setGameBoard(final Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Ends the current player's turn.
     */
    public void nextTurn() {
        Player currentPlayer = players.get(playerTurn - 1);
        // unfreeze cards
        currentPlayer.unfreezeCards(gameBoard);
        // change attack state of cards on board
        currentPlayer.resetAttackStateOfCards(gameBoard);
        // change attack state of hero
        currentPlayer.resetAttackStateOfHero();
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

    /**
     * Ends the current round.
     */
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

    /**
     * Returns the player who is waiting (not the current player).
     *
     * @return waiting player
     */
    public Player getOtherPlayer() {
        return players.get(playerTurn % 2);
    }
}
