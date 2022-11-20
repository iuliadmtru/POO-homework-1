package gameplay;

import fileio.CardInput;
import gameplay.cards.Environment;
import gameplay.cards.Hero;
import gameplay.cards.Minion;
import gameplay.cards.minions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private Hero hero;
    private ArrayList<Card> deck;
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private int mana;

    public Hero getHero() {
        return hero;
    }

    public void setHero(CardInput heroInput) {
        hero = new Hero(heroInput);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<CardInput> deckInput) {
        deck = new ArrayList<Card>();
        for (CardInput cardInput : deckInput) {
            Card card = new Card(cardInput);
            // convert cards to their corresponding type
            switch (cardInput.getName()) {
                // environment
                case "Firestorm":
                case "Winterfell":
                case "Heart Hound":
                    deck.add(new Environment(cardInput));
                    break;
                // minion
                case "Goliath":
                case "Warden":
                    deck.add(new Tank(cardInput));
                    break;
                case "Sentinel":
                case "Berserker":
                    deck.add(new Minion(cardInput));
                    break;
                case "Miraj":
                    deck.add(new Miraj(cardInput));
                    break;
                case "The Ripper":
                    deck.add(new TheRipper(cardInput));
                    break;
                case "Disciple":
                    deck.add(new Disciple(cardInput));
                    break;
                case "The Cursed One":
                    deck.add(new TheCursedOne(cardInput));
                    break;
            }
        }
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void takeCard() {
        cardsInHand.add(deck.get(0));
        deck.remove(0);
    }

    public int getMana() {
        return mana;
    }

    public void increaseManaBy(int amount) {
        mana += amount;
    }

    public void shuffleDeckWithSeed(int seed) {
        Collections.shuffle(deck, new Random(seed));
    }
}
