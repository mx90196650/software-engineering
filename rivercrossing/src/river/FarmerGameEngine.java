package river;

import java.awt.Color;

public class FarmerGameEngine extends AbstractGameEngine {

    private static final Item BEANS = Item.ITEM_0;
    private static final Item GOOSE = Item.ITEM_1;
    private static final Item WOLF = Item.ITEM_2;
    private static final Item FARMER = Item.ITEM_3;

    public FarmerGameEngine() {
        items.put(BEANS, new GameObject("B", Location.START, Color.CYAN, false));
        items.put(GOOSE, new GameObject("G", Location.START, Color.CYAN, false));
        items.put(WOLF, new GameObject("W", Location.START, Color.CYAN, false));
        items.put(FARMER, new GameObject("", Location.START, Color.MAGENTA, true));
    }

    @Override
    public boolean gameIsLost() {
        Location item0Location = items.get(BEANS).getLocation();
        Location item1Location = items.get(GOOSE).getLocation();
        Location item2Location = items.get(WOLF).getLocation();
        Location item3Location = items.get(FARMER).getLocation();
        if (item1Location.isOnBoat()) {
            return false;
        }
        if (item1Location == item3Location) {
            return false;
        }
        if (item1Location == getBoatLocation()) {
            return false;
        }
        if (item1Location == item2Location) {
            return true;
        }
        if (item1Location == item0Location) {
            return true;
        }
        return false;
    }
}
