package game.actors.friendly.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.TransactionItem;
import game.misc.Utility;

/**
 * A class representing a merchant quirk that affects item pricing during transactions.
 *
 * The `PricingQuirk` class implements the `Quirk` interface, which is used to define special behaviors for merchants
 * in the game. This quirk introduces a pricing adjustment based on a given probability and price percentage.
 * The item price can increase or decrease during transactions with this quirk based on the specified probability.
 *
 * Created by:
 * @author Fahad Assadi
 */
public class PricingQuirk implements Quirk {
    private final double probability;
    private final double pricePercentage;

    /**
     * Constructor to create an instance of the PricingQuirk with the given probability and price percentage.
     *
     * @param probability     The probability of the quirk affecting the item price (0.0 to 1.0).
     * @param pricePercentage The percentage by which the item price can increase (positive) or decrease (negative).
     */
    public PricingQuirk(double probability, double pricePercentage) {
        this.probability = probability;
        this.pricePercentage = pricePercentage;
    }

    /**
     * Adjusts the price of an item for selling to a merchant based on the price percentage.
     *
     * @param actor           The actor representing the merchant.
     * @param transactionItem The transaction item containing the item to be sold and its price.
     * @return A message describing the transaction outcome.
     */
    @Override
    public String performMerchantSelling(Actor actor, TransactionItem transactionItem) {
        Item item = transactionItem.getItem();
        int priceChange = (int) (transactionItem.getPrice() * (1 + this.pricePercentage/100));

        if (actor.getBalance() < priceChange){
            return actor + " needs " + (priceChange - actor.getBalance()) + " more to complete the purchase.";
        }

        actor.addItemToInventory(item);
        actor.deductBalance(priceChange);

        return actor + " purchases " + item + " for " + priceChange;
    }

    /**
     * Adjusts the price of an item for purchasing from a merchant based on the price percentage.
     *
     * @param actor           The actor representing the merchant.
     * @param transactionItem The transaction item containing the item to be purchased and its price.
     * @return A message describing the transaction outcome.
     */
    @Override
    public String performMerchantPurchasing(Actor actor, TransactionItem transactionItem) {
        Item item = transactionItem.getItem();
        int priceChange = (int) (transactionItem.getPrice() * (1 + this.pricePercentage/100));

        actor.addBalance(priceChange);
        actor.removeItemFromInventory(item);

        return actor + " sells " + item + " for " + priceChange;
    }

    /**
     * Determines whether this quirk occurs based on the specified probability.
     *
     * @return {@code true} if the quirk occurs, {@code false} otherwise.
     */
    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
