/**
 * Weapon class for equippable items "weapons" that have a damage value.
 * @author Zach Stepp
 */
public class Weapon extends Item{
    private int damage;
    public Weapon(String name, int value, int damage) {
        super(name, value);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
