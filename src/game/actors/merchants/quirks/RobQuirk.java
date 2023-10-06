package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.TransactionItem;
import game.misc.Utility;

/**
 * A quirk representing a merchant's behavior to rob the actor (usually the player) during a purchase transaction.
 */
public class RobQuirk implements Quirk{
    private final double probability;

    /**
     * Constructor for the RobQuirk class.
     *
     * @param probability The probability of this quirk occurring during a transaction.
     */
    public RobQuirk(double probability){
        this.probability = probability;
    }

    /**
     * Performs the selling behavior associated with this quirk.
     * Don't have an implementation yet.
     *
     * @param actor           The actor involved in the transaction.
     * @param transactionItem The item being transacted.
     * @return A message describing the transaction outcome.
     */
    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        return null;
    }

    /**
     * Performs the purchasing behavior associated with this quirk.
     *
     * @param actor           The actor involved in the transaction.
     * @param transactionItem The item being transacted.
     * @return A message describing the transaction outcome.
     */
    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        // Implementation details for robbing the actor and performing a negative transaction.
        Item item = transactionItem.getItem();

        actor.removeItemFromInventory(item);
        new ScamQuirk().performMerchantSelling(actor,transactionItem);

        return actor + " sells " + item + " for -" + Math.min(actor.getBalance(), transactionItem.getPrice());
    }

    /**
     * Checks whether this quirk occurs during a transaction based on its probability.
     *
     * @return {@code true} if the quirk occurs, {@code false} otherwise.
     */
    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
