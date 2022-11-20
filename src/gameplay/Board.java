package gameplay;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Card>> cardsOnBoard;

    public Board() {
        cardsOnBoard = new ArrayList<ArrayList<Card>>();
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

    public boolean isFull(int row) {
        return cardsOnBoard.get(row).size() == 5;
    }

    public void addCard(Card card, int row) {
        cardsOnBoard.get(row).add(card);
    }
}
