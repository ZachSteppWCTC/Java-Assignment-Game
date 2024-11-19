/**
 * This is the map class containing all zones in the game, movement system, and properties of the map
 * @author  Zach Stepp
 */
public class Map implements ItemIndex{
    private Zone currentZone;
    private final Zone respawnZone;
    private Player player;

    public Map(Player player) {
        this.player = player;
        //creating all zones with their names and descriptions
        Zone TownSquare = new Zone("Java Town Center", "The heart of Java Town. Head east to leave the town walls.");
        Zone Bank = new Zone("Bank", "Java Town's famous bank. Store items for safe-keeping to free up inventory space.");
        Zone GeneralStore = new Zone("General Store", "Wilma's General Store, buy whats in stock or sell anything you don't need.");
        Zone SmithingHub = new Zone("Smithing Hub", "A giant forge stands strong next to a sturdy anvil. Get all of your smithing done here. Weapons, armor, the lot.");
        Zone CraftingHub = new Zone("Crafting Hub", "Tables and tools are scattered around inside the city's entrance. Craft your trinkets here.");
        Zone Outskirts = new Zone("Outskirts", "Standing near the Java Town's walls, it looks looks smaller from the outside.");
        Zone GravelRoad = new Zone("Gravel Road", "A worn road leading to the mines. A few dwarves are standing around, not looking too busy");
        Zone CopperMine = new Zone("Copper Mine", "Veins of copper ore are everywhere in the entrance to the mines.");
        Zone TinMine = new Zone("Tin Mine", "Some tin ore lays deeper in the mine. Better than copper, shinier too.");
        Zone IronMine = new Zone("Iron Mine", "Iron is strewn across the walls at the end of the mine.");
        Zone SilverMine = new Zone("Silver Mine", "Silver rocks are all throughout this area of the mine. Not a great metal for gear, but it's popular for jewellery");
        Zone GoldMine = new Zone("Gold Mine", "A river flows through this section of the mine. Gold outcrops are dotted across it's edges.");
        Zone BarrenPlains = new Zone("Barren Plains", "Abandoned tree stumps are dispersed through the grassy plains");
        Zone OakForest = new Zone("Oak Forest", "Fully grown oak trees are seen in every direction.");
        Zone WillowSwamp = new Zone("Willow Swamp", "Dirty ponds and willow trees cover this muddy area.");
        Zone MapleGrove = new Zone("Maple Grove", "Red and orange maple trees are absolutely everywhere.");
        Zone GoblinCamp = new Zone("Goblin Camp", "Goblins stand by their tents, eyeing you. If you approach one, you might be in for a fight.");
        Zone MinotaurCave = new Zone("Minotaur Cave", "You sneak around the dark cave, avoiding any Minotaurs you don't intend to battle.");
        Zone HillGiantField = new Zone("Hill Giant Field", "10 foot tall Hill Giants stand around in an empty field. They're not hostile on sight, but they're still dangerous.");
        Zone DougStore = new Zone("Doug's Dragon Emporium", "This lone cabin outside the woods sells gear that no normal man could forge. Only Doug.");

        //setting zone directions and unique zone actions
        TownSquare.setNorth(GeneralStore);
        TownSquare.setEast(CraftingHub);
        TownSquare.setSouth(SmithingHub);
        TownSquare.setWest(Bank);
        TownSquare.setZoneAction("fullheal");
        GeneralStore.setSouth(TownSquare);
        GeneralStore.setZoneAction(wilma);
        CraftingHub.setWest(TownSquare);
        CraftingHub.setEast(Outskirts);
        CraftingHub.setZoneAction("crafting");
        SmithingHub.setNorth(TownSquare);
        SmithingHub.setZoneAction("smithing");
        Bank.setEast(TownSquare);
        Bank.setZoneAction("bank");
        Outskirts.setWest(CraftingHub);
        Outskirts.setSouth(GravelRoad);
        Outskirts.setNorth(BarrenPlains);

        GravelRoad.setZoneAction(freePickaxe);
        GravelRoad.setNorth(Outskirts);
        GravelRoad.setSouth(CopperMine);
        CopperMine.setNorth(GravelRoad);
        CopperMine.setEast(SilverMine);
        CopperMine.setSouth(TinMine);
        CopperMine.setZoneAction(mineCopper);
        TinMine.setNorth(CopperMine);
        TinMine.setWest(IronMine);
        TinMine.setZoneAction(mineTin);
        IronMine.setEast(TinMine);
        IronMine.setZoneAction(mineIron);
        SilverMine.setWest(CopperMine);
        SilverMine.setEast(GoldMine);
        SilverMine.setZoneAction(mineSilver);
        GoldMine.setWest(SilverMine);
        GoldMine.setZoneAction(mineGold);

        BarrenPlains.setZoneAction(freeAxe);
        BarrenPlains.setSouth(Outskirts);
        BarrenPlains.setNorth(OakForest);
        BarrenPlains.setEast(GoblinCamp);
        OakForest.setSouth(BarrenPlains);
        OakForest.setNorth(WillowSwamp);
        OakForest.setZoneAction(chopOak);
        WillowSwamp.setSouth(OakForest);
        WillowSwamp.setEast(MapleGrove);
        WillowSwamp.setZoneAction(chopWillow);
        MapleGrove.setWest(WillowSwamp);
        MapleGrove.setEast(DougStore);
        MapleGrove.setZoneAction(chopMaple);

        GoblinCamp.setWest(BarrenPlains);
        GoblinCamp.setEast(MinotaurCave);
        GoblinCamp.setZoneAction(goblin);
        MinotaurCave.setWest(GoblinCamp);
        MinotaurCave.setNorth(HillGiantField);
        MinotaurCave.setZoneAction(minotaur);
        HillGiantField.setSouth(MinotaurCave);
        HillGiantField.setNorth(DougStore);
        HillGiantField.setZoneAction(hillgiant);
        DougStore.setSouth(HillGiantField);
        DougStore.setWest(MapleGrove);
        DougStore.setZoneAction(doug);

        // sets the zone where the game begins and where the player respawns if they die
        currentZone = TownSquare;
        respawnZone = TownSquare;
    }

    public String getCurrentZoneName() {
        return currentZone.getName();
    }

    public String getCurrentZoneDescription() {
        return currentZone.getDescription();
    }

    // changes the current direction based on user input
    /**
     * if the direction based on user input is valid, the current zone is changes, otherwise sends a message
     */
    public void move(char direction) {
        if (currentZone.isValidDirection(direction)) {
            currentZone = currentZone.getAdjoiningZone(direction);
        } else Messages.invalidDirection();{
        }
    }

    public String getCurrentZoneExits() {
        return currentZone.getExits();
    }

    public Object getCurrentZoneAction() {
        return currentZone.getZoneAction();
    }

    public void respawn() {
        currentZone = respawnZone;
    }

    // print of the player's current zone
    /**
     * Game objects created here and implemented wherever needed. Lots of items and skill actions.
     * @return zone interface
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n-------------------------------------------------------------------------------");
        str.append("\nCurrent Location: " + getCurrentZoneName());
        str.append("\n" + getCurrentZoneDescription());
        str.append("\n" + getCurrentZoneExits());
        return String.valueOf(str);
    }
}