/**
 * Shield class for equippable items "shields" that have both damage and defense values.
 * @author Zach Stepp
 */
public class Shield extends Item{
    private int damage;
    private int defense;
    public Shield(String name, int value, int damage, int defense) {
        super(name, value);
        this.damage = damage;
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }
}
