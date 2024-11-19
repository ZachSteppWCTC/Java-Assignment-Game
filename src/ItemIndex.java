import java.util.ArrayList;
import java.util.Arrays;
/**
 * Game objects created here and implemented wherever needed. Lots of items and skill actions.
 * @author Zach Stepp
 */
public interface ItemIndex {

    //Mining items
    Item pickaxe = new Item("Pickaxe", 20);
    Item copper = new Item("Copper Ore", 3);
    Item sapphire = new Item("Sapphire", 60);
    Item tin = new Item("Tin Ore", 7);
    Item emerald = new Item("Emerald", 100);
    Item iron = new Item("Iron Ore", 10);
    Item ruby = new Item("Ruby", 140);
    Item silver = new Item("Silver Ore", 3);
    Item gold = new Item("Gold Ore", 10);

    //Woodcutting items
    Item axe = new Item("Axe", 20);
    Item oak = new Item("Oak Wood", 6);
    Item apple = new Consumable("Apple", 10, 3);
    Item willow = new Item("Willow Wood", 14);
    Item birdnest = new Item("Bird's Nest", 150);
    Item maple = new Item("Maple Wood", 26);
    Item sap = new Item("Sap", 20);

    //Smithing items
    Item hammer = new Item("Hammer", 10);
    Item copperbar = new Item("Copper Bar", 12);
    Item tinbar = new Item("Tin Bar", 28);
    Item ironbar = new Item("Iron Bar", 40);
    Item silverbar = new Item("Silver Bar", 12);
    Item goldbar = new Item("Gold Bar", 40);
    Item armorpad = new Item("Armor Padding", 10);
    Weapon coppersword = new Weapon("Copper Sword", 36, 2);
    Shield coppershield = new Shield("Copper Shield", 43, 1, 1);
    Armor copperboots = new Armor("Copper Boots", 55, 1, "boots");
    Armor copperlegs = new Armor("Copper Leggings", 81, 2, "leggings");
    Armor copperchest = new Armor("Copper Chestplate", 96, 3, "chestplate");
    Armor copperhelm = new Armor("Copper Helmet", 55, 1, "helmet");
    Weapon tinsword = new Weapon("Tin Sword", 74, 4);
    Shield tinshield = new Shield("Tin Shield", 81, 2, 2);
    Armor tinboots = new Armor("Tin Boots", 112, 2, "boots");
    Armor tinlegs = new Armor("Tin Leggings", 158, 4, "leggings");
    Armor tinchest = new Armor("Tin Chestplate", 192, 6, "chestplate");
    Armor tinhelm = new Armor("Tin Helmet", 112, 2, "helmet");
    Weapon ironsword = new Weapon("Iron Sword", 103, 7);
    Shield ironshield = new Shield("Iron Shield", 110, 3, 3);
    Armor ironboots = new Armor("Iron Boots", 156, 3, "boots");
    Armor ironlegs = new Armor("Iron Leggings", 216, 6, "leggings");
    Armor ironchest = new Armor("Iron Chestplate", 264, 8, "chestplate");
    Armor ironhelm = new Armor("Iron Helmet", 156, 3, "helmet");
    Weapon dragonsword = new Weapon("Dragon Sword", 4000, 10);
    Shield dragonshield = new Shield("Dragon Shield", 4000, 5, 5);
    Armor dragonboots = new Armor("Dragon Boots", 4000, 6, "boots");
    Armor dragonlegs = new Armor("Dragon Leggings", 5000, 8, "leggings");
    Armor dragonchest = new Armor("Dragon Chestplate", 6000, 10, "chestplate");
    Armor dragonhelm = new Armor("Dragon Helmet", 4000, 6, "helmet");

    //Crafting items
    Item chisel = new Item("Chisel", 10);
    Item oakcarving = new Item("Oak Carving", 22);
    Item willowcarving = new Item("Willow Carving", 52);
    Item maplecarving = new Item("Maple Carving", 98);
    Item finishedoakcarving = new Item("Finished Oak Carving", 55);
    Item finishedwillowcarving = new Item("Finished Willow Carving", 130);
    Item finishedmaplecarving = new Item("Finished Maple Carving", 245);
    Item silversaphpendant = new Item("Silver Sapphire Pendant", 168);
    Item silverempendant = new Item("Silver Emerald Pendant", 248);
    Item silverrubypendant = new Item("Silver Ruby Pendant", 328);
    Item goldsaphpendant = new Item("Gold Sapphire Pendant", 420);
    Item goldempendant = new Item("Gold Emerald Pendant", 540);
    Item goldrubypendant = new Item("Gold Ruby Pendant", 660);
    Item polsilversaphpendant = new Item("Polished Silver Sapphire Pendant", 336);
    Item polsilverempendant = new Item("Polished Silver Emerald Pendant", 469);
    Item polsilverrubypendant = new Item("Polished Silver Ruby Pendant", 656);
    Item polgoldsaphpendant = new Item("Polished Gold Sapphire Pendant", 840);
    Item polgoldempendant = new Item("Polished Gold Emerald Pendant", 1080);
    Item polgoldrubypendant = new Item("Polished Gold Ruby Pendant", 1320);

