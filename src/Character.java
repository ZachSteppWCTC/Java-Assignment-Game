/**
 * A class of the properties found in both players and enemies.
 * @author Zach Stepp
 */
public class Character {
    private final String name;
    private int hp;
    private int maxhp;
    private int damage;

    public Character(String name, int maxhp) {
        this.name = name;
        this.maxhp = maxhp;
        this.hp = maxhp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public int getDamage() {
        return damage;
    }
}
