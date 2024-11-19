/**
 * Item class that contains the properties of items that are generally stored in the player's inventory and used for a variety of things in the game.
 * @author Zach Stepp
 */
public class Item {
    private final String name;
    private final int value;
    private CraftAction recipe;

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public CraftAction getRecipe() {
        return recipe;
    }

    public void setRecipe(CraftAction recipe) {
        this.recipe = recipe;
    }
}
