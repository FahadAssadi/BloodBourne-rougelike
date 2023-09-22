package game.artifacts.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.PurchasableItem;
import game.misc.Utility;

public class ScamQuirk implements Quirk {
    private final double probability;

    public ScamQuirk(double probability) {
        this.probability = probability;
    }

    @Override
    public void perform(PurchasableItem purchasableItem) {
        purchasableItem.setItem(null);
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
