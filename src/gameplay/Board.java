package gameplay;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Card>> cardsOnBoard;

    public Board() {
        cardsOnBoard = new ArrayList<>();
        ArrayList<Card> rowZeroCards = new ArrayList<Card>();
        cardsOnBoard.add(rowZeroCards);
        ArrayList<Card> rowOneCards = new ArrayList<Card>();
        cardsOnBoard.add(rowOneCards);
        ArrayList<Card> rowTwoCards = new ArrayList<Card>();
        cardsOnBoard.add(rowTwoCards);
        ArrayList<Card> rowThreeCards = new ArrayList<Card>();
        cardsOnBoard.add(rowThreeCards);
    }

    /**
     * Returns the cards on the board as a matrix.
     *
     * @return card matrix
     */
    public ArrayList<ArrayList<Card>> getCardsOnBoard() {
        return cardsOnBoard;
    }

    /**
     * Sets the cards on the board as a matrix.
     *
     * @param cards card matrix
     */
    public void setCardsOnBoard(final ArrayList<ArrayList<Card>> cards) {
        cardsOnBoard = cards;
    }

    /**
     * Returns the cards on the board corresponding to a given player.
     *
     * @param playerIdx index of player whose cards are returned
     * @return card matrix
     */
    public ArrayList<ArrayList<Card>> getPlayerCardsOnBoard(final int playerIdx) {
        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();
        if (playerIdx == 1) {
            playerCards.add(cardsOnBoard.get(2));
            playerCards.add(cardsOnBoard.get(3));
        } else {
            playerCards.add(cardsOnBoard.get(0));
            playerCards.add(cardsOnBoard.get(1));
        }
        return playerCards;
    }

    /**
     * Checks if a board row is full.
     *
     * @param row checked row index
     * @return `true` if the row is full, `false` otherwise
     */
    public boolean isFull(final int row) {
        return cardsOnBoard.get(row).size() == 5;
    }

    /**
     * Adds a card on a given row.
     *
     * @param card added card
     * @param row  row index
     */
    public void addCard(final Card card, final int row) {
        cardsOnBoard.get(row).add(card);
    }

    /**
     * Moves a card from a given set of coordinates to another given set of coordinates.
     *
     * @param fromRow source row
     * @param fromCol source col
     * @param toRow destination row
     * @param toCol destination col
     */
    public void moveCard(final int fromRow, final int fromCol, final int toRow, final int toCol) {
        Card movedCard = cardsOnBoard.get(fromRow).get(fromCol);
        // remove from initial position
        cardsOnBoard.get(fromRow).remove(fromCol);
        // place on new position
        cardsOnBoard.get(toRow).add(movedCard);
    }

    /**
     * Remove a card from the board.
     *
     * @param card added card
     */
    public void removeCard(final Card card) {
        for (int rowIdx = 0; rowIdx < 4; rowIdx++) {
            cardsOnBoard.get(rowIdx).remove(card);
        }
    }

    /**
     * Remove a card from a given row on the board.
     *
     * @param card    added card
     * @param fromRow source row index
     */
    public void removeCard(final Card card, final int fromRow) {
        cardsOnBoard.get(fromRow).remove(card);
    }

    /**
     * Remove all cards with zero health from the given row.
     *
     * @param rowIdx source row index
     */
    public void removeDeadCards(final int rowIdx) {
        ArrayList<Card> removedCards = new ArrayList<>();
        for (Card card : cardsOnBoard.get(rowIdx)) {
            if (card.getHealth() == 0) {
                removedCards.add(card);
            }
        }
        // remove the cards from the board
        for (Card card : removedCards) {
            removeCard(card, rowIdx);
        }
    }

    /**
     * Checks if there is a card at a given position.
     *
     * @param rowIdx row index
     * @param colIdx col index
     * @return `true` if a card exists, `false` otherwise
     */
    public boolean hasCardAtPosition(final int rowIdx, final int colIdx) {
        if (cardsOnBoard.get(rowIdx).isEmpty()) {
            return false;
        }
        if (cardsOnBoard.get(rowIdx).size() <= colIdx) {
            return false;
        }
        return true;
    }

    /**
     * Returns the card from the given position.
     *
     * @param rowIdx row index
     * @param colIdx col index
     * @return card from given position
     */
    public Card getCardAtPosition(final int rowIdx, final int colIdx) {
        return cardsOnBoard.get(rowIdx).get(colIdx);
    }

    /**
     * Returns all frozen cards on the board.
     *
     * @return frozen cards on the board
     */
    public ArrayList<Card> getFrozenCards() {
        ArrayList<Card> frozenCards = new ArrayList<>();
        for (ArrayList<Card> cardsOnRow : cardsOnBoard) {
            for (Card card : cardsOnRow) {
                if (card.isFrozen()) {
                    frozenCards.add(card);
                }
            }
        }
        return frozenCards;
    }
}
