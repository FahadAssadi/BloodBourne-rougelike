package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.TransactionItem;
import game.actors.friendly.merchants.traders.quirks.Quirk;

/**
 * An action that allows an actor to purchase an item from the IsolatedTraveller.
 */
public class PurchaseAction extends Action {
    /**
     * The item to be purchased in the transaction.
     */
    private final TransactionItem transactionItem;

    /**
     * The quirk to be used in the transaction.
     */
    private final Quirk quirk;

    /**
     * Constructor.
     *
     * @param transactionItem The item to be sold in the transaction.
     * @param quirk The quirk to be used in the transaction.
     */
    public PurchaseAction(TransactionItem transactionItem, Quirk quirk){
        this.transactionItem = transactionItem;
        this.quirk = quirk;
    }

    /**
     * Execute the action, allowing the actor sell the transactionItem.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap where the action occurs.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message;

        // Check if the quirk occurs
        if (this.quirk.doesOccur()){
            message = this.quirk.performMerchantSelling(actor, this.transactionItem);
            
        } else {
            // Check if the actor has sufficient funds to purchase the item.
            if (actor.getBalance() < this.transactionItem.getPrice()){
                return actor + " needs " + (this.transactionItem.getPrice() - actor.getBalance()) + " more to complete the purchase.";
            }

            // Add the item and deduct the balance
            actor.addItemToInventory(this.transactionItem.getItem());
            actor.deductBalance(this.transactionItem.getPrice());

            message = actor + " purchases " + this.transactionItem.getItem() + " for " + this.transactionItem.getPrice();
        }

        return message;
    }

    /**
     * Get a menu description for the action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + transactionItem.getItem() + " for " + transactionItem.getPrice();
    }
    
}
