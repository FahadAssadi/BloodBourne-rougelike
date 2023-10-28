package game.actors.friendly.merchants.traders.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;

/**
 * The Quirk interface represents special behaviors or quirks that merchants can have during transactions in a game.
 */
public interface Quirk {

    /**
     * Represents the behavior of a merchant when selling an item to an actor (usually the player).
     *
     * @param actor            The actor representing the player or actor trading with the merchant.
     * @param transactionItem  The TransactionItem representing the item being sold along with its price.
     * @return A message describing the transaction outcome.
     */
    String performMerchantSelling(Actor actor, TransactionItem transactionItem);

    /**
     * Represents the behavior of a merchant when purchasing an item from an actor (usually the player).
     *
     * @param actor            The actor representing the player or actor trading with the merchant.
     * @param transactionItem  The TransactionItem representing the item being purchased along with its price.
     * @return A message describing the transaction outcome.
     */
    String performMerchantPurchasing(Actor actor, TransactionItem transactionItem);

    /**
     * Determines whether the quirk occurs during a transaction.
     *
     * @return {@code true} if the quirk occurs, {@code false} otherwise.
     */
    boolean doesOccur();
}
