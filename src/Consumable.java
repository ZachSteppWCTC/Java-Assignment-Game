public class Consumable extends Item{
    private final int healthGained;

    public Consumable(String name, int value, int healthGained) {
        super(name, value);
        this.healthGained = healthGained;
    }

    public int getHealthGained() {
        return healthGained;
    }
}
