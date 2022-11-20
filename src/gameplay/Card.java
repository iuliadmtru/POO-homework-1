package gameplay;

import fileio.CardInput;

import java.util.ArrayList;

public abstract class Card {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public Card(CardInput cardInput) {
        mana = cardInput.getMana();
        attackDamage = cardInput.getAttackDamage();
        health = cardInput.getHealth();
        description = cardInput.getDescription();
        colors = cardInput.getColors();
        name = cardInput.getName();
    }

    public Card() {
    }

    public int getMana() {
        return mana;
    }

    public void setMana(final int mana) {
        this.mana = mana;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int placeOnRow(Board board, int row) {
        // check if row is full
        if (board.getCardsOnBoard().get(row).size() == 5) {
            return 1; // ROW_IS_FULL error code
        }
        // add the card
        board.addCard(this, row);

        return 0;
    }

    public abstract int placeOnBoardOf(Player player, Board board);
}