    //Consumable items
    Consumable bread = new Consumable("Bread", 25, 6);
    Consumable steak = new Consumable("Steak", 50, 9);
    Consumable dragonsteak = new Consumable("Dragon Steak", 200, 100);

    //Misc items
    Item dirtycloth = new Item("Dirty Cloth", 10);
    Item oldbone = new Item("Old Bone", 20);
    Item diamond = new Item("Diamond", 300);


    //Mining Skill and Actions
    Skill Mining = new Skill("Mining", pickaxe, "mine");
    HarvestAction mineCopper = new HarvestAction(Mining, 5, 1, copper, sapphire, 0.1);
    HarvestAction mineTin = new HarvestAction(Mining, 15, 6, tin, emerald, 0.05);
    HarvestAction mineIron = new HarvestAction(Mining, 30, 11, iron, ruby, 0.05);
    HarvestAction mineSilver = new HarvestAction(Mining, 5, 1, silver, null, 0);
    HarvestAction mineGold = new HarvestAction(Mining, 20, 9, gold, null, 0);
    //Woodcutting Skill and Actions
    Skill Woodcutting = new Skill("Woodcutting", axe, "chop");
    HarvestAction chopOak = new HarvestAction(Woodcutting, 5, 1, oak, apple, 0.05);
    HarvestAction chopWillow = new HarvestAction(Woodcutting, 15, 4, willow, birdnest, 0.01);
    HarvestAction chopMaple = new HarvestAction(Woodcutting, 30, 8, maple, sap, 0.1);
    //Smithing Skill and Actions
    Skill Smithing = new Skill("Smithing", hammer, "craft");
    /**
     * All crafting actions are found by item name in commands, thus a warning "never used" is given.
     */
    CraftAction smeltCopper = new CraftAction(Smithing, 5, 1, copperbar, copper, 1, oak, 0);
    CraftAction smeltSilver = new CraftAction(Smithing, 5, 1, silverbar, silver, 1, oak, 0);
    CraftAction smeltTin = new CraftAction(Smithing,10, 6, tinbar, tin, 2, oak, 0);
    CraftAction smeltGold = new CraftAction(Smithing, 10, 9, goldbar, gold, 3, oak, 0);
    CraftAction smeltIron = new CraftAction(Smithing,15, 11, ironbar, iron, 3, oak, 0);
    CraftAction craftCopperSword = new CraftAction(Smithing,20, 1, coppersword, copperbar, 2, oak, 1);
    CraftAction craftCopperBoots = new CraftAction(Smithing,30, 1, copperboots, copperbar, 3, armorpad, 1);
    CraftAction craftCopperHelm = new CraftAction(Smithing,30, 2, copperhelm, copperbar, 3, armorpad, 1);
    CraftAction craftCopperLegs = new CraftAction(Smithing,40, 3, copperlegs, copperbar, 4, armorpad, 2);
    CraftAction craftCopperShield = new CraftAction(Smithing,20, 4, coppershield, copperbar, 2, oak, 2);
    CraftAction craftCopperChest = new CraftAction(Smithing,50, 5, copperchest, copperbar, 5, armorpad, 2);
    CraftAction CraftTinSword = new CraftAction(Smithing,40, 6, tinsword, tinbar, 2, oak, 1);
    CraftAction CraftTinBoots = new CraftAction(Smithing,60, 6, tinboots, tinbar, 3, armorpad, 1);
    CraftAction CraftTinHelm = new CraftAction(Smithing,60, 7, tinhelm, tinbar, 3, armorpad, 1);
    CraftAction CraftTinLegs = new CraftAction(Smithing,80, 8, tinlegs, tinbar, 4, armorpad, 2);
    CraftAction CraftTinShield = new CraftAction(Smithing,40, 9, tinshield, tinbar, 2, oak, 2);
    CraftAction CraftTinChest = new CraftAction(Smithing,100, 10, tinchest, tinbar, 5, armorpad, 2);
    CraftAction craftIronSword = new CraftAction(Smithing,60, 11, ironsword, ironbar, 2, oak, 2);
    CraftAction craftIronBoots = new CraftAction(Smithing,90, 11, ironboots, ironbar, 3, armorpad, 1);
    CraftAction craftIronHelm = new CraftAction(Smithing,90, 12, ironhelm, ironbar, 3, armorpad, 1);
    CraftAction craftIronLegs = new CraftAction(Smithing,120, 13, ironlegs, ironbar, 4, armorpad, 2);
    CraftAction craftIronShield = new CraftAction(Smithing,60, 14, ironshield, ironbar, 2, oak, 2);
    CraftAction craftIronChest = new CraftAction(Smithing,150, 15, ironchest, ironbar, 5, armorpad, 2);

