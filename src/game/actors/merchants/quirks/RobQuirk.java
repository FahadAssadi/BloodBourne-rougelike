package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.TransactionItem;
import game.misc.Utility;

public class RobQuirk implements Quirk{
    private final double probability;

    public RobQuirk(double probability){
        this.probability = probability;
    }

    // Don't have an implementation yet.
    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        return null;
    }

    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        Item item = transactionItem.getItem();

        actor.removeItemFromInventory(item);
        new ScamQuirk(100).performMerchantSelling(actor,transactionItem);

        return actor + " sells " + item + " for -" + Math.min(actor.getBalance(), transactionItem.getPrice());
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
