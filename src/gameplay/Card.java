package gameplay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;

import java.util.ArrayList;

@JsonIgnoreProperties({"frozen"})
public abstract class Card {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private boolean frozen = false;

    public Card(final CardInput cardInput) {
        mana = cardInput.getMana();
        attackDamage = cardInput.getAttackDamage();
        health = cardInput.getHealth();
        description = cardInput.getDescription();
        colors = cardInput.getColors();
        name = cardInput.getName();
    }

    public Card() {
    }

    /**
     * Returns the mana cost of a card.
     *
     * @return card mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * Sets the mana cost of a card.
     *
     * @param mana card mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * Returns the attack damage a card can cause when attacking.
     *
     * @return card attack damage
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Sets the attack damage a card can cause when attacking.
     *
     * @param attackDamage card attack damage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Returns the health of a card.
     *
     * @return card health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of a card.
     *
     * @param health card health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Returns the description of a card.
     *
     * @return card description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of a card.
     *
     * @param description card description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the colors of a card.
     *
     * @return card colors
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * Sets the colors of a card.
     *
     * @param colors card colors
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * Returns the name of a card.
     *
     * @return card name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a card.
     *
     * @param name card name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns `true` is a card is frozen and `false` otherwise.
     *
     * @return freeze state
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Marks the card as frozen or not frozen.
     *
     * @param frozen freeze state
     */
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * Places the card on a player's board.
     *
     * @param player the player whose board the card is placed on
     * @param board  the game board where all cards are placed
     * @return 0 if successful, error code otherwise
     */
    public abstract int placeOnBoardOf(Player player, Board board);
}
