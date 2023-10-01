package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.TransactionItem;
import game.misc.Utility;

public class PricingQuirk implements Quirk {
    private final double probability;
    private final double pricePercentage;

    public PricingQuirk(double probability, double pricePercentage) {
        this.probability = probability;
        this.pricePercentage = pricePercentage;
    }

    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        int priceChange = (int) (transactionItem.getPrice() * (1 + this.pricePercentage/100));
        Item item = transactionItem.getItem();

        actor.deductBalance(priceChange);
        actor.addItemToInventory(item);

        return actor + " purchases " + item + " for " + priceChange;
    }

    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        int priceChange = (int) (transactionItem.getPrice() * (1 + this.pricePercentage/100));
        Item item = transactionItem.getItem();

        actor.addBalance(priceChange);
        actor.removeItemFromInventory(item);

        return actor + " sells " + item + " for " + priceChange;
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
