package game.actors.friendly.merchants.traders.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;

/**
 * A class representing a merchant quirk with no special behavior.
 *
 * The `NoQuirk` class implements the `Quirk` interface, which is used to define special behaviors for merchants
 * in the game. In this class, there is no special behavior implemented, and all methods return default or null values.
 *
 * Created by:
 * @author Fahad Assadi
 */
public class NoQuirk implements Quirk{

    /**
     * Returns null, indicating no special behavior when a merchant sells an item.
     *
     * @param actor           The actor representing the merchant.
     * @param transactionItem The item being sold.
     * @return Null, indicating no special behavior.
     */
    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        return null;
    }

    /**
     * Returns null, indicating no special behavior when a merchant purchases an item.
     *
     * @param actor           The actor representing the merchant.
     * @param transactionItem The item being purchased.
     * @return Null, indicating no special behavior.
     */
    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        return null;
    }

    /**
     * Returns false, indicating that this quirk does not occur.
     *
     * @return {@code true} if the quirk occurs, {@code false} otherwise.
     */
    @Override
    public boolean doesOccur() {
        return false;
    }
}
