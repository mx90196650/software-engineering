package river;

import java.awt.*;

public class MonsterGameEngine extends AbstractGameEngine {

    public MonsterGameEngine() {
        GameObject monster1 = new GameObject("M", Location.START, Color.RED, true);
        GameObject monster2 = new GameObject("M", Location.START, Color.RED, true);
        GameObject monster3 = new GameObject("M", Location.START, Color.RED, true);
        GameObject munchkin1 = new GameObject("K", Location.START, Color.GREEN, true);
        GameObject munchkin2 = new GameObject("K", Location.START, Color.GREEN, true);
        GameObject munchkin3 = new GameObject("K", Location.START, Color.GREEN, true);
        items.put(Item.ITEM_0, monster1);
        items.put(Item.ITEM_2, monster2);
        items.put(Item.ITEM_4, monster3);
        items.put(Item.ITEM_1, munchkin1);
        items.put(Item.ITEM_3, munchkin2);
        items.put(Item.ITEM_5, munchkin3);
    }

    @Override
    public boolean gameIsLost() {
        int monstersAtSTART = 0;
        int munchkinsAtSTART = 0;
        int monstersAtFINISH = 0;
        int munchkinsAtFINISH = 0;
        for (Item item: getItems()) {
            if (item.ordinal() % 2 == 0) { //monster
                if (itemAtStart(item)) {
                    monstersAtSTART++;
                } else if (itemAtFinish(item)) {
                    monstersAtFINISH++;
                }
            } else { //munchkin
                if (itemAtStart(item)) {
                    munchkinsAtSTART++;
                } else if (itemAtFinish(item)) {
                    munchkinsAtFINISH++;
                }
            }
        }
        if (munchkinsAtSTART > 0 && monstersAtSTART > munchkinsAtSTART)
            return true;
        if (munchkinsAtFINISH > 0 && monstersAtFINISH > munchkinsAtFINISH)
            return true;
        return false;
    }

    private boolean itemAtStart(Item item){
        //on the shore
        if (getItemLocation(item).isAtStart())
            return true;
        //on the boat, boat on the left shore
        if (getItemLocation(item).isOnBoat() && getBoatLocation().isAtStart())
            return true;
        return false;
    }

    private boolean itemAtFinish(Item item){
        if (getItemLocation(item).isAtFinish())
            return true;
        if (getItemLocation(item).isOnBoat() && getBoatLocation().isAtFinish())
            return true;
        return false;
    }
}
