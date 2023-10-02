package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.TransactionItem;
import game.misc.Utility;

public class ScamQuirk implements Quirk {
    private final double probability;

    public ScamQuirk(double probability) {
        this.probability = probability;
    }

    public ScamQuirk() {
        this.probability = 100;
    }

    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        int price = transactionItem.getPrice();
        int actorBalance = actor.getBalance();

        int balanceToDeduct = Math.min(price, actorBalance);

        actor.deductBalance(balanceToDeduct);

        return actor + " purchases nothing for " + balanceToDeduct;
    }

    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        Item item = transactionItem.getItem();

        actor.removeItemFromInventory(item);

        return actor + " sells " + item + " for 0 runes.";
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
