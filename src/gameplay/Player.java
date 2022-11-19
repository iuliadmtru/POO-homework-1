package gameplay;

import fileio.CardInput;

import java.util.ArrayList;

public class Player {
    private Card hero;
    private ArrayList<Card> deck;

    public Player() {
    }

    public Card getHero() {
        return hero;
    }

    public void setHero(CardInput hero) {
        this.hero = new Card(hero);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<CardInput> deck) {
        this.deck = new ArrayList<Card>();
        for (CardInput card : deck) {
            this.deck.add(new Card(card));
        }
    }
}
