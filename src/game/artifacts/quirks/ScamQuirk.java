package game.artifacts.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.PurchasableItem;
import game.misc.Utility;

public class ScamQuirk implements Quirk {

    private final double occurrenceChance;
    public ScamQuirk(double chance) {

        this.occurrenceChance = chance;
    }

    @Override
    public void perform(Actor actor, Item item, int price) {

        if (!Utility.getRandomEventOccurs(occurrenceChance))
        {
            actor.addItemToInventory(item);
        }
        actor.deductBalance(price);

    }


}
