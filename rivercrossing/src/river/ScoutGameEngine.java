package river;

import java.awt.*;

public class ScoutGameEngine extends AbstractGameEngine{
    public ScoutGameEngine() {
        GameObject beaver1 = new GameObject("B", Location.START, Color.RED, true);
        GameObject explorer1 = new GameObject("E", Location.START, Color.GREEN, true);
        GameObject beaver2 = new GameObject("B", Location.START, Color.RED, true);
        GameObject explorer2 = new GameObject("E", Location.START, Color.GREEN, true);
        GameObject beaver3 = new GameObject("B", Location.START, Color.RED, true);
        items.put(Item.ITEM_0, beaver1);
        items.put(Item.ITEM_2, explorer1);
        items.put(Item.ITEM_4, beaver2);
        items.put(Item.ITEM_1, explorer2);
        items.put(Item.ITEM_3, beaver3);
    }

    /**
     * @return
     */
    @Override
    public boolean gameIsLost() {
        return false;
    }

    /**
     * @return
     */
    public void loadBoat(Item item) {
        //the same shore
        //A maximum of two Beaver Scouts or one Explorer Scout are allowed in the boat for each crossing.
        if (getItemLocation(item) == getBoatLocation()) {
            if (numberOfItemsInBoat() < 2){
                if (getItemLocation(Item.ITEM_0).isOnBoat() || getItemLocation(Item.ITEM_3).isOnBoat() || getItemLocation(Item.ITEM_4).isOnBoat()) {
                    if (item == Item.ITEM_1 || item == Item.ITEM_2) {
                        return;
                    }
                } else if (getItemLocation(Item.ITEM_1).isOnBoat() || getItemLocation(Item.ITEM_2).isOnBoat()) {
                    return;
                }
                setItemLocation(item, Location.BOAT);
            }
        }
    }
}
