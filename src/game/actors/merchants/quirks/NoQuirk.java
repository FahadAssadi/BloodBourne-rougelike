package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;

public class NoQuirk implements Quirk{

    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        return null;
    }

    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        return null;
    }

    @Override
    public boolean doesOccur() {
        return false;
    }
}
