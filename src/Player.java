import java.util.*;
/**
 * A subclass of character including all unique properties of the player and the actions done by them.
 * @author Zach Stepp
 */
public class Player extends Character implements ItemIndex {

    private int defense;
    private int gold = 50;
    private Weapon weapon = coppersword;
    private Shield shield;
    private Armor boots;
    private Armor leggings;
    private Armor chestplate;
    private Armor helmet;
    private List<Item> inventory = new ArrayList<>();
    private ArrayList<Item> bank = new ArrayList<>();

    public Player(String name) {
        super(name, 10);
    }

    /**
     * adds an item to inventory and prints a message
     */
    public void addItemToInventory(Item item) {
        this.inventory.add(item);
        Messages.changeInventory(item, "added to" ,inventory.size());
    }

    /**
     * removes an item from inventory and prints a message
     */
    public void removeItemFromInventory(Item item) {
        this.inventory.remove(item);
        Messages.changeInventory(item, "removed from" ,inventory.size());
    }

    /**
     * Supplies a string of a fancy inventory menu, with player capacity, gold, and all items listed.
     * @return inventory interface
     */
    public String printInventory() {
        StringBuilder str = new StringBuilder();
        str.append("\n|=-=-=-=-=-=-=-=-= Inventory =-=-=-=-=-=-=-=-=|");
        str.append(String.format("\n|    Capacity: (%-2d /28 )         Gold: %-5d  |", inventory.size(), gold));
        for (Item item : inventory)
            str.append(String.format("\n|  %-34s   %-4d  |", item.getName(), item.getValue()));
        str.append("\n|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
        return String.valueOf(str);
    }

    /**
     * Adds an item to the bank
     */
    public void addItemToBank(Item item) {
        removeItemFromInventory(item);
        this.bank.add(item);
    }

    /**
     * Attempts to remove an item from the bank. If inventory is full it will not,
     * otherwise it will
     */
    public void removeItemFromBank(Item item) {
        if (inventory.size() < 28) {
            addItemToInventory(item);
            this.bank.remove(item);
        }
        else
            Messages.inventoryFull("withdraw from", "the bank");
    }

    /**
     * Supplies a string of a fancy bank menu, with all items and their values, and the total value of all items in the bank.
     * @return bank interface
     */
    public String printBank() {
        StringBuilder str = new StringBuilder();
        int total = 0;
        str.append("\n|=-=-=-=-=-=-=-=-=-=- Bank =-=-=-=-=-=-=-=-=-=|");
        for (Item item : bank) total += item.getValue();
        str.append(String.format("\n|    Total Bank Value: %-6d                 |", total));
        for (Item item : bank)
            str.append(String.format("\n|  %-34s   %-4d  |", item.getName(), item.getValue()));
        str.append("\n|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
        return String.valueOf(str);
    }

    /**
     * Adds gold and prints a message
     */
    public void addGold(int gold) {
        this.gold += gold;
        Messages.changeGold("+", gold, this.gold);
    }

    /**
     * Removes gold and prints a message.
     */
    public void removeGold(int gold) {
            this.gold -= gold;
            Messages.changeGold("-", gold, this.gold);
    }

    public int getGold() {
        return gold;
    }

    /**
     * Adds xp to the skill and check if the skill is ready to level up.
     */
    public void addXp(Skill skill, int xpAdded) {
        Messages.addXp(xpAdded, skill);
        skill.setXp(skill.getXp() + xpAdded);
        skill.checkLevelUp(skill);
    }

    /**
     * Attempts to harvest. If all requirements are met including tool, level, and inventory space,
     * then it will harvest the item received from the action or a rare chance item, otherwise it will not.
     */
    public void tryHarvestAction(HarvestAction harvestAction) {
        if (harvestAction.getSkill().getLevel() >= harvestAction.getLevelRequirement()) {
            if (inventory.size() < 28) {
                if (inventory.contains(harvestAction.getSkill().getTool())) {
                    if (Math.random() <= harvestAction.getRandomItemChance()) {
                        addItemToInventory(harvestAction.getRandomItem());
                        Messages.addOn("How Lucky!");
                    } else addItemToInventory(harvestAction.getItemReceived());
                    addXp(harvestAction.getSkill(), harvestAction.getXpGain());
                } else
                    Messages.lackTool(harvestAction.getSkill().getTool().getName(), harvestAction.getSkill().getVerb(), harvestAction.getItemReceived());
            } else
                Messages.inventoryFull(harvestAction.getSkill().getVerb(), harvestAction.getItemReceived().getName());
        } else
            Messages.skillIssue(harvestAction.getSkill().name, harvestAction.getLevelRequirement(), harvestAction.getSkill().getVerb(), harvestAction.getItemReceived().getName(), harvestAction.getSkill().getLevel());
    }

    /**
     * Attempts to craft. If all requirements are met including tool, level, and materials,
     * then it will craft the item received from the craft action, otherwise it will not.
     */
    public void tryCraftAction(CraftAction craftAction) {
        if (craftAction.getSkill().getLevel() >= craftAction.getLevelRequirement()) {
            if (inventory.contains(craftAction.getSkill().getTool())) {
                if (Collections.frequency(inventory, craftAction.getMaterial1()) >= craftAction.getQuantity1() && Collections.frequency(inventory, craftAction.getMaterial2()) >= craftAction.getQuantity2()) {
                    for (int i = 0; i < craftAction.getQuantity1(); i++) removeItemFromInventory(craftAction.getMaterial1());
                    for (int i = 0; i < craftAction.getQuantity2(); i++) removeItemFromInventory(craftAction.getMaterial2());
                    addItemToInventory(craftAction.getItemReceived());
                    addXp(craftAction.getSkill(), craftAction.getXpGain());
                } else
                    Messages.lackMaterials(craftAction.getSkill().getVerb(), craftAction.getItemReceived());
            } else
                Messages.lackTool(craftAction.getSkill().getTool().getName(), craftAction.getSkill().getVerb(), craftAction.getItemReceived());

        } else
            Messages.skillIssue(craftAction.getSkill().getName(), craftAction.getLevelRequirement(), craftAction.getSkill().getVerb(), craftAction.getItemReceived().getName(), craftAction.getSkill().getLevel());
    }

    /**
     * Buys the item if there is enough inventory space, otherwise doesn't.
     */
    public void buyItem(Item item) {
        if (gold >= item.getValue()) {
            if (inventory.size() < 28) {
                removeGold(item.getValue());
                addItemToInventory(item);
            } else Messages.inventoryFull("buy", item.getName());
        } else Messages.lackGold(item);
    }

    /**
     * Sells the item and gives player gold based on the item's value
     */
    public void sellItem(Item item) {
            removeItemFromInventory(item);
            addGold(item.getValue());
    }

    /**
     * Attempts to eat the item found in inventory obtained from name in command by identifying if the item is consumable,
     * and eats it if it is, and doesn't if it's not.
     */
    public void eatItem(String string) {
        if (inventory.contains(findItemByName(string))) {
            if (Objects.equals(String.valueOf(findItemByName(string).getClass()), "class Consumable")) {
                setHp(getHp() + ((Consumable) findItemByName(string)).getHealthGained());
                if (getHp() > getMaxhp()) setHp(getMaxhp());
                Messages.eat(findItemByName(string), ((Consumable) findItemByName(string)).getHealthGained());
                inventory.remove(findItemByName(string));
                Messages.printhealth(this);
            } else Messages.unsuitableItem(findItemByName(string).getName(), "eaten");
        } else Messages.itemNotFound("eat");
    }
    /**
     * Attempts to equip the item in inventory obtained from name in command by identifying the item's class,
     * and if it's armor it will also identify the armor's slot, then equips it there removing it
     * from inventory, adding any current item in the slot to inventory, then equipping the
     * intended item to the slot.
     */
    public void equipItem(Item item) {
        switch (String.valueOf(item.getClass())) {
            case "class Weapon" -> {
                inventory.remove(item);
                if (!(weapon == null))
                    addItemToInventory(weapon);
                weapon = (Weapon) item;
                Messages.equippedItemMessage(item.getName(), "Sword", inventory.size());
            }
            case "class Shield" -> {
                inventory.remove(item);
                if (!(shield == null))
                    addItemToInventory(shield);
                shield = (Shield) item;
                Messages.equippedItemMessage(item.getName(), "Shield", inventory.size());
            }
            case "class Armor" -> {
                if (Objects.equals(findArmorByItem(item).getSlot(), "helmet")) {
                    inventory.remove(item);
                    if (!(helmet == null))
                        addItemToInventory(helmet);
                    helmet = (Armor) item;
                    Messages.equippedItemMessage(item.getName(), "Helmet", inventory.size());
                } else if (Objects.equals(findArmorByItem(item).getSlot(), "chestplate")) {
                    inventory.remove(item);
                    if (!(chestplate == null))
                        addItemToInventory(chestplate);
                    chestplate = (Armor) item;
                    Messages.equippedItemMessage(item.getName(), "Chestplate", inventory.size());
                } else if (Objects.equals(findArmorByItem(item).getSlot(), "leggings")) {
                    inventory.remove(item);
                    if (!(leggings == null))
                        addItemToInventory(leggings);
                    leggings = (Armor) item;
                    Messages.equippedItemMessage(item.getName(), "Leggings", inventory.size());
                } else if (Objects.equals(findArmorByItem(item).getSlot(), "boots")) {
                    inventory.remove(item);
                    if (!(boots == null))
                        addItemToInventory(boots);
                    boots = (Armor) item;
                    Messages.equippedItemMessage(item.getName(), "Boots", inventory.size());
                }
            }
            default -> Messages.unsuitableItem(item.getName(), "equipped");
        }
        calculateStats();
    }

    /**
     * Finds an item by name that is in the inventory, if there is nothing matching the name it returns null
     * @return inventory item found by name or null if no matches
     */
    public Item findItemByName(String name) {
        return inventory.stream().filter(i -> Objects.equals(i.getName().toUpperCase(), name.toUpperCase())).findFirst().orElse(null);
    }
    /**
     * Finds an item by name that is in the shop's stock, if there is nothing matching the name it returns null
     * @return shop item found by name or null if no matches
     */
    public Item findShopItemByName(String name, ArrayList<Item> stock) {
        return stock.stream().filter(i -> Objects.equals(i.getName().toUpperCase(), name.toUpperCase())).findFirst().orElse(null);
    }
    /**
     * attempts to buy an item from a shop by its name, if the item is not found then a message is printed, otherwise it buys the item.
     */
    public void buyShopItemByName(String name, ArrayList<Item> stock) {
        Item foundItem = findShopItemByName(name, stock);
        if (foundItem == null)
            Messages.unsuitableItem(name, "found in this shop's stock");
        else buyItem(foundItem);
    }

    /**
     * Finds an item by name that is able to be crafted, if there is nothing matching the name it returns null
     * @return craftable item found by name or null if no matches
     */
   public Item findCraftItemByName (String name) {
       return CraftAction.getCraftable().stream().filter(i -> Objects.equals(i.getName().toUpperCase(), name.toUpperCase())).findFirst().orElse(null);
   }

    /**
     * attempts to buy an item from inventory by its name, if the item is not found then a message is printed, otherwise it sells the item.
     */
    public void sellItemByName(String name) {
        Item foundItem = findItemByName(name);
        if (foundItem == null)
            Messages.itemNotFound("sell");
        else sellItem(foundItem);
    }

     /**
     * attempts to bank an item from inventory by its name, if the item is not found then a message is printed, otherwise it banks the item.
     */
    public void bankItemByName(String name) {
        Item foundItem = findItemByName(name);
        if (foundItem == null)
            Messages.itemNotFound("bank");
        else addItemToBank(foundItem);
    }

    /**
     * Finds an item by name that is in the bank, if there is nothing matching the name it returns null
     * @return bank item found by name or null if no matches
     */
    public Item findBankItemByName(String name) {
        return bank.stream().filter(i -> Objects.equals(i.getName().toUpperCase(), name.toUpperCase())).findFirst().orElse(null);
    }

    /**
     * attempts to withdraw an item from bank by its name, if the item is not found then a message is printed, otherwise it withdraws the item.
     */
    public void withdrawItemByName(String name) {
        Item foundItem = findBankItemByName(name);
        if (foundItem == null)
            Messages.unsuitableItem(name, "found in bank");
        else removeItemFromBank(foundItem);
    }
    /**
     * attempts to equip an item from inventory by its name, if the item is not found then a message is printed, otherwise it equips the item.
     */
    public void equipItemByName(String name) {
        Item foundItem = findItemByName(name);
        if (foundItem == null)
            Messages.itemNotFound("equip");
        else equipItem(foundItem);
    }
    /**
     * Finds an armor item by respective item that is in the inventory, if there is nothing matching the name it returns null
     * @return armor item found by respective item or null if no matches
     */
    public Armor findArmorByItem(Item item) {
        List<Armor> armorlist = inventory.stream().filter(Armor.class::isInstance).map(Armor.class::cast).toList();
        return armorlist.stream().filter(i -> Objects.equals(i.getName().toUpperCase(), item.getName().toUpperCase())).findFirst().orElse(null);
    }

    /**
     * Supplies a string of a fancy stats menu, including player hp, sum defense and attack, and the defense and attack of all gear.
     * If gear slot is null, then the word "none" will display in its name space, and values will be set to 0.
     * @return stats interface
     */
    public String printStats() {
        StringBuilder str = new StringBuilder();
        str.append("\n|=-=-=-=-=-=-=-=-=-=-= Character =-=-=-=-=-=-=-=-=-=-=-=|");
        str.append(String.format("\n|      HP: (%-1d/%-1d)%-9s Sum DMG: %-4d Sum DEF: %-2d   |", getHp(), getMaxhp(), " ", getDamage(), defense));
        str.append(String.format("\n|  Weapon: %-20s DMG: %-8d DEF: 0    |", (weapon==null) ? "None" : weapon.getName() , (weapon==null) ? 0 : weapon.getDamage()));
        str.append(String.format("\n|  Shield: %-20s DMG: %-8d DEF: %-2d   |", (shield==null) ? "None" : shield.getName() , (shield==null) ? 0 : shield.getDamage(), (shield==null) ? 0 : shield.getDefense()));
        str.append(String.format("\n|  Helmet: %-20s DMG: 0%-7s DEF: %-2d   |", (helmet==null) ? "None" : helmet.getName() ," ", (helmet==null) ? 0 : helmet.getDefense()));
        str.append(String.format("\n|  Chest: %-21s DMG: 0%-7s DEF: %-2d   |", (chestplate==null) ? "None" : chestplate.getName() ," ", (chestplate==null) ? 0 : chestplate.getDefense()));
        str.append(String.format("\n|  Legs: %-22s DMG: 0%-7s DEF: %-2d   |", (leggings==null) ? "None" : leggings.getName() ," ", (leggings==null) ? 0 : leggings.getDefense()));
        str.append(String.format("\n|  Boots: %-21s DMG: 0%-7s DEF: %-2d   |", (boots==null) ? "None" : boots.getName() ," ", (boots==null) ? 0 : boots.getDefense()));
        str.append("\n|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
        return String.valueOf(str);
    }
    /**
     * Supplies a string of character skills and their respective levels, xp, and xp required to reach the next level
     * @return skills interface
     */
    public String printSkills() {
        StringBuilder str = new StringBuilder();
        str.append("\n|=-=-=-=-=-=-=-=-= Skills =-=-=-=-=-=-=-=-=|");
        str.append((String.format("\n|  %-13s   %-5d ( %-6d/ %-6d) |", "Fighting", Fighting.getLevel(), Fighting.getXp(), Fighting.getNextLevel())));
        str.append((String.format("\n|  %-13s   %-5d ( %-6d/ %-6d) |", "Mining", Mining.getLevel(), Mining.getXp(), Mining.getNextLevel())));
        str.append((String.format("\n|  %-13s   %-5d ( %-6d/ %-6d) |", "Woodcutting", Woodcutting.getLevel(), Woodcutting.getXp(), Woodcutting.getNextLevel())));
        str.append((String.format("\n|  %-13s   %-5d ( %-6d/ %-6d) |", "Smithing", Smithing.getLevel(), Smithing.getXp(), Smithing.getNextLevel())));
        str.append((String.format("\n|  %-13s   %-5d ( %-6d/ %-6d) |", "Crafting", Crafting.getLevel(), Crafting.getXp(), Crafting.getNextLevel())));
        str.append("\n|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-|");
        return String.valueOf(str);
    }
    /**
     * Sets the player's damage to the sum of their weapon and shield, and their defense to the sum of their armor and shield
     */
    public void calculateStats() {
        int newDamage = ((weapon==null) ? 0 : weapon.getDamage())
                + ((shield==null) ? 0 : shield.getDamage());
        setDamage(newDamage);
        defense = ((shield==null) ? 0 : shield.getDefense())
                + ((helmet==null) ? 0 : helmet.getDefense())
                + ((chestplate==null) ? 0 : chestplate.getDefense())
                + ((leggings==null) ? 0 : leggings.getDefense())
                + ((boots==null) ? 0 : boots.getDefense());
    }

    /**
     * Attacks an enemy and checks to see if they are still alive. If the attack lands and the enemy is killed,
     * the enemy's health is set to 0 in case of damage overflow, and their health is printed in a message and
     * true is returned. If the attack misses or the attack doesn't kill the enemy, then false is returned.
     * @return true if enemy's health is 0 after an attack, otherwise false
     */
    public boolean killedEnemy(Enemy enemy) {
            if (hitEnemy(enemy)) {
                if (enemy.getHp() <= 0) {
                    enemy.setHp(0);
                    Messages.printhealth(enemy);
                    return true;
                } else {
                    Messages.printhealth(enemy);
                    return false;
                }
            }
            else {
                return false;
            }
        }

    /**
     * Whether the player lands their hit on an opponent is determined by their fighting skill level and a
     * base hit chance of 0.5.
     * @return true if the player lands an attack on the enemy, false if not.
     */
    public boolean hitEnemy(Enemy enemy) {
        if ((Math.random() + ((Fighting.getLevel() - 1) * 0.01)) > 0.5) {
            enemy.setHp(enemy.getHp() - getDamage());
            Messages.hit(this, enemy);
            return true;
        } else {
            Messages.miss(this);
            return false;
        }
    }
    /**
     * Stats are calculated and the enemy's hp is set to full before the fight.
     * If the player dies after the attacks between them and the enemy, then true is returned.
     * If the player kills the enemy first, they are rewarded and false is returned.
     * @return true if the player dies in battle, false if not.
     */
    public boolean diedInBattle(Enemy enemy) {
        calculateStats();
        enemy.setHp(enemy.getMaxhp());
        boolean fightactive = true;
        while (fightactive) {
            if (killedEnemy(enemy)) {
                addXp(Fighting, enemy.getXpGain());
                checkHealthUpgrade();
                Messages.printhealth(this);
                if (enemy.getMainDropChance() > Math.random())
                    checkDiscard(enemy.getMainDrop());
                if (enemy.getRareDropChance() > Math.random())
                    checkDiscard(enemy.getRareDrop());
                fightactive = false;
            }
            else {
                if (enemy.killedPlayer(this)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks if the player's max health can be upgraded based on their fighting level.
     */
    public void checkHealthUpgrade() {
        int newMaxHp;
        newMaxHp = 10 + (Fighting.getLevel() / 2);
        if (newMaxHp != getMaxhp()) {
            setMaxhp(newMaxHp);
            Messages.maxHpIncrease(this);
        }
    }
    /**
     * If when an enemy is killed, and they drop loot, if the player's inventory is full, they will drop the item.
     * If inventory is not full then the item will be added to inventory.
     */
    public void checkDiscard(Item item)
    {
        if (inventory.size() < 28) addItemToInventory(item);
        else Messages.discardItem(item);
    }

    public int getDefense() {
        return defense;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setBoots(Armor boots) {
        this.boots = boots;
    }

    public void setLeggings(Armor leggings) {
        this.leggings = leggings;
    }

    public void setChestplate(Armor chestplate) {
        this.chestplate = chestplate;
    }

    public void setHelmet(Armor helmet) {
        this.helmet = helmet;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public Armor getBoots() {
        return boots;
    }

    public Armor getLeggings() {
        return leggings;
    }

    public Armor getChestplate() {
        return chestplate;
    }

    public Armor getHelmet() {
        return helmet;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}


