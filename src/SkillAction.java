/**
 * This is the skill action class consisting of the properties of actions the player can do to gain xp and certain items, after reaching the level requirement.
 * @author Zach Stepp
 */
public class SkillAction {
    Skill skill;
    private final int xpGain;
    private final int levelRequirement;
    private final Item itemReceived;

    public SkillAction(Skill skill, int xpGain, int levelRequirement, Item itemReceived) {
        this.skill = skill;
        this.xpGain = xpGain;
        this.levelRequirement = levelRequirement;
        this.itemReceived = itemReceived;
    }

    public Skill getSkill() {
        return skill;
    }
    public int getXpGain() {
        return xpGain;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public Item getItemReceived() {
        return itemReceived;
    }

}
