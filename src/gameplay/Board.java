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

    public ArrayList<ArrayList<Card>> getCardsOnBoard() {
        return cardsOnBoard;
    }

    public void setCardsOnBoard(ArrayList<ArrayList<Card>> cards) {
        cardsOnBoard = cards;
    }

    public ArrayList<ArrayList<Card>> getPlayerCardsOnBoard(int playerIdx) {
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

    public boolean isFull(int row) {
        return cardsOnBoard.get(row).size() == 5;
    }

    public void addCard(Card card, int row) {
        cardsOnBoard.get(row).add(card);
    }

    public void moveCard(int fromRow, int fromCol, int toRow, int toCol) {
        Card movedCard = cardsOnBoard.get(fromRow).get(fromCol);
        // remove from initial position
        cardsOnBoard.get(fromRow).remove(fromCol);
        // place on new position
        cardsOnBoard.get(toRow).add(movedCard);
    }

    public void removeCard(Card card) {
        for (int rowIdx = 0; rowIdx < 4; rowIdx++) {
            cardsOnBoard.get(rowIdx).remove(card);
        }
    }

    public void removeCard(Card card, int fromRow) {
        cardsOnBoard.get(fromRow).remove(card);
    }

    public void removeDeadCards(int rowIdx) {
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

    public boolean hasCardAtPosition(int rowIdx, int colIdx) {
        if (cardsOnBoard.get(rowIdx).isEmpty()) {
            return false;
        }
        if (cardsOnBoard.get(rowIdx).size() <= colIdx) {
            return false;
        }
        return true;
    }

    public Card getCardAtPosition(int rowIdx, int colIdx) {
        return cardsOnBoard.get(rowIdx).get(colIdx);
    }

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
