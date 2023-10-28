package game.actors.friendly.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.TransactionItem;
import game.misc.Utility;

/**
 * A quirk representing a merchant's behavior to scam the actor (usually the player) during a transaction.
 */
public class ScamQuirk implements Quirk {
    private final double DEFAULT_SCAM_PROBABILITY = 100;
    private final double probability;

    /**
     * Constructor for the ScamQuirk class with a custom probability.
     *
     * @param probability The probability of this quirk occurring during a transaction.
     */
    public ScamQuirk(double probability) {
        this.probability = probability;
    }

    /**
     * Default constructor for the ScamQuirk class with a probability of 100%.
     */
    public ScamQuirk() {
        this.probability = DEFAULT_SCAM_PROBABILITY;
    }

    /**
     * Performs the selling behavior associated with this quirk, which involves deducting the actor's balance.
     *
     * @param actor           The actor involved in the transaction.
     * @param transactionItem The item being transacted.
     * @return A message describing the transaction outcome.
     */
    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        int price = transactionItem.getPrice();
        int actorBalance = actor.getBalance();

        int balanceToDeduct = Math.min(price, actorBalance);

        actor.deductBalance(balanceToDeduct);

        return actor + " purchases nothing for " + balanceToDeduct;
    }

    /**
     * Performs the purchasing behavior associated with this quirk, which involves selling the item for 0 runes.
     *
     * @param actor           The actor involved in the transaction.
     * @param transactionItem The item being transacted.
     * @return A message describing the transaction outcome.
     */
    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        Item item = transactionItem.getItem();

        actor.removeItemFromInventory(item);

        return actor + " sells " + item + " for 0 runes.";
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
