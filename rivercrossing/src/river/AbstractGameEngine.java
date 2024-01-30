package river;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractGameEngine implements GameEngine {

    protected final Map<Item, GameObject> items;
    private Location boatLocation;

    protected AbstractGameEngine() {
        items = new HashMap<>();
        boatLocation = Location.START;
    }

    @Override
    public String getItemLabel(Item item) {
        return items.get(item).getLabel();
    }

    @Override
    public Color getItemColor(Item item) {
        return items.get(item).getColor();
    }

    @Override
    public boolean getItemIsDriver(Item item) {
        return items.get(item).isDriver();
    }

    @Override
    public Location getItemLocation(Item item) {
        return items.get(item).getLocation();
    }

    @Override
    public void setItemLocation(Item item, Location location) {
        items.get(item).setLocation(location);
    }

    @Override
    public Location getBoatLocation() {
        return boatLocation;
    }

    @Override
    public void loadBoat(Item item) {
        //the same shore
        if (getItemLocation(item) == getBoatLocation()) {
            if (numberOfItemsInBoat() < 2){
                setItemLocation(item, Location.BOAT);
            }
        }
    }

    @Override
    public void unloadBoat(Item item) {
        if (items.get(item).getLocation().isOnBoat())
            setItemLocation(item, boatLocation);
    }

    @Override
    public void rowBoat() {
        assert (!boatLocation.isOnBoat());
        if (isDriverOnBoard()) {
            if (boatLocation.isAtStart()) {
                boatLocation = Location.FINISH;
            } else {
                boatLocation = Location.START;
            }
        }
    }

    @Override
    public boolean gameIsWon() {
        for (Item item: items.keySet()) {
            if (!getItemLocation(item).isAtFinish()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public abstract boolean gameIsLost();

    @Override
    public void resetGame() {
        for (Item item: items.keySet()) {
            setItemLocation(item, Location.START);
        }
        boatLocation = Location.START;
    }

    @Override
    public List<Item> getItems() {
        ArrayList<Item> itemsArray = new ArrayList<>();
        for (Item item : Item.values()) {
            if (item.ordinal() < items.size()) {
                itemsArray.add(item);
            }
        }
        return itemsArray;
    }

    protected int numberOfItemsInBoat() {
        int numberOfItems = 0;
        for (Item item: items.keySet()) {
            if (getItemLocation(item).isOnBoat()) {
                numberOfItems++;
            }
        }
        return numberOfItems;
    }

    private boolean isDriverOnBoard() {
        for (Item item: items.keySet()) {
            if (getItemLocation(item).isOnBoat() && getItemIsDriver(item)) {
                return true;
            }
        }
        return false;
    }
}
