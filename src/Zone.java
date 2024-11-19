import java.util.ArrayList;
/**
 * This is the Zone class containing properties for each unique "zone" or area found in the game.
 * @author  Zach Stepp
 */
public class Zone{

    private String name;
    private String description;
    private Zone north;
    private Zone south;
    private Zone east;
    private Zone west;
    private Store store;
    private String craftable;
    private Object zoneAction;
    public Zone(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Zone getAdjoiningZone(char direction) {

        if (direction == 'n' && this.north != null)
            return north;
        else if (direction == 's' && this.south != null)
            return south;
        else if (direction == 'e' && this.east != null)
            return east;
        else if (direction == 'w' && this.west != null)
            return west;
        else
            return null;
    }

    /**
     * Creates a string of all available exits to a zone
     * @return zone exits string
     */
    public String getExits() {
        ArrayList<Zone> exits = new ArrayList<Zone>();
        exits.add(this.north); exits.add(this.south); exits.add(this.east); exits.add(this.west);
        ArrayList<String> charCompass = new ArrayList<String>();
        charCompass.add("North"); charCompass.add("South"); charCompass.add("East"); charCompass.add("West");

        String output = "Available Paths: ";
        for(int i = 0; i < exits.size(); i++) {
            if (exits.get(i) != null) {
                output += " " + charCompass.get(i);
            }
        }
        return output;
    }
    /**
     * Returns a boolean depending on whether the direction specified in user input is valid or not.
     * @return boolean of whether the direction is valid
     */
    public boolean isValidDirection(char direction) {
        if (direction == 'n' && this.north != null)
            return true;
        else if (direction == 's' && this.south != null)
            return true;
        else if (direction == 'e' && this.east != null)
            return true;
        else if (direction == 'w' && this.west != null)
            return true;
        else
            return false;
    }
    public void setNorth(Zone north) {
        this.north = north;
    }

    public void setSouth(Zone south) {
        this.south = south;
    }

    public void setEast(Zone east) {
        this.east = east;
    }

    public void setWest(Zone west) {
        this.west = west;
    }
    public void setStore(Store store) {this.store = store;}

    public Store getStore() {return store;}

    public String getCraftable() {return craftable;}

    public void setCraftable(String craftable) {this.craftable = craftable;}

    public void setZoneAction(Object zoneAction) {this.zoneAction = zoneAction;}

    public Object getZoneAction() {return zoneAction;}
}
