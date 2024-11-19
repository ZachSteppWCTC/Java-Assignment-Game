public interface Messages {
    /**
     * Messages that are commonly sent when certain actions occur.
     * @author Zach Stepp
     */
    //Character messages
    static void equippedItemMessage(String itemname, String slot, int inventorysize) {
        System.out.printf("\n%s removed from inventory and equipped to %s slot [%d/28]",itemname, slot, inventorysize);
    }

    static void unsuitableItem(String itemname, String action) {
        System.out.printf("\n%s cannot be %s.", itemname, action);
    }

    static void itemNotFound (String action) {
        System.out.printf("\nYou don't have the item you're trying to %s.", action);
    }

    static void inventoryFull (String action, String itemname) {
        System.out.printf("\nYou cannot %s %s because your inventory is full.", action, itemname);
    }

    static void skillIssue (String skillname, int levelrequired, String action, String itemname, int currentlevel) {
        System.out.printf("\nYour %s skill needs to be level %d to %s %s, but it is only level %d.", skillname, levelrequired, action, itemname, currentlevel);
    }

    static void lackMaterials (String action, Item item) {
        System.out.printf("\nYou lack the proper materials to %s %s. Interact to see recipes.", action, item.getName());
    }

    static void lackTool (String toolname, String action, Item item) {
        System.out.printf("\nYou need a %s to %s %s.", toolname, action, item.getName());
    }

    static void printhealth(Character character) {
        System.out.printf("\n%s's health: (%d/%d)", character.getName(), character.getHp(), character.getMaxhp());
    }

    static void addXp(int xp, Skill skill) {
        System.out.printf("\n+%d %s XP", xp, skill.getName());
    }

    static void changeGold(String symbol, int goldAdded, int gold) {
        System.out.printf("\n%s%d Gold (%d)", symbol, goldAdded, gold);
    }

    static void changeInventory(Item item, String action, int inventorysize) {
        System.out.printf("\n%s %s inventory [%d/28]", item.getName(), action, inventorysize);
    }

    static void lackGold(Item item) {
        System.out.printf("\nYou don't have enough gold to buy %s.", item.getName());
    }

    static void eat(Item item, int gainedHp) {
        System.out.printf("\nYou eat the %s and gain %d health", item.getName(), gainedHp);
    }

    static void hit(Character attacker, Character victim) {
        System.out.printf("\n%s hit %s for %s damage.", attacker.getName(), victim.getName(), attacker.getDamage());
    }

    static void miss(Character attacker) {
        System.out.printf("\n%s missed an attack.", attacker.getName());
    }

    static void maxHpIncrease(Character character) {
        System.out.printf("\nYour max health has increased to %d", character.getMaxhp());
    }

    static void discardItem(Item item) {
        System.out.printf("\n%s was discarded because your inventory is full", item.getName());
    }

    //Command messages
    static void enterName(String action) {
        System.out.printf("\nEnter item name in command to %s.", action);
    }
    //Misc messages
    static void addOn(String message) {
        System.out.print(" " + message);
    }

    //Map messages
    static void invalidDirection() {
        System.out.println("\nThere is nothing in that direction.");
    }
    static void controls() {
        System.out.println("""
                \n
                Commands:
                N/S/E/W or NORTH/SOUTH/EAST/WEST to move in a direction
                INTERACT/I to interact with something in the area and read shop, recipe, or bank listings
                LAST/L to quickly repeat last command
                BUY [Item] to buy an item in stock while at a shop
                SELL [Item] to sell an item while at a shop
                EQUIP [Item] to equip a piece of gear to your character
                EAT/USE [Item] to eat a food item and restore health
                CRAFT [Item] to use materials to craft a new item. Interact at a crafting or smithing area for recipes
                BANK/B/DEPOSIT/D/STORE [Item] to store an item in the bank
                UNBANK/U/WITHDRAW to withdraw an item from the bank
                VIEW INVENTORY/I/BACKPACK/B to view inventory
                VIEW STATS/CHARACTER/C to view character stats and equipped gear
                VIEW SKILLS/S to view skills information
                QUIT to quit the game
                HELP/COMMANDS/? see this menu for command help
                Commands are not case sensitive!\
                """);
    }

    static void smithingrecipes() {
        System.out.println("""
                \n
                Smithing Recipes:
                Smelt ores first! Better ores take more to make one bar.
                Metals: Copper (1 ore), Tin (2 ore), or Iron (3 ore)
                Smeltable Only Metals: Silver (1 ore), Gold (3 ore)
                
                Bar - x Ore
                Sword - 2 Metal + 1 Oak Wood
                Shield - 2 Metal + 2 Oak Wood
                Boots - 3 Metal + 1 Armor Padding
                Leggings - 4 Metal + 2 Armor Padding
                Chestplate - 5 Metal + 2 Armor Padding +
                Helmet - 3 Metal + 2 Armor Padding

                Command: Craft [Item]
                Examples: "Craft Copper Leggings", "Craft Gold Bar"
                """);
    }

    static void craftingrecipes() {
        System.out.println("""
                \n
                Crafting Recipes:
                Woods: Oak, Willow, Maple
                Metals: Silver, Gold
                Gems: Sapphire, Emerald, Ruby
                
                Carving - 3 Wood
                Finished Carving - 1 Carving + 1 Sap
                Pendant - 2 Metal + 1 Gem
                Polished Pendant - 1 Pendant + 1 Sap

                Command: Craft [Item]
                Examples: "Craft Silver Emerald Pendant", "Craft Finished Willow Carving"
                \s""");
    }
}
