import java.util.ArrayList;
/**
 * This is the store class containing the properties for each store/shop in the game.
 * @author  Zach Stepp
 */
public class Store implements ItemIndex{
    private String name;
    private ArrayList<Item> stock = new ArrayList<>();

    public Store(String name, ArrayList<Item> stock) {
        this.name = name;
        this.stock = stock;
    }

    /**
     * Builds a fancy interface string of the store's stock and player's inventory.
     * @return store interface
     */
    public String viewStore(Player player) {
        StringBuilder str = new StringBuilder();
        str.append("\n|=-=-=-=-=-=-=-=-=-= Store =-=-=-=-=-=-=-=-=-=|=-=-=-=-=-=-=-=-= Inventory =-=-=-=-=-=-=-=-=|");
        str.append(String.format("\n|    %-37s    |    Capacity: (%-2d /28 )         Gold: %-5d  |", name, player.getInventory().size(), player.getGold()));
        for (int i = 0; i < player.getInventory().size() || i < stock.size(); i++){
            if (i < player.getInventory().size() && i < stock.size()) {
                str.append(String.format("\n|  %-34s - %-4d  |  %-34s + %-4d  |", stock.get(i).getName(), stock.get(i).getValue(), player.getInventory().get(i).getName(), player.getInventory().get(i).getValue()));
            } if (i < player.getInventory().size() && i > stock.size()) {
                str.append(String.format("\n|  %41s  |  %-34s + %-4d  |"," ",player.getInventory().get(i).getName(), player.getInventory().get(i).getValue()));
            } if (i > player.getInventory().size() && i < stock.size())
                str.append(String.format("\n|  %-34s - %-4d  |  %41s  |",stock.get(i).getName(), stock.get(i).getValue()," "));
        }
        str.append("\n|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
        return String.valueOf(str);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getStock() {
        return stock;
    }
}

