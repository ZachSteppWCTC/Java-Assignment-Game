import java.util.ArrayList;
/**
 * A subclass of skill action where items are crafted using materials in the player's inventory
 * @author Zach Stepp
 */
public class CraftAction extends SkillAction {
    private static ArrayList<Item> craftable = new ArrayList<>();
    private final Item material1;
    private final int quantity1;
    private final Item material2;
    private final int quantity2;
    public CraftAction(Skill skill, int xpGain, int levelRequirement, Item itemReceived, Item material1, int quantity1, Item material2, int quantity2) {
        super(skill, xpGain, levelRequirement, itemReceived);
        this.material1 = material1;
        this.quantity1 = quantity1;
        this.material2 = material2;
        this.quantity2 = quantity2;
        itemReceived.setRecipe(this);
        craftable.add(itemReceived);
    }

    public Item getMaterial1() {
        return material1;
    }

    public int getQuantity1() {
        return quantity1;
    }

    public Item getMaterial2() {
        return material2;
    }

    public int getQuantity2() {
        return quantity2;
    }

    public static ArrayList<Item> getCraftable() {
        return craftable;
    }
}
