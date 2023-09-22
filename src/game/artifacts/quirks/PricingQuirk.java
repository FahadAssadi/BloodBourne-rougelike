package game.artifacts.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.PurchasableItem;
import game.misc.Utility;

public class PricingQuirk implements Quirk {
    private double occurrenceChance;
    private double pricePercentage;

    public PricingQuirk(double occurrenceChance, double pricePercentage) {
        this.occurrenceChance = occurrenceChance;
        this.pricePercentage = pricePercentage;
    }

    @Override
    public void perform(Actor actor, Item item, int price) {

        if (!Utility.getRandomEventOccurs(occurrenceChance))
        {
            actor.deductBalance(price);
            actor.addItemToInventory(item);
        }
        else {
            int markedUpPrice =  (int) (price * (1+this.pricePercentage/100));
            actor.deductBalance(markedUpPrice);
            actor.addItemToInventory(item);
        }

    }


}
