package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.PurchasableItem;
import game.artifacts.quirks.Quirk;

public class PurchaseAction extends Action {

    private final PurchasableItem purchasableItem;
    private final Quirk quirk;

    public PurchaseAction(PurchasableItem purchasableItem, Quirk quirk){
        this.purchasableItem = purchasableItem;
        this.quirk = quirk;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if the quirk occurs
        if (this.quirk.doesOccur()){
            this.quirk.perform(this.purchasableItem);
        }

        // Add the item if it's not null
        if (this.purchasableItem.getItem() != null){
            actor.addItemToInventory(this.purchasableItem.getItem());
        }

        // Deduct the price of the item
        actor.deductBalance(this.purchasableItem.getPrice());

        return actor + " purchased " + purchasableItem.getItem() + " for " + purchasableItem.getPrice();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + purchasableItem.getItem() + " for " + purchasableItem.getPrice();
    }
}
