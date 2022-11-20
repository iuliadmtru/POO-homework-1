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
    private int playerIdx;
    private int mana;
    private Hero hero;
    private ArrayList<Card> deck;
    private ArrayList<Card> cardsInHand = new ArrayList<>();

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public int getMana() {
        return mana;
    }

    public void increaseManaBy(int amount) {
        mana += amount;
    }

    public void decreaseManaBy(int amount) {
        mana -= amount;
    }

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
                    deck.add(new RegularMinion(cardInput));
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

    public void shuffleDeckWithSeed(int seed) {
        Collections.shuffle(deck, new Random(seed));
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void takeCard() {
        cardsInHand.add(deck.get(0));
        deck.remove(0);
    }

    public int placeCard(int cardIdx, Board board) {
        if (cardIdx >= cardsInHand.size()) {
            return 0;
        }
        // get the card from the player's hand
        Card card = cardsInHand.get(cardIdx);
        if (card.getMana() > mana) {
            return 3; // NOT_ENOUGH_MANA error code
        }
        // place the card on the board
        int exitCode = card.placeOnBoardOf(this, board);
        // if successful remove the card from the player's hand
        if (exitCode == 0) {
            cardsInHand.remove(cardIdx);
        }
        return exitCode;
    }
}
