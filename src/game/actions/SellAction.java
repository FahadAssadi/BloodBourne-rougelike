package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.TransactionItem;
import game.actors.merchants.quirks.Quirk;

public class SellAction extends Action {
    private final TransactionItem transactionItem;
    private final Quirk quirk;

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.transactionItem.getItem() + " for " + this.transactionItem.getPrice();
    }
}
