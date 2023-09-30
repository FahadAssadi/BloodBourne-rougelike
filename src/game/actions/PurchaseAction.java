package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.TransactionItem;
import game.artifacts.quirks.Quirk;

public class PurchaseAction extends Action {

    private final TransactionItem transactionItem;
    private final Quirk quirk;

    public PurchaseAction(TransactionItem transactionItem, Quirk quirk){
        this.transactionItem = transactionItem;
        this.quirk = quirk;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if the quirk occurs
        if (this.quirk.doesOccur()){
            this.quirk.perform(this.transactionItem);
        }

        // Add the item if it's not null
        if (this.transactionItem.getItem() != null){
            actor.addItemToInventory(this.transactionItem.getItem());
        }

        // Deduct the price of the item
        actor.deductBalance(this.transactionItem.getPrice());

        return actor + " purchased " + transactionItem.getItem() + " for " + transactionItem.getPrice();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + transactionItem.getItem() + " for " + transactionItem.getPrice();
    }
}
