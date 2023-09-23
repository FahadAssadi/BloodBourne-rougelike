package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.TransactionItem;
import game.artifacts.quirks.Quirk;

public class SellAction extends Action {
    private final TransactionItem transactionItem;
    private final Quirk quirk;

    public SellAction(TransactionItem transactionItem, Quirk quirk){
        this.transactionItem = transactionItem;
        this.quirk = quirk;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Item item = this.transactionItem.getItem();

        if (this.quirk.doesOccur()){
            this.quirk.perform(actor, this.transactionItem);
        }

        // Remove the item if it's null
        if (this.transactionItem.getItem() == null){
            actor.removeItemFromInventory(item);
        } else {
            // Remove the item and add the balance
            actor.removeItemFromInventory(this.transactionItem.getItem());
            // Add the price of the item
            actor.addBalance(this.transactionItem.getPrice());
        }

        return actor + " sold " + item + " for " + (this.transactionItem.getItem() == null ? 0 : this.transactionItem.getPrice());
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.transactionItem.getItem() + " for " + this.transactionItem.getPrice();
    }
}
