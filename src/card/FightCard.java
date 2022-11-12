package card;

abstract public class FightCard extends Card {
    private int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseHealthBy(int amount) {
        health += amount;
    }

    public void defend(int damage) {
        health -= damage;
    }

    abstract public void attack(FightCard opponent);
}
