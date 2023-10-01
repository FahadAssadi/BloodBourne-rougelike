package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.TransactionItem;
import game.actors.merchants.quirks.Quirk;

public class PurchaseAction extends Action {

    private final TransactionItem transactionItem;
    private final Quirk quirk;

    public PurchaseAction(TransactionItem transactionItem, Quirk quirk){
        this.transactionItem = transactionItem;
        this.quirk = quirk;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String message;

        // Check if the quirk occurs
        if (this.quirk.doesOccur()){
            message = this.quirk.performMerchantSelling(actor, this.transactionItem);
            
        } else {
            // Add the item and deduct the balance
            actor.addItemToInventory(this.transactionItem.getItem());
            actor.deductBalance(this.transactionItem.getPrice());

            message = actor + " purchases " + this.transactionItem.getItem() + " for " + this.transactionItem.getPrice();
        }

        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + transactionItem.getItem() + " for " + transactionItem.getPrice();
    }
}
