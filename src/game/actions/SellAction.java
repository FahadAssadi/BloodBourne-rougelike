package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.TransactionItem;
import game.actors.merchants.quirks.Quirk;

/**
 * An action that allows an actor to sell an item to the IsolatedTraveller.
 */
public class SellAction extends Action {
    /**
     * The item to be sold in the transaction.
     */
    private final TransactionItem transactionItem;

    /**
     * The quirk to be used in the transaction.
     */
    private final Quirk quirk;

    /**
     * Constructor.
     *
     * @param transactionItem The item to be purchased in the transaction.
     * @param quirk The quirk to be used in the transaction.
     */
    public SellAction(TransactionItem transactionItem, Quirk quirk){
        this.transactionItem = transactionItem;
        this.quirk = quirk;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String message;

        // Check if the quirk occurs
        if (this.quirk.doesOccur()){
            message = this.quirk.performMerchantPurchasing(actor, this.transactionItem);

        } else {
            // Remove the item and add the balance
            actor.removeItemFromInventory(this.transactionItem.getItem());
            actor.addBalance(this.transactionItem.getPrice());

            message = actor + " sold " + this.transactionItem.getItem() + " for " + this.transactionItem.getPrice();
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
        return actor + " sells " + this.transactionItem.getItem() + " for " + this.transactionItem.getPrice();
    }

}
