package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;

public interface Quirk {

    // Takes actor because a vendor might have a quirk that does something to the actor
    // (ie: actor refers to the person trading with the merchant.
    // (eg: he kicks the actor after taking his money)

    // From the point of view of the merchant
    // (ie: the merchant is selling)
    String performMerchantSelling(Actor actor, TransactionItem transactionItem);

    // From the point of view of the merchant
    // (ie: the merchant is purchasing)
    String performMerchantPurchasing(Actor actor, TransactionItem transactionItem);

    boolean doesOccur();
}
