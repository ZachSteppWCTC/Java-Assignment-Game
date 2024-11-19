import java.util.*;
/**
 * This is the main class for the AssignmentScape game including a character creation and all game commands with user input
 * @author  Zach Stepp
 */
public class Main{
    public static void main(String[] args) {
        boolean programActive = false;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\n=-=-=-=-= Welcome to AssignmentScape =-=-=-=-=");
        System.out.println("Begin your journey by creating your character.\n");
        System.out.print("What shall your character be named? : ");
        Player player = new Player(keyboard.nextLine());
        System.out.print("\nOut of red, green, or blue, which is your favorite color? (R/G/B) : ");
        switch (keyboard.nextLine().toUpperCase()) {
            case "R", "RED" -> player.addItemToInventory(ItemIndex.ruby);
            case "G", "GREEN" -> player.addItemToInventory(ItemIndex.emerald);
            case "B", "BLUE" -> player.addItemToInventory(ItemIndex.sapphire);
            default -> System.out.println("You didn't pick one of the options, no free gem for you.");
        }
        System.out.print("\n\nWhich do you prefer, (A) Fighting evil monsters, (B) Harvesting ores and wood, or (C) Crafting strong gear and intricate jewelery? (A/B/C) : ");
        switch (keyboard.nextLine().toUpperCase()) {
            case "A", "FIGHTING" ->
                player.addXp(player.Fighting, 300);
            case "B", "HARVESTING" -> {
                player.addXp(player.Mining, 150);
                player.addXp(player.Woodcutting, 150);
            }
            case "C", "CRAFTING" -> {
                player.addXp(player.Smithing, 150);
                player.addXp(player.Crafting, 150);
            }
            default -> System.out.println("You didn't pick one of the options, no boosted skills for you.");
        }
            player.checkHealthUpgrade();
            Map map = new Map(player);
            Messages.controls();
            programActive = true;
            String last = "";

        //Big while statement containing the game's command input.
    while (programActive) {
        System.out.println(map);
        System.out.print("> ");
        String command = keyboard.nextLine();
        // gets the previously input command for quick repetitive command execution if the player chooses
        if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LAST"))
            command = last;
        // splits the command after the first space for commands that use items or view commands
        String[] split = command.split("\\s", 2);
        switch (split[0].toUpperCase()) {
            case "?", "HELP", "COMMANDS" -> Messages.controls(); // prints controls again
            case "EQUIP" -> {
                // attempts to equip the item if one is specified
                if (Arrays.stream(split).count() < 2)
                    Messages.enterName("equip");
                else player.equipItemByName(split[1]);
            }
            case "BUY" -> {
                // attempts to buy the item if you are in a shop, the item is specified
                if (map.getCurrentZoneAction().getClass() != Store.class)
                    System.out.println("\nYou need to be in a store to buy items.");
                else {
                    if (Arrays.stream(split).count() < 2)
                        Messages.enterName("buy");
                    else {
                        player.buyShopItemByName(split[1], ((Store) map.getCurrentZoneAction()).getStock());
                    }
                }
            }
            case "SELL" -> {
                // attempts to sell an item if you are in a shop and the item is specified
                if (map.getCurrentZoneAction().getClass() != Store.class)
                    System.out.println("\nYou need to be in a shop to sell items.");
                else {
                    if (Arrays.stream(split).count() < 2)
                        Messages.enterName("sell");
                    else {
                        player.sellItemByName(split[1]);
                    }
                }
            }
            case "CRAFT" -> {
                // attempts to craft an item if you are in a zone where crafting is allowed, an item is specified,
                // and the item is able to be crafted in the specific zone
                if (map.getCurrentZoneAction() != "smithing" && map.getCurrentZoneAction() != "crafting")
                    System.out.println("\nYou need to be at a Crafting or Smithing area to craft items.");
                else if (Arrays.stream(split).count() < 2)
                    Messages.enterName("craft");
                else if (player.findCraftItemByName(split[1]) == null)
                    System.out.println("\nThis cannot be crafted.");
                else if ((player.findCraftItemByName(split[1]).getRecipe().getSkill() != ItemIndex.Smithing && map.getCurrentZoneAction() == "smithing") || (player.findCraftItemByName(split[1]).getRecipe().getSkill() != ItemIndex.Crafting && map.getCurrentZoneAction() == "crafting"))
                    System.out.println("\nYou cannot craft this item here.");
                else
                    player.tryCraftAction(player.findCraftItemByName(split[1]).getRecipe());
            }
            case "VIEW" -> {
                // views certain interfaces depending on the second half of the command
                if (Arrays.stream(split).count() < 2)
                    System.out.println("\nView Options: Inventory, Stats, Skills.");
                else switch (split[1].toUpperCase()) {
                    case "INVENTORY", "I", "BACKPACK", "B" -> System.out.println(player.printInventory());
                    case "STATS", "CHARACTER", "C" -> System.out.println(player.printStats());
                    case "SKILLS", "S" -> System.out.println(player.printSkills());
                    default -> System.out.println("\nView Options: Inventory, Stats, Skills.");
                }
            }
            case "SOUTH", "S", "NORTH", "N", "EAST", "E", "WEST", "W" -> map.move(split[0].charAt(0)); // changes the player's current zone
            case "BANK", "B", "DEPOSIT", "D", "STORE" -> {
                // puts an item in the bank if the player is at a bank and the item is specified
                if (map.getCurrentZoneAction() != "bank")
                    System.out.println("\nYou need to be at a bank to store items.");
                else if (Arrays.stream(split).count() < 2)
                    Messages.enterName("store it");
                else
                    player.bankItemByName(split[1]);
            }
            case "UNBANK", "U", "WITHDRAW" -> {
                // attempts to withdraw an item from the bank if the player is at a bank and the item is specified
                if (map.getCurrentZoneAction() != "bank")
                    System.out.println("\nYou need to be at a bank to withdraw items.");
                else if (Arrays.stream(split).count() < 2)
                    Messages.enterName("withdraw it");
                else
                    player.withdrawItemByName(split[1]);
            }
            case "EAT", "USE" -> {
                // attempts to eat an item from the inventory if the item is specified
                if (Arrays.stream(split).count() < 2)
                    Messages.enterName("eat it");
                else player.eatItem(split[1]);
            }
            case "INTERACT", "I" -> {
                // interactions are possible for all zones, but not all zones do something
                if (map.getCurrentZoneAction() == null)
                    System.out.println("\nNothing to do here.");
                else {
                    switch (String.valueOf(map.getCurrentZoneAction().getClass())) {
                        case "class Store" ->
                            // prints the store's interface including the store's stock and player inventory
                                System.out.println(((Store) map.getCurrentZoneAction()).viewStore(player));
                        case "class Event" -> {
                            // if the event has an item and if it has not yet been completed, give the player the item.
                            // prints a message that depends on the event object's completion status
                            if (((Event) map.getCurrentZoneAction()).getEventItem() != null && !((Event) map.getCurrentZoneAction()).getComplete())
                                player.addItemToInventory(((Event) map.getCurrentZoneAction()).getEventItem());
                            System.out.printf("\n" + map.getCurrentZoneAction());
                        }
                        case "class HarvestAction" ->
                            //tries the zone's harvest action
                                player.tryHarvestAction((HarvestAction) map.getCurrentZoneAction());
                        case "class Enemy" -> {
                            //if statement for if the player died battling the zone's enemy, and bringing the consequences if they do
                            if (player.diedInBattle((Enemy) map.getCurrentZoneAction())) {
                                map.respawn();
                                player.setHp(player.getMaxhp());
                                player.removeGold(player.getGold() / 5);
                                System.out.println("\nYou died and lost 20% of your gold.");
                                System.out.println("\nYou respawn in Java Town with your HP fully restored.");
                            }
                        }
                        case "class java.lang.String" -> {
                            //if statements for if the zone's action is a string meant only to identify it
                            if (map.getCurrentZoneAction() == "bank") {
                                System.out.println(player.printBank());
                            } else if (map.getCurrentZoneAction() == "smithing") {
                                Messages.smithingrecipes();
                            } else if (map.getCurrentZoneAction() == "crafting") {
                                Messages.craftingrecipes();
                            } else if (map.getCurrentZoneAction() == "fullheal") {
                                player.setHp(player.getMaxhp());
                                System.out.println("\nBeing in the center of Java Town fills you with determination. HP restored.");
                            }
                        }
                    }
                }
            }
            case "QUIT" -> {
                // Quits the program without an error after confirming the user knows all unsaved progress will be lost.
                System.out.print("\nAre you sure you want to quit? All progress will be lost. (Y/N) : ");
                String confirmquit = keyboard.nextLine();
                if (confirmquit.equalsIgnoreCase("YES") || confirmquit.equalsIgnoreCase("Y")) {
                    programActive = false;
                    System.out.println("Bye Bye!");
                } else System.out.println("\nQuit cancelled.");
            }
            default -> System.out.println("\nPlease enter an appropriate command.");
        }
        last = command;
    }


        }

}
