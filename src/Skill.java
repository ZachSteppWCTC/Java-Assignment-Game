/**
 * Skills class containing skills had by the player which unlock new recipes and abilities as they are leveled up
 * @author Zach Stepp
 */
public class Skill {
    public final String name;
    private int level = 1;
    private int xp = 0;
    private int nextLevel = 50;
    private final Item tool;
    private final String verb;

    public Skill(String name, Item tool, String verb) {
        this.name = name;
        this.tool = tool;
        this.verb = verb;
    }

    public String getName() {
        return name;
    }
    public Item getTool() { return tool; }
    public String getVerb() {
        return verb;
    }
    public int getLevel() {
        return level;
    }
    public int getXp() {
        return xp;
    }
    public int getNextLevel() {
        return nextLevel;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    /**
     * Checks if the skill is ready to level up, then levels up if able until it cannot anymore.
     * If unable to level up at all the xp progress to the next level will be shown.
     */
    public void checkLevelUp(Skill skill) {
        if (xp >= nextLevel) {
            while (xp >= nextLevel) {
                nextLevel = nextLevel + ((level) * 10) + 50;
                level++;
                System.out.printf("\nYour %s skill has leveled up! It is now level %d.", skill.getName(), skill.getLevel());
            }
        }
        else System.out.printf(" (%d/%d)", xp, nextLevel);
    }
}