    //Crafting Skill and Actions
    Skill Crafting = new Skill("Crafting", chisel, "craft");
    CraftAction craftOakCarving = new CraftAction(Crafting, 20,1, oakcarving, oak, 3, sap, 0);
    CraftAction craftWillowCarving = new CraftAction(Crafting,40, 4, willowcarving, willow, 3, sap, 0);
    CraftAction craftMapleCarving = new CraftAction(Crafting,60, 8, maplecarving, maple, 3, sap, 0);
    CraftAction craftFinishedOakCarving = new CraftAction(Crafting,120, 8, finishedoakcarving, oakcarving, 1, sap, 1);
    CraftAction craftFinishedWillowCarving = new CraftAction(Crafting,140, 9, finishedwillowcarving, willowcarving, 1, sap, 1);
    CraftAction craftFinishedMapleCarving = new CraftAction(Crafting,160, 10, finishedmaplecarving, maplecarving, 1, sap, 1);
    CraftAction craftsilversaphpendant = new CraftAction(Crafting,40, 1, silversaphpendant, silverbar, 2, sapphire, 1);
    CraftAction craftsilverempendant = new CraftAction(Crafting,80, 3, silverempendant, silverbar, 2, sapphire, 1);
    CraftAction craftsilverrubypendant = new CraftAction(Crafting,100, 5, silverrubypendant, silverbar, 2, sapphire, 1);
    CraftAction craftpolishedsilversaphpendant = new CraftAction(Crafting,140, 8, polsilversaphpendant, silversaphpendant, 1, sap, 1);
    CraftAction craftpolishedsilverempendant = new CraftAction(Crafting,180, 9, polsilverempendant, silverempendant, 1, sap, 1);
    CraftAction craftpolishedsilverrubypendant = new CraftAction(Crafting,200, 10, polsilverrubypendant, silverrubypendant, 1, sap, 1);
    CraftAction craftgoldsaphpendant = new CraftAction(Crafting,40, 7, goldsaphpendant, goldbar, 2, sapphire, 1);
    CraftAction craftgoldempendant = new CraftAction(Crafting,80, 8, goldempendant, goldbar, 2, sapphire, 1);
    CraftAction craftgoldrubypendant = new CraftAction(Crafting,100, 9, goldrubypendant, goldbar, 2, sapphire, 1);
    CraftAction craftpolishedgoldsaphpendant = new CraftAction(Crafting,140, 11, polgoldsaphpendant, goldsaphpendant, 1, sap, 1);
    CraftAction craftpolishedgoldempendant = new CraftAction(Crafting,180, 12, polgoldempendant, goldempendant, 1, sap, 1);
    CraftAction craftpolishedgoldrubypendant = new CraftAction(Crafting,200, 13, polgoldrubypendant, goldrubypendant, 1, sap, 1);

    //Fighting Skill
    Skill Fighting = new Skill("Fighting", null,"hit");
    Enemy goblin = new Enemy("Goblin", 5, 1, 0.6, 10, dirtycloth, 0.4, copperboots, 0.05);
    Enemy minotaur = new Enemy("Minotaur", 20, 3, 0.75, 30, oldbone, 0.4, tinlegs, 0.05);
    Enemy hillgiant = new Enemy("Hill Giant", 50, 6, 0.9, 60, sap, 0.35, diamond, 0.5);
    //Stores
    Store wilma = new Store("Wilma's General Things & Stuff", new ArrayList<>(
            Arrays.asList(coppersword, axe, pickaxe, hammer, chisel, armorpad, sapphire, apple, bread, steak)));

    Store doug = new Store("Doug's Dragon Gear Emporium", new ArrayList<>(
            Arrays.asList(dragonsword, dragonshield, dragonboots, dragonlegs, dragonchest, dragonhelm, dragonsteak)));
    //Events
    Event freePickaxe = new Event("One of the Dwarves approaches you- \"Hey you. Me bones hurt, so I'm retiring from this old mine. Here's me old pickaxe, take care of her for me.\"", "None of the dwarves want to talk with you.", pickaxe);
    Event freeAxe = new Event("You find an old axe in one of the stumps. Hoping nobody will miss it, you take it for yourself", "No trees to chop here, they're already stumps.", axe);

}
