/**
 * Shield class for equippable items "armor" that have defense values and a specific slot they belong to.
 * @author Zach Stepp
 */
public class Armor extends Item{
    private final int defense;
    private final String slot;
    public Armor(String name, int value, int defense, String slot) {
        super(name, value);
        this.defense = defense;
        this.slot = slot;
    }

    public int getDefense() {
        return defense;
    }

    public String getSlot() {
        return slot;
    }
}
