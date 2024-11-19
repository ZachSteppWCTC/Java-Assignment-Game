/**
 * A subclass of skill action where, when an item is harvested, it has a chance of granting a lucky item instead
 * @author Zach Stepp
 */
public class HarvestAction extends SkillAction{
    private final Item randomItem;
    private final double randomItemChance;

    public HarvestAction(Skill skill, int xpGain, int levelRequirement, Item itemReceived, Item randomItem, double randomItemChance) {
        super(skill, xpGain, levelRequirement, itemReceived);
        this.skill = skill;
        this.randomItem = randomItem;
        this.randomItemChance = randomItemChance;
    }


    public Item getRandomItem() {
        return randomItem;
    }

    public double getRandomItemChance() {
        return randomItemChance;
    }
}
